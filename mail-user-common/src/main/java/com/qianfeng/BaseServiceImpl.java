package com.qianfeng;

import java.util.List;

public abstract class BaseServiceImpl<T> implements IBaseService<T>{

   // private  IBaseDao baseDao;
    public  abstract IBaseDao<T> getBaseDao();

    @Override
    public int deleteByPrimaryKey(Long id) {

        return getBaseDao().deleteByPrimaryKey(id);
    }
    @Override
    public int insert(T record) {

        return getBaseDao().insert(record);
    }
    @Override
    public int insertSelective(T record) {
        return getBaseDao().insertSelective(record);
    }
    @Override
    public T selectByPrimaryKey(Long id) {
        return getBaseDao().selectByPrimaryKey(id);
    }
    @Override
    public int updateByPrimaryKeySelective(T record) {
        return getBaseDao().updateByPrimaryKeySelective(record);
    }
    @Override
    public int updateByPrimaryKeyWithBLOBs(T record) {
        return getBaseDao().updateByPrimaryKeyWithBLOBs(record);
    }
    @Override
    public int updateByPrimaryKey(T record) {
        return getBaseDao().updateByPrimaryKey(record);
    }
    @Override
    public List<T> list() {
        return getBaseDao().list();
    }
}
