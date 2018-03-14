- eureka-server(port:8761):服务注册与发现工程
- service-hi(port:8762,8763):服务提供工程
- client-ribbon(port:8764):服务消费工程，采用ribbon调用
- client-feign(port:8765):服务消费工程，采用feign调用

> 在微服务架构中，业务都会被拆分成一个独立的服务，服务与服务的通讯是基于http restful的。Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign。  
> Ribbon is a client side load balancer which gives you a lot of control over the behaviour of HTTP and TCP clients. Feign already uses Ribbon, so if you are using @FeignClient then this section also applies.–摘自官网

# eureka-server
- 1、pom：引入spring-cloud-starter-eureka-server依赖
- 2、启动服务注册中心：@EnableEurekaServer注解
- 3、配置appication.yml
    - eureka.client.registerWithEureka：false和fetchRegistry：false来表明自己是一个eureka server.
    - http://l27.0.0.1:8761,监控服务中心

# service-hi
- 1、pom：引入spring-cloud-starter-eureka-server依赖
- 2、表明自己是一个EurekaClient：@EnableEurekaClient注解
- 3、配置appication.yml
    - spring.application.name:指定服务名称
    - eureka.client.service-url.defaultZone:指定连接的注册中心
    -  http://localhost:8762/hi?name=forezp：测试服务提供者是否正常

# client-ribbon
- 1、pom：引入spring-cloud-starter-eureka、spring-cloud-starter-ribbon依赖
- 2、向服务中心注册：@EnableDiscoveryClient注解
- 3、配置appication.yml
    - spring.application.name:指定服务名称
    - eureka.client.service-url.defaultZone:指定连接的注册中心
- 4、@Bean和@LoadBalanced注入一个RestTemplate
    ```
    	@Bean
    	@LoadBalanced
    	RestTemplate restTemplate(){
    		return new RestTemplate();
    	}
    ```
- 5、通过RestTemplate消费服务：
    ```
    @Service
    public class HiService {
        @Autowired
        RestTemplate restTemplate;
    
        public String hiService(String name) {
            return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
        }
    }
    ```
- 6、测试：
    - 启动两个service-hi
    - 访问：http://localhost:8764/hi?name=forezp,可以实现负载均衡
- 7、断路器：
    - 加入spring-cloud-starter-hystrix依赖
    - 开启Hystrix：@EnableHystrix注解
    - HiService增加相应的注解和方法，如下,hiError方法即为服务不可用时调用的方法：
    ```
    @Service
    public class HiService {
        @Autowired
        RestTemplate restTemplate;
        
        @HystrixCommand(fallbackMethod = "hiError")
        public String hiService(String name) {
            return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
        }
    
        public String hiError(String name) {
            return "sorry " + name + ",服务暂不可用!";
        }
    }
    ```
    
# client-feign
- 1、pom：引入spring-cloud-starter-eureka、spring-cloud-starter-feign依赖
- 2、向服务中心注册：@EnableDiscoveryClient注解
- 3、启用feign进行远程调用：@EnableFeignClients注解
- 3、配置appication.yml
    - spring.application.name:指定服务名称
    - eureka.client.service-url.defaultZone:指定连接的注册中心
- 4、定义一个feign接口，通过@ FeignClient（“服务名”），来指定调用哪个服务（即service-hi工程中的spring.application.name）
    ```
    	@FeignClient(value = "service-hi")
        public interface HiService {
            @RequestMapping(value = "/hi", method = RequestMethod.GET)
            String sayHi(@RequestParam(value = "name") String name);
        }
    ```
- 5、直接注入HiService并调用对应的方法即可
- 6、测试：
    - 启动两个service-hi
    - 访问：http://localhost:8765/hi?name=forezp,可以实现负载均衡
- 7、断路器：
    - 打开断路器：feign.hystrix.enabled=true
    - @FeignClient注解标注的类上，增加属性，并实现当前接口、重写相应的方法  
    `
    @FeignClient(value = "service-hi", fallback = HiServiceHystric.class)
    `


参考博客：
* [方志朋](http://blog.csdn.net/forezp/article/details/70148833)
* [程序猿DD](http://blog.didispace.com/)
* [markdown使用](https://www.jianshu.com/p/q81RER)