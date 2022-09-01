# 团子博客

<p align=center>
    团子博客
</p>
<p align=center>
   团子博客，一个基于微服务架构的前后端分离博客系统
</p>
<p align="center">
<a target="_blank" href="https://gitee.com/moxi159753/mogu_blog_v2">
    	<img src="https://img.shields.io/hexpm/l/plug.svg" ></img>
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
        <img src="https://img.shields.io/badge/nodejs-14.x-green" ></img>
        <img src="https://img.shields.io/badge/springboot-2.2.2.RELEASE-green" ></img>
        <img src="https://img.shields.io/badge/SpringCloud-Hoxton.RELEASE-brightgreen" ></img>
        <img src="https://img.shields.io/badge/vue-2.5.17-green" ></img>
        <img src="https://img.shields.io/badge/swagger-3.0.0-brightgreen" ></img>
        <img src="https://img.shields.io/badge/mybatis--plus-3.1.2-green" ></img>
        <img src="https://gitee.com/moxi159753/mogu_blog_v2/badge/star.svg?theme=dark" ></img>
        <img src="https://gitee.com/moxi159753/mogu_blog_v2/badge/fork.svg?theme=dark" ></img>
</a></p>
##  前言
欢迎各位小伙伴关注我，和我一起共同学习，同时我也希望各位小伙伴能够给**团子博客**项目多多 **Star** 支持，您的**点赞**就是我维护的动力！


项目根据蘑菇博客修改而来，借鉴了若依系统，halo系统，把各系统比较好的想法加入到团子博客里面

## 项目介绍

团子博客( **TuanZiBlog** )，一个**基于微服务架构的前后端分离博客系统**。**Web** 端使用 **Vue** + **ElementUi** , 移动端使用 **uniapp** 和 **ColorUI**
。后端使用 **SpringCloud** + **SpringBoot** + **Mybatis-plus**进行开发，使用 **Jwt** + **SpringSecurity** 做登录验证和权限校验，使用 **
ElasticSearch** 和 **Solr** 作为全文检索服务，使用 **Github Actions**完成博客的持续集成，使用 **ElasticStack** 收集博客日志，文件支持**上传本地**、**七牛云** 和 **
Minio**.

- 团子博客大部分功能是我个人进行开发的，因能力有限，其中很多技术都是一边学习一边使用的，可以说团子博客也是一个我用来熟悉技术的项目，所以很多地方可能考虑不周，故有能改正的地方，还请各位老哥能够指出~

## 运行配置

团子博客使用了一些监控的 **SpringCloud** 组件，但是并不一定都需要部署，必须启动的服务包含

`nacos`，`nginx`，`rabbitmq`， `redis`，`mysql`，`mogu-gateway`，`mogu-sms`，`mogu-picture`， `mogu-web`, `mogu-admin`

其它的服务都可以不启动，也不影正常使用，可以根据自身服务器配置来启动

推荐配置：2核4G 【博主目前配置】

## 项目中初始用户和密码

- **后台登录**：用户：admin，密码：mogu2018
- 前台登录：用户：mogu2018，密码：mogu2018
- **Mysql**：用户：root，密码：mogu2018
- **Redis**：密码：mogu2018
- **远程SSH初始密码**：用户：root，密码：mogu2018
- **RabbitMQ管理页面：** 用户：admin，密码：mogu2018
- **Nacos管理页面：** 用户：nacos，密码：nacos
- **Sentinel管理页面：** 用户：sentinel，密码：sentinel
- **蘑菇博客监控页面**：用户：user，密码：password123
- **Druid初始密码：** 用户：admin，密码：123456

## 项目特点

- 友好的代码结构及注释，便于阅读及二次开发
- 实现前后端分离，通过 **Json** 进行数据交互，前端再也不用关注后端技术
- 页面交互使用 **Vue2.x**，极大的提高了开发效率。
- 引入**Swagger** 文档支持，方便编写 **API** 接口文档。
- 引入**RabbitMQ** 消息队列，用于邮件发送、更新 **Redis** 和 **Solr**
- 引入**JustAuth** 第三方登录开源库，支持 **Gitee**、**Github** 账号登录。
- 引入**ElasticSearch** 和 **Solr** 作为全文检索服务，并支持可插拔配置
- 引入**Github Actions** 工作流，完成蘑菇博客的持续集成、持续部署。
- 引入七牛云对象存储，同时支持本地文件存储
- 引入 **RBAC** 权限管理设计，灵活的权限控制，按钮级别的细粒度权限控制，满足绝大部分的权限需求
- 引入 **Zipkin** 链路追踪，聚合各业务系统调用延迟数据，可以一眼看出延迟高的服务
- 采用**自定义参数校验注解**，轻松实现后端参数校验
- 采用 **AOP** + 自定义注解 + **Redis** 实现限制IP接口访问次数
- 采用自研的评论模块，实现评论邮件通知
- 采用 **Nacos** 作为服务发现和配置中心，轻松完成项目的配置的维护
- 采用 **Sentinel** 流量控制框架，通过配置再也不怕网站被爆破
- 采用[uniapp](https://uniapp.dcloud.io/) 和[ColorUi](https://github.com/weilanwl/ColorUI) 完成蘑菇博客的移动端门户页面搭建
- 支持多种文本编辑器，**Markdown** 编辑器([Vditor](https://github.com/Vanessa219/vditor))
  和富文本编辑器([CKEditor](https://github.com/ckeditor/ckeditor4))随心切换
- 采用 **ElasticStack**【**ElasticSearch** + **Beats** + **Kibana** + **Logstash**】[搭建蘑菇博客日志收集](http://moguit.cn/info/436)
- 采用 **Docker Compose** 完成容器编排，**Portainer** 实现容器可视化，支持[一键部署线上环境](http://www.moguit.cn/info/565)

## 项目目录

- MoguBlog 是一款基于最新技术开发的多人在线、简洁的博客系统。
- mogu_admin: 提供admin端API接口服务；
- mogu_web：提供web端API接口服务；
- mogu_eureka： 服务发现和注册【注: Nacos分支没有该目录，用Nacos作为服务发现组件】
- mogu_picture： 图片服务，用于图片上传和下载；
- mogu_sms：消息服务，用于更新ElasticSearch、Solr索引、邮件和短信发送
- mogu_monitor：监控服务，集成SpringBootAdmin用于管理和监控SpringBoot应用程序
- mogu_spider：爬虫服务`（目前还未完善）`
- mogu_gateway：网关服务`（目前还未完善）`
- mogu_zipkin：链路追踪服务，`目前使用java -jar的方式启动`
- mogu_search：搜索服务，ElasticSearch和Solr作为全文检索工具，[支持可插拔配置](http://www.moguit.cn/info/119)，默认使用SQL搜索
- mogu_commons：公共模块，主要用于存放Entity实体类、Feign远程调用接口、以及公共config配置
- mogu_utils: 是常用工具类；
- mogu_xo: 是存放 VO、Service，Dao层的
- mogu_base: 是一些Base基类
- doc: 是蘑菇博客的一些文档和数据库文件
- vue_mogu_admin：VUE的后台管理页面
- vue_mogu_web：VUE的门户网站
- uniapp_mogu_web：基于uniapp 和 colorUi 的蘑菇博客移动端门户页面（Nacos分支）
- nuxt_mogu_web：Nuxt的门户网站，主要用于支持SEO搜索引擎优化`（目前还未完善）`

## 技术选型

### 系统架构图

> 团子博客系统架构图，使用 [Processon](https://www.processon.com/i/5e380df1e4b05b335ffa81e9) 在线绘制

### 后端技术

|      技术      |           说明            |                             官网                             |
| :------------: | :-----------------------: | :----------------------------------------------------------: |
|   SpringBoot   |          MVC框架          | [ https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
|  SpringCloud   |        微服务框架         |           https://spring.io/projects/spring-cloud/           |
| SpringSecurity |      认证和授权框架       |          https://spring.io/projects/spring-security          |
|  MyBatis-Plus  |          ORM框架          |                   https://mp.baomidou.com/                   |
|   Swagger-UI   |       文档生产工具        | [ https://github.com/swagger-api/swagger-ui](https://github.com/swagger-api/swagger-ui) |
|     Kibana     |     分析和可视化平台      |               https://www.elastic.co/cn/kibana               |
| Elasticsearch  |         搜索引擎          | [ https://github.com/elastic/elasticsearch](https://github.com/elastic/elasticsearch) |
|     Beats      |     轻量型数据采集器      |               https://www.elastic.co/cn/beats/               |
|    Logstash    | 用于接收Beats的数据并处理 |              https://www.elastic.co/cn/logstash              |
|      Solr      |         搜索引擎          |                http://lucene.apache.org/solr/                |
|    RabbitMQ    |         消息队列          |   [ https://www.rabbitmq.com/](https://www.rabbitmq.com/)    |
|     Redis      |        分布式缓存         |                      https://redis.io/                       |
|     Docker     |        容器化部署         |      [ https://www.docker.com](https://www.docker.com/)      |
|     Druid      |       数据库连接池        | [ https://github.com/alibaba/druid](https://github.com/alibaba/druid) |
|     七牛云     |     七牛云 - 对象储存     |         https://developer.qiniu.com/sdk#official-sdk         |
|      JWT       |        JWT登录支持        |                 https://github.com/jwtk/jjwt                 |
|     SLF4J      |         日志框架          |                    http://www.slf4j.org/                     |
|     Lombok     |     简化对象封装工具      | [ https://github.com/rzwitserloot/lombok](https://github.com/rzwitserloot/lombok) |
|     Nginx      |  HTTP和反向代理web服务器  |                      http://nginx.org/                       |
|    JustAuth    |     第三方登录的工具      |             https://github.com/justauth/JustAuth             |
|     Hutool     |      Java工具包类库       |                  https://hutool.cn/docs/#/                   |
|    阿里大于    |       短信发送平台        |            https://doc.alidayu.com/doc2/index.htm            |
| Github Actions |        自动化部署         |              https://help.github.com/en/actions              |
|     Zipkin     |         链路追踪          |             https://github.com/openzipkin/zipkin             |
| Flexmark-java  |     Markdown转换Html      |            https://github.com/vsch/flexmark-java             |
|   Ip2region    |     离线IP地址定位库      |          https://github.com/lionsoul2014/ip2region           |
|     Minio      |     本地对象存储服务      |                       https://min.io/                        |
| Docker Compose |      Docker容器编排       |               https://docs.docker.com/compose/               |
|   Portainer    |     Docker可视化管理      |            https://github.com/portainer/portainer            |

### 前端技术

|         技术          |                  说明                   |                             官网                             |
| :-------------------: | :-------------------------------------: | :----------------------------------------------------------: |
|        Vue.js         |                前端框架                 |                      https://vuejs.org/                      |
|      Vue-router       |                路由框架                 |                  https://router.vuejs.org/                   |
|         Vuex          |            全局状态管理框架             |                   https://vuex.vuejs.org/                    |
|        Nuxt.js        |        创建服务端渲染 (SSR) 应用        |                    https://zh.nuxtjs.org/                    |
|        Element        |               前端ui框架                |    [ https://element.eleme.io](https://element.eleme.io/)    |
|         Axios         |              前端HTTP框架               | [ https://github.com/axios/axios](https://github.com/axios/axios) |
|        Echarts        |                图表框架                 |                      www.echartsjs.com                       |
|       CKEditor        |              富文本编辑器               |                    https://ckeditor.com/                     |
|     Highlight.js      |            代码语法高亮插件             |         https://github.com/highlightjs/highlight.js          |
|        Vditor         |             Markdown编辑器              |             https://github.com/Vanessa219/vditor             |
|      vue-cropper      |              图片裁剪组件               |           https://github.com/xyxiao001/vue-cropper           |
| vue-image-crop-upload |           vue图片剪裁上传组件           |      https://github.com/dai-siki/vue-image-crop-upload       |
|   vue-emoji-comment   |          Vue Emoji表情评论组件          |       https://github.com/pppercyWang/vue-emoji-comment       |
|     clipboard.js      |            现代化的拷贝文字             |                  http://www.clipboardjs.cn/                  |
|      js-beautify      |           美化JavaScript代码            |         https://github.com/beautify-web/js-beautify          |
|     FileSaver.js      |            保存文件在客户端             |           https://github.com/eligrey/FileSaver.js            |
|      SortableJS       |       功能强大的JavaScript 拖拽库       |                  http://www.sortablejs.com/                  |
|   vue-side-catalog    |               目录导航栏                |        https://github.com/yaowei9363/vue-side-catalog        |
|        uniapp         |            移动端跨平台语言             |                  https://uniapp.dcloud.io/                   |
|        colorUi        |         专注视觉的小程序组件库          |             https://github.com/weilanwl/ColorUI              |
|       showdown        | 用Javascript编写的Markdown 到Html转换器 |            https://github.com/showdownjs/showdown            |
|       turndown        | 用JavaScript编写的HTML到Markdown转换器  |           https://github.com/domchristie/turndown            |

## 环境搭建

### 开发工具

|     工具     |       说明        |                             官网                             |
| :----------: | :---------------: | :----------------------------------------------------------: |
|     IDEA     |    Java开发IDE    |           https://www.jetbrains.com/idea/download            |
|   WebStorm   |    前端开发IDE    |             https://www.jetbrains.com/webstorm/              |
| RedisDesktop |  Redis可视化工具  | [ https://redisdesktop.com/download](https://redisdesktop.com/download) |
| SwitchHosts  |   本地Host管理    |             https://oldj.github.io/SwitchHosts/              |
|   X-shell    | Linux远程连接工具 |               https://xshell.en.softonic.com/                |
|    X-ftp     | Linux文件传输工具 |         https://www.netsarang.com/zh/all-downloads/          |
|    SQLyog    |  数据库连接工具   |               https://sqlyog.en.softonic.com/                |
| ScreenToGif  |    Gif录制工具    | [ https://www.screentogif.com/](https://www.screentogif.com/) |

### 开发环境

|     工具      | 版本号 |                             下载                             |
| :-----------: | :----: | :----------------------------------------------------------: |
|      JDK      |  1.8   | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
|     Maven     | 3.3.0+ |                   http://maven.apache.org/                   |
| Elasticsearch | 6.3.0  |               https://www.elastic.co/downloads               |
|     Solr      |  7.0   |                http://lucene.apache.org/solr/                |
|     MySQL     |  5.6   |                    https://www.mysql.com/                    |
|    Erlang     |  20.3  |                   https://www.erlang.org/                    |
|   RabbitMQ    | 3.7.4  |            http://www.rabbitmq.com/download.html             |
|     Nginx     |  1.10  |              http://nginx.org/en/download.html               |
|     Redis     | 3.3.0  |                  https://redis.io/download                   |
|    Zipkin     | 2.12.5 | https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec |
|     Nacos     | 1.3.2  |          https://github.com/alibaba/nacos/releases           |
|   Sentinel    | 1.7.2  |         https://github.com/alibaba/Sentinel/releases         |

## 未来计划

- [ ] 增加K8S部署蘑菇博客教程
- [ ] 增加大屏数据展示页面
- [ ] 增加定时任务模块
- [ ] 使用Freemark页面静态化技术对博客详情页静态化
- [ ] 解决Nuxt_mogu_web中存在的问题，使博客能被搜索引擎收录
- [ ] 让原创文章能够同步到多平台，如：CSDN，掘金，博客园等
- [ ] 增加博客迁移功能，让其它平台的博客，如：CSDN、博客园，WordPress能够同步到蘑菇博客中

## 贡献代码

开源项目离不开大家的支持，如果您有好的想法，遇到一些 **BUG** 并修复了，以及 团子博客文档 上有错误的地方订正过来了，欢迎小伙伴们提交 **Pull Request** 参与开源贡献

1. **fork** 本项目到自己的 **repo**
2. 把 **fork** 过去的项目也就是你仓库中的项目 **clone** 到你的本地
3. 修改代码
4. **commit** 后 **push** 到自己的库
5. 发起**PR**（ **pull request**） 请求，提交到  **Nacos** 分支
6. 等待作者合并

## 开源协议

[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0.html)