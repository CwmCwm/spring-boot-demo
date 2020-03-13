package com.demo.websocket.serverendpoint;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @ServerEndpoint("/im/{userId}")  就类似于Controller的路径匹配
 * 这里规范websocket的消息为 json格式的字符串
 *
 * websocket就四个事件，具体见下面代码
 * IMServerEndpoint 是多例模式，有连接时才实例化对象，而且有多个连接就有多个对象。
 * */
@ServerEndpoint("/im/{userId}")
@Component
public class IMServerEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(IMServerEndpoint.class);

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;

    /**concurrent包的线程安全Set，用来存放每个客户端对应的 IMServerEndpoint对象 */
    private static ConcurrentHashMap<String, IMServerEndpoint> iMServerEndpointMap = new ConcurrentHashMap<>();

    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;

    /**接收userId*/
    private String userId = "";




    /**
     * websocket的open事件/打开事件
     * 连接建立成功调用的方法
     * */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        if (iMServerEndpointMap.containsKey(userId)) {
            iMServerEndpointMap.remove(userId);
            iMServerEndpointMap.put(userId, this);
        } else {
            iMServerEndpointMap.put(userId,this);
            addOnlineCount();
        }

        logger.info("用户连接:" + userId + ",当前在线人数为:" + getOnlineCount());

        try {
            session.getBasicRemote().sendText("连接成功");
        } catch (IOException e) {
            logger.error("用户:" + userId + ",网络异常!!!!!!");
        }
    }

    /**
     * websocket的close事件/关闭事件
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(iMServerEndpointMap.containsKey(userId)){
            iMServerEndpointMap.remove(userId);
            subOnlineCount();
        }
        logger.info("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
    }

    /**
     * websocket的message事件/消息事件
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("用户消息:" + userId + ",报文:" + message);
        if(!StringUtils.isEmpty(message)){
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromUserId", this.userId);
                String toUserId = jsonObject.getString("toUserId");
                //传送给对应toUserId用户的websocket
                if (!StringUtils.isEmpty(toUserId) && iMServerEndpointMap.containsKey(toUserId)) {
                    iMServerEndpointMap.get(toUserId).session.getBasicRemote().sendText(message);
                } else {
                    logger.error("请求的userId:" + toUserId + "不在该服务器上");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * websocket的error事件/错误事件
     *
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("用户错误:"+this.userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }



    /**
     * 提供静态方法供 Controller，Service调用
     * 发送自定义消息
     * 示例
     * message={"fromUserId":"cwm", "toUserId":"hwy", "contentText":"ccc"}
     * toUserId="hwy"
     * */
    public static void sendInfo(String message, String toUserId) throws IOException {
        logger.info("发送消息到:" + toUserId + "，报文:"+message);
        if (!StringUtils.isEmpty(toUserId) && iMServerEndpointMap.containsKey(toUserId)) {
            iMServerEndpointMap.get(toUserId).session.getBasicRemote().sendText(message);
        } else {
            logger.error("用户" + toUserId + ",不在线！");
        }
    }

    //返回在线数
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    //在线数+1
    public static synchronized void addOnlineCount() {
        IMServerEndpoint.onlineCount++;
    }

    //在线数-1
    public static synchronized void subOnlineCount() {
        IMServerEndpoint.onlineCount--;
    }

}
