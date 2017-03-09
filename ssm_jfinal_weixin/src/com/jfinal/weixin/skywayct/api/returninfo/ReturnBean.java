package com.jfinal.weixin.skywayct.api.returninfo;

/**
 * @author CZJIE
 * @version V1.0
 * @Title: ReturnBean.java
 * @date 2015/9/8 11:07
 * @Description:
 */
public class ReturnBean {
    private Integer code;
    private String msg;
    private String detail;

    public ReturnBean(Integer code,String msg,String detail){
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
