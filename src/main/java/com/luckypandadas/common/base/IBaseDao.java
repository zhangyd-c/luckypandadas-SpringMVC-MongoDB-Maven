package com.luckypandadas.common.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yadong.zhang on 2015/12/23.
 */
public interface IBaseDao<T extends BaseModel> {


    /**
     * 获取集合
     * @author yadong.zhang
     * @param vo
     * @return
     * @throws Exception
     */
    List<T> getList(PageVo<T> vo) throws Exception;

    /**
     * 修改操作
     * @author yadong.zhang
     * @param entity
     * @throws Exception
     */
    void update(T entity) throws Exception;

    /**
     * save函数根据参数条件,调用了insert或update函数:有则改之,无则加之
     * @author yadong.zhang
     * @param entity
     * @throws Exception
     */
    void save(T entity) throws Exception;

    /**
     * insert的对象如果存在则不会修改之前的值，也不会重新增加
     * @author yadong.zhang
     * @param entity
     * @throws Exception
     */
    void insert(T entity) throws Exception;

    /**
     * 获取单个
     * @author yadong.zhang
     * @param tel
     * @return
     * @throws Exception
     */
    T getByTel(String tel) throws Exception;

    /**
     * 删除
     * @author yadong.zhang
     * @param entity
     * @throws Exception
     */
    void delete(T entity) throws Exception;
}