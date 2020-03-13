# spring-boot-websocket


## 本项目知识点
1. websocket是什么  
2. 演示示例  



## 内容 
### 1. websocket是什么  
参考文章  https://blog.csdn.net/moshowgame/article/details/80275084  
具体百度websocket协议  
懂计算机网络就明白下面是什么意思？   
websocket是一种协议，那websocket协议在计算机网络哪一层啊=》应用层  
http是一种协议，http协议也是计算机网络的应用层  
| http协议| websocket协议 |
|----|----|
| 通信只能由客户端发起，HTTP 协议做不到服务器主动向客户端推送信息 | 它实现了浏览器与服务器全双工(full-duplex)通信——允许服务器主动发送信息给客户端 |
根据这些特点，就明白websocket协议的使用场景  
在没有websocket协议前，浏览器实现股票数据展示，股票信息实时展示，怎么办=》浏览器JavaScript定时轮询/发请求查询股票数据  
自己想想有场景，使用轮询方式http协议 和 websocket协议 对带宽，请求数的影响  
场景一：浏览器实现IM即时通信  
场景二：浏览器实现股票数据展示  



### 2. 演示示例  
场景一：浏览器实现IM即时通信，单聊   
见代码  
static/im.html   
com.demo.websocket.serverendpoint.IMServerEndpoint  
com.demo.websocket.controller.IMController  
com.demo.websocket.configuration.WebsocketConfiguration  
知道websocket的api后，群聊就可以实现了    




