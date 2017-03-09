package com.jfinal.weixin.skywayct.api.common;

import com.jfinal.weixin.skywayct.api.returninfo.ReturnBean;
import com.jfinal.weixin.skywayct.api.returninfo.ReturnHandler;


/**
 * @author 
 * @version V1.0
 * @Title: CommonResponse.java
 * @date 
 * @Description:
 */
public class CommonResponse {

    private Integer code;
    private String msg;

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


    public void setReturn(Integer error){
        ReturnBean r = ReturnHandler.get(error);
        this.setCode(r.getCode());
        this.setMsg(r.getDetail());
    }
}
