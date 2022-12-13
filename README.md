## 平台简介

若依是一套全部开源的快速开发平台，毫无保留给个人及企业免费使用。我们在此基础上开发了ruoyi-mall。希望能给做商城的朋友减轻工作量。

* 前端采用Vue、Element UI。
* 后端采用Spring Boot、Spring Security、Redis & Jwt。
* 权限认证使用Jwt，支持多终端认证系统。
* 支持加载动态权限菜单，多方式轻松权限控制。
* 高效率开发，使用代码生成器可以一键生成前后端代码。

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
   1. 启动程序，启动程序路径如下
   ![img.png](doc/img5.png)

## 关于我们
开发团队成立5年，我们前端开发、后端架构，有一颗热爱开源的心，致力于打造企业级的通用产品设计UI体系让项目 或者更直观，更高效、更简单，未来将持续关注UI交互，持续推出高质量的交互产品。