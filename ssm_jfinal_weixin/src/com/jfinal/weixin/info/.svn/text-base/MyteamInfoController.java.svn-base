package com.jfinal.weixin.info;

import net.sf.json.JSONObject;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.weixin.model.Member;

public class MyteamInfoController extends Controller{
	
	public void index() throws Exception {
		JSONObject team_json = new JSONObject();
		String open_id = getSessionAttr("open_id");
		if (StrKit.isBlank(open_id)) {
			team_json.put("code", "-1");
			team_json.put("msg", "页面请求超时，请重新打开！");
		}else{
			team_json.put("code", "0");
			int pageNumber = getParaToInt("page") ==null ? 1:getParaToInt("page");
			int pageSize = getParaToInt("size") ==null ? 10:getParaToInt("size");
			open_id = getPara("open_id");
			if("".equals(open_id)) open_id = getSessionAttr("open_id");
			// 旧版查询(实时数据)
//			StringBuilder builder = new StringBuilder(" from weixin_member w where 1=1 and w.parent_id ='"+ open_id +"' and dr =0");
//			Page<Member> page = Member.dao.paginate(pageNumber, pageSize, "select w.openid,w.nick_name,w.parent_id,w.phone,(select count(m.openid) from weixin_member m where m.parent_id=w.openid and m.dr=0)  as lv1,(select count(1) from weixin_member m where exists(select 1 from weixin_member a where a.parent_id = w.openid and a.openid = m.parent_id and a.dr=0) and m.dr=0) as lv2,(select count(1) from weixin_member m where exists(select 1 from weixin_member a where a.parent_id in (select openid from weixin_member where parent_id = w.openid and dr=0) and a.openid = m.parent_id and a.dr=0)) as lv3 ", builder.toString());
			// 新版查询 (定时更新数据)
			String nick_name = getPara("nick");
			if(StrKit.isBlank(nick_name)) nick_name ="";
			StringBuilder builder = new StringBuilder("FROM weixin_member w  left join weixin_member_statistics m on w.openid = m.openid where w.dr=0 and w.parent_id ='"+open_id+"' and nick_name like '%"+nick_name+"%' order by lv1 desc,lv2 desc,lv3 desc,w.create_date desc ");
			Page<Member> page = Member.dao.paginate(pageNumber, pageSize, "SELECT w.openid,w.nick_name,w.parent_id,m.lv1,m.lv2,m.lv3 ", builder.toString());
			if(!StrKit.isBlank(getPara("js")) && "1".equals(getPara("js"))){
				Member count = Member.dao.findFirst("select (select count(m.openid) from weixin_member m where m.parent_id=w.openid and m.dr=0)  as lv1,(select count(1) from weixin_member m where exists(select 1 from weixin_member a where a.parent_id = w.openid and a.openid = m.parent_id and a.dr=0) and m.dr=0) as lv2,(select count(1) from weixin_member m where exists(select 1 from weixin_member a where a.parent_id in (select openid from weixin_member where parent_id = w.openid ) and a.openid = m.parent_id) and dr =0) as lv3 from weixin_member w where 1=1 and w.dr =0 and w.openid ='"+ open_id +"'");
				team_json.put("lv1", count.getLong("lv1"));
				team_json.put("lv2", count.getLong("lv2"));
				team_json.put("lv3", count.getLong("lv3"));
			}
			team_json.put("myteams", JsonKit.toJson(page));
		}
		renderJson(team_json.toString());
	}
}
