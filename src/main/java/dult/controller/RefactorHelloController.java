package dult.controller;

import dult.dto.User;
import dult.service.HelloService;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dult on 2021-11-10.
 */
@RestController
public class RefactorHelloController implements HelloService {
    @Override
    public String hello(String name) {
        return "hello "+name;
    }

    @Override
    public User hello(String name, Integer age) {
        return new User(name,age);
    }

    @Override
    public String hello(User user) {
        return "hello " + user.getName() + " ," + user.getAge();
    }
}
