package com.bootdo.system.service.impl;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.system.dao.RoleDao;
import com.bootdo.system.dao.UserRoleDao;
import com.bootdo.system.domain.RoleDO;
import com.bootdo.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Lihq on 2018/10/8 9:49
 * Description: 角色业务层实现类
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<RoleDO> list(String id) {
        List<Long> roleIds = userRoleDao.findRoleByUserId(id);
        List<RoleDO> roleList = roleDao.list(new HashMap<>());

        for (RoleDO role : roleList) {
            role.setRoleSign("false");

            for (Long roleId : roleIds) {
                if (Objects.equals(roleId, role.getRoleId())) {
                    role.setRoleSign("true");
                    break;
                }
            }
        }
        return roleList;
    }

    @Override
    public PageUtils listAll(Map<String, Object> paramMap) {
        Query query = new Query(paramMap);
        List<RoleDO> roleList = roleDao.list(query);
        int count = roleDao.count(query);
        return new PageUtils(roleList, count);
    }
}
