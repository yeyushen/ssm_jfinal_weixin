package com.skywayct.controller.weixin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.model.Member;
import com.jfinal.weixin.model.Statistics;
import com.jfinal.weixin.model.Wallet;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.UserApi;
import com.skywayct.service.weixin.mywallet.MyWalletManager;
import com.skywayct.service.weixin.ordinarymember.OrdinaryMemberManager;
import com.skywayct.util.DateUtil;
import com.skywayct.util.Logger;
import com.skywayct.util.PageData;
import com.skywayct.util.UuidUtil;


/***
 * 定制执行更新会员名称任务
 * 2016-06-01 10:56
 * @author hmf 
 * 
 */
public class WeixinMemberUpdateTask {
	
	private Logger logger = Logger.getLogger(this.getClass());
	 
	@Resource(name = "ordinarymemberService")
	private OrdinaryMemberManager memberService;
	
	@Resource(name = "mywalletService")
	private MyWalletManager mywalletService;
	
    private PageData pd = null;
    private Member m = null;

	/**
	 * 执行更新任务
	 */
	public void updateMemberTask(){
		logger.info("----------------开始执行更新会员任务info-----------------" );
		
		try{
			ApiConfigKit.setThreadLocalApiConfig(getApiConfig());
			ApiResult userResult = UserApi.getFollowers(null);
			List<ApiResult> userRSList = new ArrayList<ApiResult>();
			userRSList.add(userResult);
			String nextOpenId = userResult.getStr("next_openid");
			if( userResult != null ){
				if( userResult.isSucceed() ){
				//若关注者超过10000的则需要继续获取openId
				while( !nextOpenId.equals("") ){
					userResult = UserApi.getFollowers(nextOpenId);
					nextOpenId = userResult.getStr("next_openid");
					if(userResult.isSucceed() ){
					//因为微信接口拿完openid后,再请求才返回空	
					if( nextOpenId.equals(""))
					{
						break;
					}
					userRSList.add(userResult);
					}
					else{
						break;
					}
				}
				JSONObject tempObj = null;
				JSONArray arrayJson = null;
				ApiResult sigleUser = null;
				String sigleOpenId = null;
				
				pd = new PageData();
				
				for (ApiResult apiResult : userRSList) {
					tempObj = apiResult.get("data");
					arrayJson = JSONArray.fromObject( tempObj.get("openid") );
					
					//每10000个openId的段
					for(int i = 0; i < arrayJson.size(); i++ ){
						sigleUser = UserApi.getUserInfo( arrayJson.opt(i).toString() );  //读取每个openId
						sigleOpenId = sigleUser.getStr("openid");
						
						if( sigleOpenId == null ){
							logger.info("openid is null :" + arrayJson.size() + ".........." + i );
							continue;
						}
						//是否存在openId
						if( memberService.isExist(sigleOpenId) ){
							//更新
							updateMember( sigleUser );
						}else{
							//插入
							insertMember( sigleUser );
						}
					}
					
				}
				System.out.println("-------------执行更新会员任务完成---------------");
				logger.info("-------------执行更新会员任务完成---------------");
				
				}
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("--------------执行更新会员计划发生错误!-------------");
			logger.info("--------------执行更新会员计划发生错误!-------------");
			logger.info(ex.toString());
		}

	}
	
	//更新操作
	private void updateMember(ApiResult sigleUser) throws Exception{
		
		//新增根据微信返回标签判定是否黑名单
/*		JSONArray tagidList = JSONArray.fromObject( sigleUser.get("tagid_list") );
		if( tagidList.size() > 0 ){
			int isblack = tagidList.getInt(0);
			
			if( isblack == 1 ){
				pd.put("dr", 1);
			}else{
				pd.put("dr", 0);
			}
			
		}else{
			pd.put("dr", 0);
		}*/
		pd.put("nick_name", sigleUser.getStr("nickname") );
		pd.put("photo", sigleUser.getStr("headimgurl") );
		pd.put("open_id", sigleUser.getStr("openid") );
		pd.put("province", sigleUser.getStr("province") );
		pd.put("country", sigleUser.getStr("country") );
		pd.put("city", sigleUser.getStr("city") );
		memberService.updateByOpenId(pd);
	}
	
	//插入操作
	private void insertMember(ApiResult sigleUser) throws Exception{
		
		m = new Member();
		m.set("ordinarymember_id", UuidUtil.get32UUID());
		m.set("openid", sigleUser.getStr("openid"));
		m.set("nick_name", sigleUser.getStr("nickname"));
		m.set("name", sigleUser.getStr("nickname"));
		m.set("photo", sigleUser.getStr("headimgurl"));
		m.set("sex",  sigleUser.get("sex"));
		m.set("province",  sigleUser.get("province"));
		m.set("country",  sigleUser.get("country"));
		m.set("city",  sigleUser.get("city"));
		m.set("dr", 0);
		m.set("create_date", DateUtil.fomatDateTiem(System.currentTimeMillis()));
		m.set("level_id", "0");		
		m.set("parent_id", "0");
		m.set("email", "");
		m.set("phone", "");
		m.save();
		
		//保存钱包
		 Wallet w=null;
			//判断账户是否已存在
		 w = Wallet.dao.findFirst(" select * from weixin_wallet where open_id='"+sigleUser.getStr("openid")+"' ");
			if(w!=null){
				w.set("dr", 0).update();
			}
			else{
				w = new Wallet();
				w.set("open_id", sigleUser.getStr("openid"));
				w.set("amount", 0.0);
				w.set("dr", 0);
				w.set("wallet_id",  UuidUtil.get32UUID());
				w.save();
			}
			
			Statistics.addStatistics(sigleUser.getStr("openid"), "0", 1);
			
		}
	
	
	
	
	/**
	 * 获取配置信息
	 * @return
	 */
	private ApiConfig getApiConfig() {
		// TODO Auto-generated method stub
		ApiConfig ac = new ApiConfig();

		// 配置微信 API 相关常量
		ac.setToken(PropKit.get("token"));
		ac.setAppId(PropKit.get("appId"));
		ac.setAppSecret(PropKit.get("appSecret"));

		/**
		 * 是否对消息进行加密，对应于微信平台的消息加解密方式： 1：true进行加密且必须配置 encodingAesKey
		 * 2：false采用明文模式，同时也支持混合模式
		 */
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey",
				"setting it in config file"));
		return ac;
	}
	
}
