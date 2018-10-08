package com.bootdo.system.dao;

import com.bootdo.system.domain.RoleDO;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lihq on 2018/10/8 9:50
 * Description: 角色持久层
 */
public interface RoleDao {

    List<RoleDO> list(HashMap map);
}
