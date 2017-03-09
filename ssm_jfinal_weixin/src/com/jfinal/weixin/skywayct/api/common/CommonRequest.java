package com.jfinal.weixin.skywayct.api.common;

/**
 * @author CZJIE
 * @version V1.0
 * @Title: CommonRequest.java
 * @date 2015/9/1 21:07
 * @Description:
 */
public class CommonRequest {
    private String appid;
    private String serial;
    private String access_token;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    public String getAccess_token() {
        return access_token;
    }

    public Boolean checkSerial(){
        if(this.serial==null || "".equals(this.serial)){
            return false;
        }
        return true;
    }

    public Boolean checkAppid(){
        if(this.appid==null || "".equals(this.appid)){
            return false;
        }
        return true;
    }

    public Boolean checkAccessToken(){
        if(this.access_token==null || "".equals(this.access_token)){
            return false;
        }
        return true;
    }

}
