# docker安装elk

## 本项目知识点
1. elk是啥  
2. docker安装ElasticSearch  
3. docker安装kibana  
4. docker安装logstash  


## 内容
### 1. elk是啥  
e  是 ElasticSearch  
l  是 logstash   
k  是 kibana   




### 2. docker安装ElasticSearch  
这里使用  elasticsearch:7.8.0  这个版本镜像(docker官方镜像)   
拉取镜像  `docker pull elasticsearch:7.8.0`
启动容器  `docker run --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" --network diyNetwork -d elasticsearch:7.8.0`
开启防火墙端口  `firewall-cmd --add-port=9200/tcp --permanent`  和  `firewall-cmd --reload`



### 3. docker安装kibana  
拉取镜像  `docker pull kibana:7.8.0`  
启动容器  `docker run --name kibana -p 5601:5601 --network diyNetwork -d kibana:7.8.0`  
开启防火墙端口  `firewall-cmd --add-port=5601/tcp --permanent`  和  `firewall-cmd --reload`







