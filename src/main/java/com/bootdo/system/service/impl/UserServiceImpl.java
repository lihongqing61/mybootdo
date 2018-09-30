package com.bootdo.system.service.impl;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by Lihq on 2018/9/30 14:54
 * Description:
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public PageUtils list(Query query) {
        List<UserDO> userList = userDao.list(query);
        int count = userDao.count(query);
        return new PageUtils(userList, count);
    }
}
