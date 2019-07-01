package com.qianfeng.mailuserregister.controller;

import com.qianfeng.TUser;
import com.qianfeng.constant.RabbitMQConstant;
import com.qianfeng.service.IUserService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: WangXi
 * @Date: 2019/6/21
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Reference
    private IUserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("register")
    @ResponseBody
    public String register(TUser user){

        userService.insertSelective(user);
        String s = UUID.randomUUID().toString();
        //需要将redis存放进redis
        Map<String,String> map=new HashMap<>();
        map.put("email",user.getEmail());
        map.put("uuid",s);
        rabbitTemplate.convertAndSend(RabbitMQConstant.EMAIL_EXCHANGE,"email.send",map);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("check")
    public String check(String uuid){

        return uuid;
    }
}
