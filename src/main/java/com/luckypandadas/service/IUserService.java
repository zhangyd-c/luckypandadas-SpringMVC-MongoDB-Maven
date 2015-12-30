package com.luckypandadas.service;

import com.luckypandadas.common.ResponseVo;
import com.luckypandadas.common.base.IBaseDao;
import com.luckypandadas.model.User;
import org.apache.ibatis.executor.ExecutorException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by yadong.zhang on 2015/12/23.
 */
public interface IUserService extends IBaseDao<User> {

    /**
     * 修改头像
     *
     * @param entity
     * @throws Exception
     * @author yadong.zhang
     */
    public void updatePicture(User entity) throws Exception;

    /**
     * 修改密码
     *
     * @param param
     * @throws Exception
     * @author yadong.zhang
     */
    public ResponseVo updatePassword(String param) throws Exception;

    /**
     * 修改状态
     *
     * @param param
     * @throws Exception
     * @author yadong.zhang
     */
    public ResponseVo updateStatus(String param) throws Exception;

    /**
     * 根据邮箱获取单个
     *
     * @param email
     * @return
     * @throws Exception
     * @author yadong.zhang
     */
    public User getByEmail(String email) throws Exception;

    /**
     * 登录
     *
     * @param param
     * @return
     * @throws Exception
     * @author yadong.zhang
     */
    public ResponseVo login(String param) throws Exception;

    /**
     * 修改用户
     *
     * @param param
     * @return
     * @throws Exception
     */
    public ResponseVo modify(String param) throws Exception;

    /**
     * 添加用户
     *
     * @param param
     * @return
     * @throws Exception
     */
    public ResponseVo signUp(String param) throws Exception;
}
