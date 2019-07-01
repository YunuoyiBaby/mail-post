package com.qianfeng.emilservice;


import com.qianfeng.pojo.ResultBean;

public interface IEmailService {
    public ResultBean send(String to, String subjecct, String text);
}
