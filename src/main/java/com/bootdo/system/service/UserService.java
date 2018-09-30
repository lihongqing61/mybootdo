package com.bootdo.system.service;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;

/**
 * Created by Lihq on 2018/9/30 14:53
 * Description: 用户持久层
 */
public interface UserService {

    PageUtils list(Query query);
}
