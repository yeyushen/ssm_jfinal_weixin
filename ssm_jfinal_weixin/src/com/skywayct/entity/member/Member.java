package com.skywayct.entity.member;

import java.util.List;

public class Member {
	

	/**
	 * 
	* 类名称：组织机构
	* 类描述： 
	* @author FH QQ []
	* 作者单位： 
	* 联系方式：
	* 修改时间：2015年12月16日
	* @version 2.0
	 */

		private String openid;				//微信openid
		private String nick_name;			//会员昵称
		private String name;				//姓名	
		private	String create_date;			//加入时间
		private String ordinarymember_id;	//主键		
		private String parent_id;			//父节点id
		
		private String target;
		private Member member;
		private List<Member> subMember;//根据父节点获取所有子节点部门
		private boolean hasMember = false;
		private String treeurl;
		
		private String photo;				//头像
//		private String sex;					//性别
//		private String phone;				//手机号码
//		private String mobile;				//电话
//		private String email;	
//		private String canceled;			//激活状态
//		private String level_id;	
//		private String dr;					//主键private String ordinarymember_id;	//主键
//		private String ts;	
		
		
		public String getOpenid() {
			return openid;
		}
		public String getPhoto() {
			return photo;
		}
		public void setPhoto(String photo) {
			this.photo = photo;
		}
		public void setOpenid(String openid) {
			this.openid = openid;
		}
		public String getNick_name() {
			return nick_name;
		}
		public void setNick_name(String nick_name) {
			this.nick_name = nick_name;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCreate_date() {
			return create_date;
		}
		public void setCreate_date(String create_date) {
			this.create_date = create_date;
		}
		public String getOrdinarymember_id() {
			return ordinarymember_id;
		}
		public void setOrdinarymember_id(String ordinarymember_id) {
			this.ordinarymember_id = ordinarymember_id;
		}
		public String getParent_id() {
			return parent_id;
		}
		public void setParent_id(String parent_id) {
			this.parent_id = parent_id;
		}
		public String getTarget() {
			return target;
		}
		public void setTarget(String target) {
			this.target = target;
		}
		public Member getMember() {
			return member;
		}
		public void setMember(Member member) {
			this.member = member;
		}
		public List<Member> getSubMember() {
			return subMember;
		}
		public void setSubMember(List<Member> subMember) {
			this.subMember = subMember;
		}
		public boolean isHasMember() {
			return hasMember;
		}
		public void setHasMember(boolean hasMember) {
			this.hasMember = hasMember;
		}
		public String getTreeurl() {
			return treeurl;
		}
		public void setTreeurl(String treeurl) {
			this.treeurl = treeurl;
		}
		


		
		
		
		
}


