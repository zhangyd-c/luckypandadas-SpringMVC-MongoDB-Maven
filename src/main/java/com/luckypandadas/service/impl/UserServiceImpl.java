package com.luckypandadas.service.impl;

import com.luckypandadas.common.ResponseStatus;
import com.luckypandadas.common.ResponseVo;
import com.luckypandadas.common.annotation.LoggerAnnotation;
import com.luckypandadas.common.base.BaseUtil;
import com.luckypandadas.common.base.PageVo;
import com.luckypandadas.dao.IUserDao;
import com.luckypandadas.model.User;
import com.luckypandadas.model.vo.UserVO;
import com.luckypandadas.service.IUserService;
import com.luckypandadas.utils.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by Innodev-E531 on 2015/12/23.
 */
@Service
public class UserServiceImpl implements IUserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private IUserDao userDao;

    /**
     * 获取集合
     *
     * @param vo
     * @return
     * @throws Exception
     * @author yadong.zhang
     */
    @Override
    @LoggerAnnotation(level = 2, className = "UserServiceImpl", methodName = "getList")
    public List<User> getList(PageVo<User> vo) throws Exception {
        return userDao.getList(vo);
    }

    /**
     * 修改操作
     *
     * @param entity
     * @throws Exception
     * @author yadong.zhang
     */
    @Override
    @LoggerAnnotation(level = 2, className = "UserServiceImpl", methodName = "update")
    public void update(User entity) throws Exception {
        userDao.update(entity);
    }

    /**
     * save函数根据参数条件,调用了insert或update函数:有则改之,无则加之
     *
     * @param entity
     * @throws Exception
     * @author yadong.zhang
     */
    @Override
    @LoggerAnnotation(level = 2, className = "UserServiceImpl", methodName = "save")
    public void save(User entity) throws Exception {
        userDao.save(entity);
    }

    /**
     * 添加用户
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    @LoggerAnnotation(level = 2, className = "UserServiceImpl", methodName = "signUp")
    public ResponseVo signUp(String param) throws Exception {
        ResponseVo responseVo = null;
        if (StringUtil.isEmpty(param)) {
            return new ResponseVo(ResponseStatus.EMPTY_PARAM);
        }
        UserVO userVO = BaseUtil.json2User(param);
        if (userVO == null) {
            return new ResponseVo(ResponseStatus.DATA_PARSE_ERROR);
        }
        System.err.println(userVO.getSecurityCode());
        if (!"123456".equals(userVO.getSecurityCode())) {
            return new ResponseVo(ResponseStatus.SECURITY_CODE_ERROR);
        }
        User _user = userDao.getByTel(userVO.getUser().getTelephone());
        if (_user == null) {
            _user = new User(userVO.getUser().getTelephone(), userVO.getUser().getPassword());
            userDao.save(_user);
            responseVo = new ResponseVo(ResponseStatus.SUCCESS, userVO.getUser());
        } else {
            responseVo = new ResponseVo(ResponseStatus.USER_EXIST);
        }
        return responseVo;
    }

    /**
     * insert的对象如果存在则不会修改之前的值，也不会重新增加
     *
     * @param entity
     * @throws Exception
     * @author yadong.zhang
     */
    @Override
    @LoggerAnnotation(level = 2, className = "UserServiceImpl", methodName = "insert")
    public void insert(User entity) throws Exception {
        userDao.insert(entity);
    }

    /**
     * 获取单个
     *
     * @param tel
     * @return
     * @throws Exception
     * @author yadong.zhang
     */
    @Override
    @LoggerAnnotation(level = 2, className = "UserServiceImpl", methodName = "getByTel")
    public User getByTel(String tel) throws Exception {
        return userDao.getByTel(tel);
    }

    /**
     * 删除
     *
     * @param entity
     * @throws Exception
     * @author yadong.zhang
     */
    @Override
    @LoggerAnnotation(level = 2, className = "UserServiceImpl", methodName = "delete")
    public void delete(User entity) throws Exception {
        userDao.delete(entity);
    }

    /**
     * 修改头像
     *
     * @param entity
     * @throws Exception
     * @author yadong.zhang
     */
    @Override
    @LoggerAnnotation(level = 2, className = "UserServiceImpl", methodName = "updatePicture")
    public void updatePicture(User entity) throws Exception {
        userDao.updatePicture(entity);
    }

    /**
     * 修改密码
     *
     * @param param
     * @throws Exception
     * @author yadong.zhang
     */
    @Override
    @LoggerAnnotation(level = 2, className = "UserServiceImpl", methodName = "updatePassword")
    public ResponseVo updatePassword(String param) throws Exception {
        if (StringUtil.isEmpty(param)) {
            return new ResponseVo(ResponseStatus.EMPTY_PARAM);
        }
        UserVO userVO = BaseUtil.json2User(param);
        if (userVO == null) {
            return new ResponseVo(ResponseStatus.DATA_PARSE_ERROR);
        }
        if (!"123456".equals(userVO.getSecurityCode())) {
            return new ResponseVo(ResponseStatus.SECURITY_CODE_ERROR);
        }
        if (!userVO.getRePassword().equals(userVO.getPassword())) {
            return new ResponseVo(ResponseStatus.PASSWORD_ERROR);
        }
        userDao.updatePassword(userVO.getUser());
        return new ResponseVo(ResponseStatus.SUCCESS, userVO.getUser());
    }

    /**
     * 修改状态
     *
     * @param param
     * @throws Exception
     * @author yadong.zhang
     */
    @Override
    @LoggerAnnotation(level = 2, className = "UserServiceImpl", methodName = "updateStatus")
    public ResponseVo updateStatus(String param) throws Exception {
        if (StringUtil.isEmpty(param)) {
            return new ResponseVo(ResponseStatus.EMPTY_PARAM);
        }

        UserVO userVO = BaseUtil.json2User(param);
        if (userVO == null) {
            return new ResponseVo(ResponseStatus.DATA_PARSE_ERROR);
        }
        if (!"123456".equals(userVO.getSecurityCode())) {
            return new ResponseVo(ResponseStatus.SECURITY_CODE_ERROR);
        }
        userDao.updateStatus(userVO.getUser());
        return new ResponseVo(ResponseStatus.SUCCESS, userVO.getUser());
    }

    /**
     * 修改用户
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    @LoggerAnnotation(level = 2, className = "UserServiceImpl", methodName = "modify")
    public ResponseVo modify(String param) throws Exception {
        UserVO userVO = BaseUtil.json2User(param);
        if (userVO == null) {
            return new ResponseVo(ResponseStatus.DATA_PARSE_ERROR);
        }
        userDao.update(userVO.getUser());
        return new ResponseVo(ResponseStatus.SUCCESS, userVO.getUser());
    }

    /**
     * 根据邮箱获取单个
     *
     * @param email
     * @return
     * @throws Exception
     * @author yadong.zhang
     */
    @Override
    @LoggerAnnotation(level = 2, className = "UserServiceImpl", methodName = "getByEmail")
    public User getByEmail(String email) throws Exception {
        return userDao.getByEmail(email);
    }

    /**
     * 登录
     *
     * @param param
     * @return
     * @throws Exception
     * @author yadong.zhang
     */
    @Override
    @LoggerAnnotation(level = 2, className = "UserServiceImpl", methodName = "login")
    public ResponseVo login(String param) throws Exception {
        ResponseVo responseVo = null;
        if (StringUtil.isEmpty(param)) {
            return new ResponseVo(ResponseStatus.EMPTY_PARAM);
        }
        UserVO userVO = BaseUtil.json2User(param);
        if (userVO == null) {
            return new ResponseVo(ResponseStatus.DATA_PARSE_ERROR);
        }
        User _user = userDao.login(userVO.getUser().getTelephone(), userVO.getUser().getPassword());
        if (_user == null) {
            responseVo = new ResponseVo(ResponseStatus.LOGIN_ERROR);
        } else {
            responseVo = new ResponseVo(ResponseStatus.SUCCESS, _user);
        }
        return responseVo;
    }

}
