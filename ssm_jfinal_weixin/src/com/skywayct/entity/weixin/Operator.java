package com.skywayct.entity.weixin;

import java.util.List;

public class Operator {
	private int region;
	private String id;
	private String type_code;
	private String type_name;
	private String parent_id;
	private String chnnl_type;
	private int dr;
	private String target;
	private boolean hasOperator = false;
	private Operator operator;
	private List<Operator> subOperator;
	private String treeurl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType_code() {
		return type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getChnnl_type() {
		return chnnl_type;
	}

	public void setChnnl_type(String chnnl_type) {
		this.chnnl_type = chnnl_type;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public List<Operator> getSubOperator() {
		return subOperator;
	}

	public void setSubOperator(List<Operator> subOperator) {
		this.subOperator = subOperator;
	}

	public String getTreeurl() {
		return treeurl;
	}

	public void setTreeurl(String treeurl) {
		this.treeurl = treeurl;
	}

	public int getDr() {
		return dr;
	}

	public void setDr(int dr) {
		this.dr = dr;
	}

	public boolean isHasOperator() {
		return hasOperator;
	}

	public void setHasOperator(boolean hasOperator) {
		this.hasOperator = hasOperator;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	
}
