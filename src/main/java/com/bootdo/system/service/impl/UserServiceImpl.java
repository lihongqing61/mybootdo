package com.bootdo.system.service.impl;

import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.dao.UserRoleDao;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.domain.UserRoleDO;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lihq on 2018/9/30 14:54
 * Description:
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public PageUtils list(Query query) {
        List<UserDO> userList = userDao.list(query);
        int count = userDao.count(query);
        return new PageUtils(userList, count);
    }

    @Override
    public boolean exit(String username) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("offset", "0");
        paramMap.put("limit", "10");

        Query query = new Query(paramMap);

        boolean flag = false;
        flag = userDao.list(query).size() > 0;
        return flag;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int save(UserDO user) {
        user.setPassword(MD5Utils.encrypt(user.getPassword()));
        int count = 0;
        try {
            count = userDao.save(user);

            userRoleDao.removeRoleById(user.getUserId());

            List<UserRoleDO> userRoleList = new ArrayList<>();
            for (Long roleId : user.getRoleIds()) {
                UserRoleDO ur = new UserRoleDO();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                userRoleList.add(ur);
            }
            if (userRoleList.size() > 0) {
                userRoleDao.batchSave(userRoleList);
            }
        } catch (Exception e) {
            throw e;
        }
        return count;
    }

    @Override
    public UserDO findUserById(String id) {
        return userDao.findUserById(id);
    }
}
