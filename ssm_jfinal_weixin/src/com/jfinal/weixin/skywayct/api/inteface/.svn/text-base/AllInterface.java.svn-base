package com.jfinal.weixin.skywayct.api.inteface;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.weixin.skywayct.api.common.FlowServerHandler;

/**
 * @author CZJIE
 * @version V1.0
 * @Title: AllInterface.java
 * @date 2015/9/21 21:09
 * @Description:
 */
public class AllInterface {

    private static Map<Integer, FlowServerHandler> allhandler = null;

    public static Map<Integer, FlowServerHandler> getAllhandler() {
        return allhandler;
    }

    public static void put(Integer interfaceId, FlowServerHandler handler){
        if(allhandler==null){
            allhandler = new HashMap<Integer, FlowServerHandler>();
        }
        allhandler.remove(interfaceId);
        allhandler.put(interfaceId, handler);
    }

    public static FlowServerHandler get(Integer interfaceId){
        if(allhandler==null){
            return null;
        }else {
            return allhandler.get(interfaceId);
        }
    }
}
