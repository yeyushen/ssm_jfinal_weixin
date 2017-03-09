package com.skywayct.entity.weixin;

import java.util.List;

public class OrdinaryMember {

	private String openid;
	private String nick_name;
	private String name;
	private String sex;
	private String create_date;
	private String parent_id;
	private String dr;
	private String target;
	private OrdinaryMember member;

	private List<OrdinaryMember> subMember;
	private boolean hasMember = false;
	private String treeurl;

	public String getOpenid() {
		return openid;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public OrdinaryMember getMember() {
		return member;
	}

	public void setMember(OrdinaryMember member) {
		this.member = member;
	}

	public List<OrdinaryMember> getSubMember() {
		return subMember;
	}

	public void setSubMember(List<OrdinaryMember> subMember) {
		this.subMember = subMember;
	}

	public String getTreeurl() {
		return treeurl;
	}

	public void setTreeurl(String treeurl) {
		this.treeurl = treeurl;
	}

	public boolean isHasMember() {
		return hasMember;
	}

	public void setHasMember(boolean hasMember) {
		this.hasMember = hasMember;
	}

}

