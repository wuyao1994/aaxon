# AAXON
[![React](https://img.shields.io/badge/react-^16.2.0-brightgreen.svg?style=flat-square)](https://github.com/facebook/react)
[![Ant Design](https://img.shields.io/badge/ant--design-^3.0.3-yellowgreen.svg?style=flat-square)](https://github.com/ant-design/ant-design)
[![dva](https://img.shields.io/badge/dva-^2.1.0-orange.svg?style=flat-square)](https://github.com/dvajs/dva)
[![spring boot](https://img.shields.io/badge/spring%20boot-2.0.6.RELEASE-brightgreen.svg?style=flat-square)](https://github.com/spring-projects/spring-boot)
[![spring cloud](https://img.shields.io/badge/spring%20cloud-Finchley.SR2-green.svg?style=flat-square)](https://github.com/spring-cloud)
[![MIT](https://img.shields.io/dub/l/vibe-d.svg?style=flat-square)](http://opensource.org/licenses/MIT)
## 项目介绍
Spring Cloud reference application for building an site as microservices  
演示地址 <https://tainfu.club>
## 组织结构 

```bash
aaxon
├── aaxon-static          -- 前端静态代码
|    ├── /dist/           # 项目输出目录
|    ├── /src/            # 项目源码目录
│    |     ├── /public/       # 公共文件，编译时copy至dist目录
│    |     ├── /components/   # UI组件及UI相关方法
│    |     ├── /routes/       # 路由组件
│    |     |     └── app.js       # 路由入口
│    |     ├── /models/       # 数据模型
│    |     ├── /services/     # 数据接口
│    |     ├── /themes/       # 项目样式
│    |     ├── /mock/         # 数据mock
│    |     ├── /utils/        # 工具函数
│    │     |      ├── menu.js      # 菜单及面包屑配置
│    │     |      ├── config.js    # 项目常规配置
│    │     |      ├── request.js   # 异步请求函数
│    │     |      ├── inedx.js      # 工具函数
│    │     |      └── theme.js     # 项目需要在js中使用到样式变量
│    |     ├── route.js       # 路由配置
│    |     ├── index.js       # 入口文件
│    |     └── entry.ejs     
|    ├── package.json     # 项目信息
|    ├── .eslintrc        # Eslint配置
|    ├── version.js
|    ├── wepack.config.js #webpack配置
|    ├── theme.config.js  #theme配置        
|    ├── .roadhogrc.mock.js #mock        
|    └── .roadhogrc.js    # roadhog配置
└── upm-service -- 用户权限管理服务
└── config-service -- 集中配置中心
└── discovery-service -- 服务注册中心
└── hystrix-dashboard -- 服务容错监控中心
```

## 技术选型

### 前端技术：

| 技术 |  官网 |
| ------------- | ----- |
| react | [https://reactjs.org/](https://reactjs.org/ "https://reactjs.org/") | 
| redux | [http://redux.js.org/](http://redux.js.org/ "http://redux.js.org/")
| dva | [https://github.com/dvajs/dva/](https://github.com/dvajs/dva/ "https://github.com/dvajs/dva/")
| Ant Design | [https://ant.design/index-cn](https://ant.design/index-cn "https://ant.design/index-cn")|

### 后端技术：
| 技术 | 官网 |
| ------------- | ----- |
| spring boot | [http://projects.spring.io/spring-boot/](http://projects.spring.io/spring-boot/ "http://projects.spring.io/spring-boot/") |
| spring cloud | [http://projects.spring.io/spring-cloud/](http://projects.spring.io/spring-cloud/ "http://projects.spring.io/spring-cloud/") |
## 环境搭建
Mysql5.7+  
Zookeeper  
Node  
Java1.8  
Redis  
## 快速开始
本机安装Jdk1.8、Mysql、Redis、Zookeeper并**启动相关服务**，使用默认配置默认端口即可  
**推荐使用IntelliJ IDEA**，本地编译并安装到本地maven仓库  
克隆项目文件:
```bash
git clone https://github.com/wuyao1994/aaxon.git
```

#### 安装依赖  
前端
```bash
#开始前请确保没有安装roadhog、webpack到NPM全局目录
npm i 或者 yarn install
```
后台
```bash
根据 pom.xml 配置下载 maven 依赖包
```
#### 构建项目  
前端
```bash
npm run build
```
后端
```bash
mvn clean install
```
#### 本地开发  
前端
```bash
npm run build:dll #第一次npm run dev时需运行此命令，使开发时编译更快
npm run dev
```
后端
```bash
step 1.add hosts :
127.0.0.1 discovery-service
127.0.0.1 config-service
127.0.0.1 upm-service
127.0.0.1 aaxon kafka mysql zookeeper
step2.启动各个module Application.java, 启动顺序config-service discovery-service hystrix-service upm-service 
```
#### 访问
```bash
打开 http://localhost:8000
```
## 服务器部署
1. 环境配置  
Centos7  
docker  
maven  
node  
JDK1.8  
nginx
2. nginx配置  
静态代理指向dist目录, 反向代理配置为路由端口
参考配置
```bash
     server{
                listen       80;
                server_name localhost;
                 root  /home/xxx/workspace/aaxon/aaxon-static/dist;
                 gzip on;
                 gzip_buffers 32 4k;
                 gzip_comp_level 6;
                 gzip_min_length 200;
                 gzip_types text/css text/xml application/javascript;
                 gzip_vary on;
 
                 location /api/v1 {
                           proxy_pass http://localhost:8081/;
                 }
  
                 location / {
                                 index  index.html;
                                 try_files $uri $uri/ /index.html;
                 }
         }

```
3.docker部署service
```bash
sh run.sh
```


## 参考项目资料

| 项目 |  官网 |
| ------------- | ----- |
| zheng | [https://github.com/shuzheng/zheng](https://github.com/shuzheng/zheng/ "https://github.com/shuzheng/zheng") | 
| antd-admin | [https://github.com/zuiidea/antd-admin](https://github.com/zuiidea/antd-admin "https://github.com/zuiidea/antd-admin") |
