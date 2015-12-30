package com.luckypandadas.dao;

import com.luckypandadas.common.base.IBaseDao;
import com.luckypandadas.common.base.PageVo;
import com.luckypandadas.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Innodev-E531 on 2015/12/23.
 */
@Repository
public class IUserDao implements IBaseDao<User> {
    @Autowired
    private MongoTemplate mongoTemplate;

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
        List<User> list1 = mongoTemplate.findAll(User.class);
        return list1;
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
        Query query = new Query();
        query.addCriteria(new Criteria("telephone").is(entity.getTelephone()));
        Update update = new Update();
        update.set("telephone", entity.getTelephone());
        update.set("email", entity.getEmail());
        update.set("nickname", entity.getNickname());
        update.set("lastLoginTime", entity.getLastLoginTime());
        update.set("updateTime", entity.getUpdatedTime());
        update.set("gender", entity.getGender());
        update.set("picturePath", entity.getPicturePath());
        this.mongoTemplate.updateFirst(query, update, User.class);
    }

    /**
     * 修改头像
     *
     * @param entity
     * @throws Exception
     * @author yadong.zhang
     */
    public void updatePicture(User entity) throws Exception {
        Query query = new Query();
        query.addCriteria(new Criteria("telephone").is(entity.getTelephone()));
        Update update = new Update();
        update.set("picturePath", entity.getPicturePath());
        this.mongoTemplate.updateFirst(query, update, User.class);
    }

    /**
     * 修改密码
     *
     * @param entity
     * @throws Exception
     * @author yadong.zhang
     */
    public void updatePassword(User entity) throws Exception {
        Query query = new Query();
        query.addCriteria(new Criteria("telephone").is(entity.getTelephone()));
        Update update = new Update();
        update.set("password", entity.getPassword());
        this.mongoTemplate.updateFirst(query, update, User.class);
    }

    /**
     * 修改状态
     *
     * @param entity
     * @throws Exception
     * @author yadong.zhang
     */
    public void updateStatus(User entity) throws Exception {
        Query query = new Query();
        query.addCriteria(new Criteria("telephone").is(entity.getTelephone()));
        Update update = new Update();
        update.set("status", entity.getStatus());
        this.mongoTemplate.updateFirst(query, update, User.class);
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
        this.mongoTemplate.save(entity);
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
        //save函数根据参数条件,调用了insert或update函数:有则改之,无则加之
        this.mongoTemplate.insert(entity);
    }

    /**
     * 根据手机获取单个
     *
     * @param tel
     * @return
     * @throws Exception
     * @author yadong.zhang
     */
    @Override
    public User getByTel(String tel) throws Exception {
        Query query = new Query();
        query.addCriteria(new Criteria("telephone").is(tel));
        return this.mongoTemplate.findOne(query, User.class);
    }

    /**
     * 根据邮箱获取单个
     *
     * @param email
     * @return
     * @throws Exception
     * @author yadong.zhang
     */
    public User getByEmail(String email) throws Exception {
        Query query = new Query();
        query.addCriteria(new Criteria("email").is(email));
        return this.mongoTemplate.findOne(query, User.class);
    }

    /**
     * 删除
     *
     * @param user
     * @throws Exception
     * @author yadong.zhang
     */
    @Override
    public void delete(User user) throws Exception {
        this.mongoTemplate.remove(user);
    }

    /**
     * 登录
     *
     * @param loginname
     * @param password
     * @return
     * @throws Exception
     * @author yadong.zhang
     */
    public User login(String loginname, String password) throws Exception {
        Query query = new Query();
        query.addCriteria(new Criteria("telephone").is(loginname));
        query.addCriteria(new Criteria("password").is(password));
        User _user = this.mongoTemplate.findOne(query, User.class);
        if (_user == null) {
            query = new Query();
            query.addCriteria(new Criteria("email").is(loginname));
            query.addCriteria(new Criteria("password").is(password));
            _user = this.mongoTemplate.findOne(query, User.class);
        }
        return _user;
    }
}
