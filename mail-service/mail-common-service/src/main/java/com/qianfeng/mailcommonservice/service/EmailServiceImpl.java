package com.qianfeng.mailcommonservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.qianfeng.emilservice.IEmailService;
import com.qianfeng.pojo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements IEmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${mail.fromAddr}")
    private String fromAddr;

    @Override
    public ResultBean send(String to, String subject, String text) {
        MimeMessage message= javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(fromAddr);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text,true);

            javaMailSender.send(message);

            System.out.println("发送邮件成功");
            return new ResultBean("200","成功");

        }catch (MessagingException e){
            e.printStackTrace();
        }
        return new ResultBean("500","失败");
    }
}
