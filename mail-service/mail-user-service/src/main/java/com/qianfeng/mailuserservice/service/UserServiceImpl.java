package com.qianfeng.mailuserservice.service;


import com.qianfeng.BaseServiceImpl;
import com.qianfeng.IBaseDao;
import com.qianfeng.TUser;
import com.qianfeng.mapper.TUserMapper;
import com.qianfeng.pojo.ResultBean;
import com.qianfeng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends BaseServiceImpl<TUser> implements IUserService {
    @Autowired
    private TUserMapper userMapper;


    @Override
    public IBaseDao<TUser> getBaseDao() {
        return userMapper;
    }
    @Override
    public int insertSelective(TUser record) {
       super.insertSelective(record);
       return record.getId().intValue();
    }

    @Override
    public ResultBean checkLogin(TUser user) {
        TUser currnetUser = userMapper.selectByUsername(user.getUsername());
        if(currnetUser != null){
            if(currnetUser.getPassword().equals(user.getPassword())){
                //合法账号
                //2.操作Redis保存凭证
                String uuid = UUID.randomUUID().toString();
                String key = new StringBuilder("user:token:").append(uuid).toString();
                //去掉密码信息
                user.setPassword(null);

                //返回信息
                return new ResultBean("200",uuid);
            }
        }
        //验证不通过
        return new ResultBean("404","");
    }

    @Override
    public ResultBean checkIsLogin(String uuid) {
        return null;
    }

    @Override
    public ResultBean logout(String uuid) {
        return null;
    }


}
