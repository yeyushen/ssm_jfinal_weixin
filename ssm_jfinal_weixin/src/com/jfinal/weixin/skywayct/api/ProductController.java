package com.jfinal.weixin.skywayct.api;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.model.Attribution;
import com.jfinal.weixin.model.Dictionaries;
import com.jfinal.weixin.model.Product;
import com.jfinal.weixin.model.ProductType;
import com.jfinal.weixin.model.Wallet;
import com.jfinal.weixin.skywayct.api.common.CommonResponse;
import com.jfinal.weixin.skywayct.api.common.OrderRequest;
import com.jfinal.weixin.skywayct.api.common.WeixinConst;

/**
 * @author 
 * @version V1.0
 * @Title: ProductController.java
 * @date 
 * @Description:
 */
public class ProductController extends Controller {
	/**
	 * 获取产品
	 */
	@Clear
	// @Before({TokenValidator.class ,LogInterceptor.class})
	public void index() {
		
		OrderRequest req = new OrderRequest();
//		req.setPhone("13544452227");
		req.setPhone(getPara("phone"));

		if("default".equals(req.getPhone())){
			renderJson(getDefault());
			return;
		}
		if("default2".equals(req.getPhone())){
			renderJson(newgetDefault());
			return;
		}
		
		String open_id= getSessionAttr("open_id");
		if(StrKit.isBlank(open_id))
		{
			renderText("{\"code\":-1,\"msg\":\"还未关注或页面打开超时\"}");
			return;
		}
		
		CommonResponse res = new CommonResponse();

		if (StrKit.isBlank(req.getPhone())) {
			res.setReturn(50012);
			renderJson(res);
			return;
		}

		if (req.checkPhone()) {
			res.setReturn(50015);
			renderJson(res);
			return;
		}

		if (req.getPhone().length() < 11) {
			res.setReturn(50015);
			renderJson(res);
			return;
		}

        //1查询手机所在地区
		String flag7 = req.getPhone().substring(0, 7);
		Attribution att = Attribution.dao.findFirst("select * from mobile_attribution where number_section1 ='"+flag7+"'");
		if(att==null){
			renderJson("");
			return;
		}
		
		String province = att.getStr("province");
		String city = att.getStr("city");
		String card_type = att.getStr("card_type");
		
		int cardtype = WeixinConst.getCardType(att.getStr("card_type"));
		//先用地市找商品,找不到再用省份  
		List<Dictionaries> d = Dictionaries.dao.find("select * from sys_dictionaries where bianma like '003%' and bianma not in ('003')"
				+ "  and name='"+city+"' ");
		List<Dictionaries> s = Dictionaries.dao.find("select * from sys_dictionaries where bianma like '003%' and bianma not in ('003')"
				+ "  and name='"+province+"' ");
		//如有多条,再进行折扣排序处理; 
	
		String dictionaries_id = null;
		if(d!=null&&d.size()>0){
			dictionaries_id  =d.get(0).getStr("DICTIONARIES_ID");
			
		}
		String s_dictionaries_id = null;
		if(s!=null&&s.size()>0){
			s_dictionaries_id  =s.get(0).getStr("DICTIONARIES_ID");
//			if(dictionaries_id==null)
//				dictionaries_id  =s.get(0).getStr("DICTIONARIES_ID");
		}
		
//		if(d!=null&&d.size()>0){
//			dictionaries_id  = d.get(0).getStr("DICTIONARIES_ID");
//		}
		List<Product> s_products = null;
		List<Product> h_products = null;
		JSONObject json = new JSONObject();
		if(getPara("type")!=null&&getPara("type").equals("1")){ // 话费
			s_products =Product.dao.find("select p.product_id,p.product_name,p.settlement_price,t.chnnl_type,t.region,p.specify_name,p.product_price, p.is_recommend from weixin_product p \n" +
					" left join weixin_product_type t \n" +
					" on p.pid = t.id \n" +
					" where t.chnnl_type="+cardtype+" and t.region=1 and t.type=1 and p.dr=0 and "+
					" t.dictionaries_id='"+dictionaries_id+"' and status=0 order by p.product_price asc "); 
			json.put("s_products", JsonKit.toJson(s_products));
		}
		else{
			if(getPara("t")!=null&&getPara("t").equals("2")){
				renderJson(getnewProduct(flag7,open_id));
				return;
			}
			//省内流量
			s_products = Product.dao.find("select p.product_id,p.product_name,p.settlement_price,t.chnnl_type,t.region,p.specify_name,p.product_price, p.is_recommend from weixin_product p \n" +
					" left join weixin_product_type t \n" +
					" on p.pid = t.id \n" +
					" where t.chnnl_type="+cardtype+" and t.region=1  and if(type is null,0,type) !=1   and p.dr=0 and "+
					" t.dictionaries_id='"+dictionaries_id+"' and status=0 order by p.product_price asc ");
			if(s_products==null||s_products.size()<=0){
				s_products = Product.dao.find("select p.product_id,p.product_name,p.settlement_price,t.chnnl_type,t.region,p.specify_name,p.product_price, p.is_recommend from weixin_product p \n" +
						" left join weixin_product_type t \n" +
						" on p.pid = t.id \n" +
						" where t.chnnl_type="+cardtype+" and t.region=1  and if(type is null,0,type) !=1   and p.dr=0 and "+
						" t.dictionaries_id='"+s_dictionaries_id+"' and status=0 order by p.product_price asc ");
			}
			
			//国内流量
			 h_products = Product.dao.find("select  p.product_id,p.product_name,p.settlement_price,t.chnnl_type,t.region,p.specify_name,p.product_price, p.is_recommend  from weixin_product p \n" +
					" left join weixin_product_type t \n" +
					" on p.pid = t.id \n" +
					" where t.chnnl_type="+cardtype+" and t.region=2 and "+
					" t.dictionaries_id='"+s_dictionaries_id+"' and status=0  and if(type is null,0,type) !=1 and  p.dr=0 order by p.product_price asc ");
			
			if(h_products==null||h_products.size()<=0){
			//全国流量
			 h_products = Product.dao.find("select  p.product_id,p.product_name,p.settlement_price,t.chnnl_type,t.region,p.specify_name,p.product_price, p.is_recommend from weixin_product p \n" +
					" left join weixin_product_type t \n" +
					" on p.pid = t.id \n" +
					" where t.chnnl_type="+cardtype+" and t.region=3 and status=0 and if(type is null,0,type) !=1  and  p.dr=0  order by p.product_price asc ");
			}
		}

		json.put("s_products", JsonKit.toJson(s_products));
		json.put("g_products", JsonKit.toJson(h_products));
//		json.put("h_products", JsonKit.toJson(g_products));
		
		json.put("province", province);
		json.put("city", city);
		json.put("card_type", card_type);
		Wallet m = Wallet.dao.findFirst(" select * from weixin_wallet where open_id='"+open_id+"'");
		if(m!=null)
			json.put("amount", m.getDouble("amount"));
		renderJson(json.toString());
	}

	  //获取归属地
    public static String getAttribution(String phone){
		String flag7 = phone.substring(0, 7);
		Attribution a = Attribution.dao.findFirst("select * from mobile_attribution where number_section1 ='"+flag7+"'");
		if(a!=null){
			return (a.get("attribution")==null?"":a.get("attribution").toString())+"   "+(a.get("card_type")==null?"":a.get("card_type").toString());
		}
		 return "";
	}
    
    /**
     * @return 返回系统默认套餐
     */
    public static String getDefault(){
    	JSONObject json = new JSONObject();
    	String sql = "SELECT p.product_name, p.settlement_price, t.chnnl_type, t.region, p.specify_name, p.product_price, p.is_recommend FROM weixin_product p LEFT JOIN weixin_product_type t ON p.pid = t.id WHERE t.region IN (?) AND IF (type IS NULL, 0, type) != 1 AND p.dr = 0 AND p.is_default = 1 AND STATUS = 0 ORDER BY chnnl_type, p.product_price ASC";
    	List<Product> s_products = Product.dao.find(sql,1);
    	List<Product> g_products = Product.dao.find(sql,2);
    	if(g_products==null||g_products.size() <= 0)
    		g_products = Product.dao.find(sql,3);
		json.put("s_products", JsonKit.toJson(s_products));
		json.put("g_products", JsonKit.toJson(g_products));
    	return json.toString();
    }
    
    
    @SuppressWarnings("unchecked")
    public static String newgetDefault(){
    	JSONObject objson = new JSONObject();
    	JSONArray json = new JSONArray();
    	json.add(getChnnlProduct(1,null,null));
    	json.add(getChnnlProduct(2,null,null));
    	json.add(getChnnlProduct(3,null,null));
    	objson.put("list", json.toString());
    	return objson.toString();
    }
    
    public static JSONObject getChnnlProduct(int type,String dictionaries_id,String s_dictionaries_id){
    	JSONArray json = new JSONArray();
    	List<Product> s_products = null;
    	JSONObject pjs = null;
    	List<ProductType> productTypes = ProductType.dao.find("select id,type_name from weixin_product_type where is_show_wx =1 and chnnl_type =? and dr =0 order by type_code desc",type);
    	for (ProductType productType : productTypes) {
    		pjs = new JSONObject();
			pjs.put("typename", productType.get("type_name"));
			//查询分类下的产品
			if(dictionaries_id==null&&s_dictionaries_id==null)
				s_products = Product.dao.find("select p.product_id,p.product_name,p.settlement_price,t.chnnl_type,t.region,p.specify_name,p.product_price, p.is_recommend from weixin_product p \n" +
						" left join weixin_product_type t \n" +
						" on p.pid = t.id \n" +
						" where if(type is null,0,type) !=1  and p.dr=0 and status=0 and is_default =1 and t.parent_id=? order by p.product_price asc",productType.getStr("id"));
			else
			{	
				// 如果是移动或者电信要 不显示全国流量在国内流量已存在的流量包
				if((type==1 || type==3) && productType.getStr("type_name").indexOf("全国流量") >-1)
				{
					//全国
					ProductType pt = ProductType.dao.findFirst("select id from weixin_product_type where is_show_wx =1 and chnnl_type =? and dr =0 and type_name like '%国内流量%' order by type_code desc;",type);
					s_products = Product.dao.find("select p.product_id,p.product_name,p.settlement_price,t.chnnl_type,t.region,p.specify_name,p.product_price, p.is_recommend from weixin_product p \n" +
							" left join weixin_product_type t \n" +
							" on p.pid = t.id \n" +
							" where if(type is null,0,type) !=1 and p.dr=0 and "+
							" t.parent_id=? and status=0  and NOT EXISTS(select product_code from weixin_product a left join weixin_product_type b on a.pid = b.id where if(type is null,0,type) !=1  and a.dr=0 and status=0  and chnnl_type = ? and b.region=2 and b.parent_id=? and dictionaries_id =?) order by p.product_price asc ",productType.getStr("id"),type,pt.getStr("id"),s_dictionaries_id);
				}else{
					//省内
					s_products = Product.dao.find("select p.product_id,p.product_name,p.settlement_price,t.chnnl_type,t.region,p.specify_name,p.product_price, p.is_recommend from weixin_product p \n" +
							" left join weixin_product_type t \n" +
							" on p.pid = t.id \n" +
							" where if(type is null,0,type) !=1  and p.dr=0 and "+
							" t.dictionaries_id=? and t.parent_id=? and status=0  order by p.product_price asc ",dictionaries_id,productType.getStr("id"));
					
					//国内
					if(s_products==null||s_products.size()<=0){
						s_products = Product.dao.find("select p.product_id,p.product_name,p.settlement_price,t.chnnl_type,t.region,p.specify_name,p.product_price, p.is_recommend from weixin_product p \n" +
								" left join weixin_product_type t \n" +
								" on p.pid = t.id \n" +
								" where if(type is null,0,type) !=1  and p.dr=0 and "+
								" t.dictionaries_id=? and t.parent_id=? and status=0 order by p.product_price asc ",s_dictionaries_id,productType.getStr("id"));
					}
					//全国
					if(s_products==null||s_products.size()<=0){
						s_products = Product.dao.find("select p.product_id,p.product_name,p.settlement_price,t.chnnl_type,t.region,p.specify_name,p.product_price, p.is_recommend from weixin_product p \n" +
								" left join weixin_product_type t \n" +
								" on p.pid = t.id \n" +
								" where if(type is null,0,type) !=1 and t.region=3 and p.dr=0 "+
								" and t.parent_id=? and status=0 order by p.product_price asc ",productType.getStr("id"));
					}
					
				}
			}
			pjs.put("products", JsonKit.toJson(s_products));
			json.add(pjs);
		}
    	
    	JSONObject str = new JSONObject();
    	String strname = type==1?"中国移动":(type==2?"中国联通":"中国电信");
    	str.put("title", strname);
    	str.put("content", json.toString());
    	return str;
    }
    
    
    @SuppressWarnings("unchecked")
    public static String getnewProduct(String flag7,String open_id){
    	//1查询手机所在地区
		Attribution att = Attribution.dao.findFirst("select * from mobile_attribution where number_section1 ='"+flag7+"'");
		if(att==null){
			return "";
		}
		
		String province = att.getStr("province");
		String city = att.getStr("city");
		String card_type = att.getStr("card_type");
		
		int cardtype = WeixinConst.getCardType(att.getStr("card_type"));
		//先用地市找商品,找不到再用省份  
		List<Dictionaries> d = Dictionaries.dao.find("select * from sys_dictionaries where bianma like '003%' and bianma not in ('003')"
				+ "  and name='"+city+"' ");
		List<Dictionaries> s = Dictionaries.dao.find("select * from sys_dictionaries where bianma like '003%' and bianma not in ('003')"
				+ "  and name='"+province+"' ");
		//如有多条,再进行折扣排序处理; 
	
		String dictionaries_id = null;
		if(d!=null&&d.size()>0){
			dictionaries_id  =d.get(0).getStr("DICTIONARIES_ID");
			
		}
		String s_dictionaries_id = null;
		if(s!=null&&s.size()>0){
			s_dictionaries_id  =s.get(0).getStr("DICTIONARIES_ID");
		}
		JSONArray jsonarr = new JSONArray();
		jsonarr.add(getChnnlProduct(cardtype,dictionaries_id,s_dictionaries_id));
		
		JSONObject json = new JSONObject();
		//首先查询要显示的产品类型
		json.put("list",jsonarr.toString());
		json.put("province", province);
		json.put("city", city);
		json.put("card_type", card_type);
		Wallet m = Wallet.dao.findFirst(" select * from weixin_wallet where open_id=?",open_id);
		if(m!=null)
			json.put("amount", m.getDouble("amount"));
		return json.toString();
    }
    
}
