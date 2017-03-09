package com.jfinal.weixin.skywayct.api.common;

import com.jfinal.kit.StrKit;

/**
 * @author 
 * @version V1.0
 * @Title: OrderRequest.java
 * @date 
 * @Description:
 */
public class OrderRequest extends  CommonRequest{

    private String product_id;
    private String phone;
    private String openid;
    private String effective_type;
    private String settlement_method;
    
    
    

    public String getSettlement_method() {
		return settlement_method;
	}

	public void setSettlement_method(String settlement_method) {
		this.settlement_method = settlement_method;
	}

	public String getEffective_type() {
        return effective_type;
    }

    public void setEffective_type(String effective_type) {
        this.effective_type = effective_type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Boolean checkProductId(){
        if(!StrKit.isBlank(product_id)){
            try {
                int i = new Integer(product_id);
            }catch (Exception e){
                return true;
            }
        }
        return false;
    }

    public Boolean checkPhone(){
        if(!StrKit.isBlank(phone)){
            try {
                Double i = new Double(phone);
            }catch (Exception e){
                return true;
            }
        }
        return false;
    }

    public Boolean checkEffectiveType(){
        if(!StrKit.isBlank(effective_type)){
            try {
                int i = new Integer(effective_type);
            }catch (Exception e){
                return true;
            }
        }
        return false;
    }
}
