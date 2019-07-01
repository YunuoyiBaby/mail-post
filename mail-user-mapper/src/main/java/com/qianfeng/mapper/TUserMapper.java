package com.qianfeng.mapper;


import com.qianfeng.IBaseDao;
import com.qianfeng.TUser;

public interface TUserMapper extends IBaseDao<TUser> {


    TUser selectByUsername(String username);
}