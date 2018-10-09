package com.bootdo.system.service;

import com.bootdo.system.domain.RoleDO;
import java.util.List;

/**
 * Created by Lihq on 2018/10/8 9:45
 * Description: 角色业务层
 */
public interface RoleService {

    List<RoleDO> list(String id);
}
