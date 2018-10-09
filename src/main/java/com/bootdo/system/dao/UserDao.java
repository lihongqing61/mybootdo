package com.bootdo.system.dao;

import com.bootdo.common.utils.Query;
import com.bootdo.system.domain.UserDO;
import java.util.List;

/**
 * Created by Lihq on 2018/9/29 11:23
 * Description:
 */

// @Mapper  此次不用配置 已在主控制器中配置@MapperScan("com.bootdo.**.dao")
public interface UserDao {

    UserDO findUserByUsername(String username);

    List<UserDO> list(Query query);

    int count(Query query);

    int save(UserDO user);

    UserDO findUserById(String id);
}
