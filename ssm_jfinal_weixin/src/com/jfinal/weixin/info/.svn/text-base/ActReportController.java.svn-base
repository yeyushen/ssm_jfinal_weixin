package com.jfinal.weixin.info;

import net.sf.json.JSONObject;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.weixin.model.Member;

public class ActReportController extends Controller{
	public void index() throws Exception {
		JSONObject top_json = new JSONObject();
		String open_id = getSessionAttr("open_id");
		if (StrKit.isBlank(open_id)) {
			top_json.put("code", "-1");
			top_json.put("msg", "页面请求超时，请重新打开！");
		}else{
			top_json.put("code", "0");
			int pageNumber = getParaToInt("page") ==null ? 1:getParaToInt("page");
			int pageSize = getParaToInt("size") ==null ? 20:getParaToInt("size");
			StringBuilder builder = new StringBuilder(" from weixin_member a ,v_activity_report b where a.openid =b.openid and cout >0");
			Page<Member> page = Member.dao.paginate(pageNumber, pageSize, "select (SELECT COUNT(1)+1 FROM v_activity_report A WHERE A.cout > B.cout) top,b.* ", builder.toString());
			top_json.put("myteams", JsonKit.toJson(page));
			String sql = "SELECT w.id,w.openid,w.nick_name,w.create_date,b.top,(c.cout - (select count(m.openid) from v_activity_weixin_member m where m.parent_id=w.openid and m.dr=0)) as less FROM weixin_member w \n" +
			"left join (select b.* ,(SELECT COUNT(1)+1 FROM v_activity_report A WHERE A.cout > B.cout) top from v_activity_report b) b on b.openid = w.openid\n" +
			"left join (select * from (select b.* ,(SELECT COUNT(1)+1 FROM v_activity_report A WHERE A.cout > B.cout) as top from v_activity_report b) d where top <=100 order by cout asc LIMIT 1) c on 1=1 WHERE w.openid = ? AND dr = 0 order by top asc";
			Member ow = Member.dao.findFirst(sql,open_id);
			if(ow!=null){
				top_json.put("openid", ow.getStr("openid"));
				top_json.put("top", ow.getLong("top"));
				top_json.put("less", ow.getLong("less"));
			}
		}
		renderJson(top_json.toString());
	}
}
