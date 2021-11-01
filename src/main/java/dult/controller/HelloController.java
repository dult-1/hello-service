package dult.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        for(ServiceInstance instance:serviceInstanceList){
            System.out.println(" hello,host:"+instance.getHost()+" serverid:"+instance.getServiceId());
        }
        return "hello eureka ";
    }
}
