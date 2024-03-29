package com.qianfeng.service;

import com.qianfeng.IBaseService;
import com.qianfeng.TUser;
import com.qianfeng.pojo.ResultBean;

/**
 * @author huangguizhao
 */
public interface IUserService extends IBaseService<TUser> {

    /**
     *
     * @param user
     * @return  resultBean里面的data，保存在redis中的凭证key（uuid）
     */
    public ResultBean checkLogin(TUser user);

    /**
     *
     * @param uuid 保存在客户端的凭证信息
     * @return
     */
    public ResultBean checkIsLogin(String uuid);

    /**
     *
     * @param uuid 保存在客户端的凭证信息
     * @return
     */
    public ResultBean logout(String uuid);
}
