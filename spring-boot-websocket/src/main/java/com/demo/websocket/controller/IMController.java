package com.demo.websocket.controller;


import com.alibaba.fastjson.JSON;
import com.demo.websocket.serverendpoint.IMServerEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
public class IMController {


    /**
     * 这里场景是用户 hwy 通过im.html连接到这个websocket服务器
     * 但用户 cwm 没有通过im.html连接到这个websocket服务器， cwn用户通过该http接口发送消息给 hwy
     * 其实就是多端即时通信（网页端 和 APP端）
     *
     * 示例
     * 127.0.0.1:8080/imController
     * {"fromUserId":"cwm", "toUserId":"hwy", "contentText":"ccc"}
     * */
    @RequestMapping(value = "/imController", method = RequestMethod.POST)
    @ResponseBody
    public Object imController(@RequestBody Map<String, Object> messageMap) throws Exception {
        String toUserId = messageMap.get("toUserId").toString();
        String jsonMessage = JSON.toJSONString(messageMap);
        IMServerEndpoint.sendInfo(jsonMessage, toUserId);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success/IM发送成功");
        return responseMap;
    }


}
