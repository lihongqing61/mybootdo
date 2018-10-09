package com.bootdo.system.service;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.system.domain.UserDO;

/**
 * Created by Lihq on 2018/9/30 14:53
 * Description: 用户持久层
 */
public interface UserService {

    PageUtils list(Query query);

    boolean exit(String username);

    int save(UserDO user);
}
