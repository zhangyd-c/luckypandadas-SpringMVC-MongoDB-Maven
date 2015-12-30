package com.luckypandadas.service.impl;

import com.luckypandadas.common.Constants;
import com.luckypandadas.common.ResponseVo;
import com.luckypandadas.common.base.PageVo;
import com.luckypandadas.dao.IUserDao;
import com.luckypandadas.model.User;
import com.luckypandadas.model.vo.UserVO;
import com.luckypandadas.service.IUserService;
import com.luckypandadas.utils.StringBuildUtils;
import com.luckypandadas.utils.StringUtil;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
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
    public ResponseVo signUp(String param) throws Exception {
        ResponseVo responseVo = null;
        if (StringUtil.isEmpty(param)) {
            return new ResponseVo(Constants.RESPONSE_CODE_500, "参数错误");
        }
        UserVO userVO = json2User(param);
        if (userVO == null) {
            return new ResponseVo(Constants.RESPONSE_CODE_500, "json解析异常");
        }
        System.err.println(userVO.getSecurityCode());
        if (!"123456".equals(userVO.getSecurityCode())) {
            return new ResponseVo(Constants.RESPONSE_CODE_500, "验证码错误！");
        }
        User _user = userDao.getByTel(userVO.getUser().getTelephone());
        if (_user == null) {
            _user = new User(userVO.getUser().getTelephone(), userVO.getUser().getPassword());
            userDao.save(_user);
            responseVo = new ResponseVo(Constants.RESPONSE_CODE_200, "注册成功！", userVO.getUser());
        } else {
            responseVo = new ResponseVo(Constants.RESPONSE_CODE_500, "已存在的用户！");
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
    public ResponseVo updatePassword(String param) throws Exception {
        if (StringUtil.isEmpty(param)) {
            return new ResponseVo(Constants.RESPONSE_CODE_500, "参数错误");
        }
        UserVO userVO = json2User(param);
        if (userVO == null) {
            return new ResponseVo(Constants.RESPONSE_CODE_500, "json解析异常");
        }
        if (!"123456".equals(userVO.getSecurityCode())) {
            return new ResponseVo(Constants.RESPONSE_CODE_500, "验证码错误！");
        }
        if (!userVO.getRePassword().equals(userVO.getPassword())) {
            return new ResponseVo(Constants.RESPONSE_CODE_500, "密码不一致！");
        }
        userDao.updatePassword(userVO.getUser());
        return new ResponseVo(Constants.RESPONSE_CODE_200, "密码修改成功！", userVO.getUser());
    }

    private UserVO json2User(String param) {
        UserVO userVO = null;
        try {
            JSONObject obj = JSONObject.fromObject(param);
            User user = (User) JSONObject.toBean(JSONObject.fromObject(obj.get("user")), User.class);
            userVO = new UserVO(user, obj.get("securityCode").toString(), obj.get("rePassword").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userVO;
    }

    /**
     * 将json转化为实体POJO
     *
     * @param jsonStr
     * @param obj
     * @return
     */
    public static <T> Object JSONToObj(String jsonStr, Class<T> obj) {
        T t = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            t = objectMapper.readValue(jsonStr, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 修改状态
     *
     * @param param
     * @throws Exception
     * @author yadong.zhang
     */
    @Override
    public ResponseVo updateStatus(String param) throws Exception {
        if (StringUtil.isEmpty(param)) {
            return new ResponseVo(Constants.RESPONSE_CODE_500, "参数错误");
        }
        UserVO userVO = json2User(param);
        if (userVO == null) {
            return new ResponseVo(Constants.RESPONSE_CODE_500, "json解析异常");
        }
        if (!"123456".equals(userVO.getSecurityCode())) {
            return new ResponseVo(Constants.RESPONSE_CODE_500, "验证码错误！");
        }
        userDao.updateStatus(userVO.getUser());
        return new ResponseVo(Constants.RESPONSE_CODE_200, "修改成功！", userVO.getUser());
    }

    /**
     * 修改用户
     *
     * @param param
     * @return
     * @throws Exception
     */
    public ResponseVo modify(String param) throws Exception {
        UserVO userVO = json2User(param);
        if (userVO == null) {
            return new ResponseVo(Constants.RESPONSE_CODE_500, "json解析异常");
        }
        userDao.update(userVO.getUser());
        return new ResponseVo(Constants.RESPONSE_CODE_200, "修改成功！", userVO.getUser());
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
    public ResponseVo login(String param) throws Exception {
        ResponseVo responseVo = null;
        if (StringUtil.isEmpty(param)) {
            return new ResponseVo(Constants.RESPONSE_CODE_500, "参数错误");
        }
        UserVO userVO = json2User(param);
        if (userVO == null) {
            return new ResponseVo(Constants.RESPONSE_CODE_500, "json解析异常");
        }
        User _user = userDao.login(userVO.getUser().getTelephone(), userVO.getUser().getPassword());
        if (_user == null) {
            responseVo = new ResponseVo(Constants.RESPONSE_CODE_500, "用户名或密码错误！");
        } else {
            responseVo = new ResponseVo(Constants.RESPONSE_CODE_200, "登录成功！", _user);
        }
        return responseVo;
    }

}
