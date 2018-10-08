package com.bootdo.system.service.impl;

import com.bootdo.system.dao.RoleDao;
import com.bootdo.system.domain.RoleDO;
import com.bootdo.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lihq on 2018/10/8 9:49
 * Description: 角色业务层实现类
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<RoleDO> list() {
        return roleDao.list(new HashMap<>());
    }
}
