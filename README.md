# 校园闲置电子产品交易平台
ciep_trading为项目服务端开发，ciep_trading_view为项目客户端开发。
## 主要技术栈
- Spring Boot
- JWT
- MyBatis
- MyBatis-Plus
- Swagger
- MySQL
- Git
- Maven
## 运行项目
1. 克隆项目
```cmd
git clone https://github.com/woshizhendefule/ciep_trading.git
```
2. 下载依赖,点击 pom.xml 下载 maven 依赖
3. 准备MySql数据库,执行 doc/sql/create_table.sql
4. 修改 application.yml 的 datasource 相关配置
5. 运行 CiepTradingApplication.java
## 注意事项
1. 图片缓存路径设置
```java
web:
  upload-path: C:\\Users\\CL\\Desktop\\data
```
upload-path为图片缓存绝对路径
