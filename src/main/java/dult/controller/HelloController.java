package dult.controller;

import dult.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dult on 2021-11-1.
 */
@RestController
public class HelloController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/hello")
    public String hello(){
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("hello-service");
        //测试超时
//        int sleepTime = new Random().nextInt(3000);
//        System.out.println("sleepTime:" + sleepTime);
//        try {
//            Thread.sleep(sleepTime);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(" hello,host:"+serviceInstanceList.get(0).getHost()+" serverid:"+serviceInstanceList.get(0).getServiceId());
        return "hello eureka "+System.currentTimeMillis();
    }

    @RequestMapping("/findAll")
    public List<String> findAll(@RequestParam("ids") List<String> ids){
        System.out.println("===服务提供者收到findAll请求==="+ids);
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("hello-service");
        for(ServiceInstance instance:serviceInstanceList){
            System.out.println(" hello,host:"+instance.getHost()+" serverid:"+instance.getServiceId());
        }
        List<String> resultList = new ArrayList<>();
        resultList = ids;
        return resultList;
    }

    @RequestMapping("/hello1")
    public String hello(@RequestParam String name){
        return "hello "+name;
    }

    @RequestMapping("/hello2")
    public User hello(@RequestHeader String name,@RequestHeader Integer age){
        return new User(name,age);
    }

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    public String hello(@RequestBody User user){
        return "hello "+user.getName() +" ,"+user.getAge();
    }
}
