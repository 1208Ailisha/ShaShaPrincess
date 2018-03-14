# ShaShaPrincess

### 多环境配置
application.properties
application-dev.properties
application-test.properties
application-prod.properties

在application.properties中设置spring.profiles.active=dev/test/prod可以在不同的环境使用不同的配置
也可以在工程启动时设置，如：
java -jar xxx.jar --spring.profiles.active=prod
