## 平台简介

若依是一套全部开源的快速开发平台，毫无保留给个人及企业免费使用。我们在此基础上开发了ruoyi-mall，希望能给做商城的朋友减轻工作量。

* 前端采用Vue、Element UI。
* 后端采用Spring Boot、Spring Security、Redis & Jwt。
* 权限认证使用Jwt，支持多终端认证系统。
* 支持加载动态权限菜单，多方式轻松权限控制。
* 高效率开发，使用代码生成器可以一键生成前后端代码。

## 项目地址
|                | gitee                                   | github                                       |
|:--------------:|:----------------------------------------|:---------------------------------------------|
| 管理端vue element | https://gitee.com/zccbbg/ruoyi-mall-vue | https://github.com/zccbbg/ruoyi-mall-vue     |
|   h5 uniapp    | https://gitee.com/zccbbg/ruoyi-mall-uniapp | https://github.com/zccbbg/ruoyi-mall-uniapp  |

## 在线体验
- 演示地址：http://mall.ichengle.top/
- 目前商品模块基本完成，其他模块正在开发中。
- 陆陆续续收到一些打赏，为了更好的体验已用于演示服务器升级。谢谢各位小伙伴。

## 若依技术专栏
- 常见问题：https://blog.csdn.net/qq_27575627/category_12336113.html
- 后端技术：https://blog.csdn.net/qq_27575627/category_12331868.html
- 前端技术：https://blog.csdn.net/qq_27575627/category_12331867.html
- 运维：https://blog.csdn.net/qq_27575627/category_12332546.html

## 若依mall功能
1. 首页：
2. PMS商品管理：
3. UMS会员管理：
4. OMS订单管理：
5. SMS营销管理：
6. CMS内容管理：

## 本地安装
### 基本环境
1、JDK：8+
2、Redis 3.0+
3、Maven 3.0+
4、MYSQL 5.7+
5、Node v8+

### 后台系统工程（JAVA端）
1. 导入数据库，配置开发环境数据库信息及其redis信息，我设置了git ignore，请自行创建application-dev.yml文件，文件路径如下：  
   ![img.png](doc/img.png)  
   ![img.png](doc/img2.png)  
   ![img.png](doc/img3.png)  
2. 在父级pom.xml输入命令 mvn clean install 或者用idea工具操作  
   ![img.png](doc/img4.png)  
3.启动程序，启动程序路径如下  
   ![img.png](doc/img5.png)  

## 关于我们
* 开发团队成立5年，我们前端开发、后端架构，有一颗热爱开源的心，致力于打造企业级的通用产品设计UI体系让项目 或者更直观，更高效、更简单，未来将持续关注UI交互，持续推出高质量的交互产品。
* 这五年我主要做isv对接淘宝、拼多多、抖音、美团等平台的订单处理应用，日处理订单300w条，因为要熟悉业务也开过淘宝和拼多多店铺运营了一个网易严选的品牌。我们的公众号会陆续更新一些我一边撸代码一边做客服的经历。也会更新一些我的读书笔记以及编程、创业、生活中踩坑的文章。另外还会开放一些米哈游、博世、企查查、同程、阿里、京东、拼多多等大厂、中厂或外企的内推岗位！

## 技术支持
|                    公众号                     |
|:------------------------------------------:|
| <img src="doc/datacall.jpg" width="200px"> |
扫公众号二维码，关注后，回复：“支持”