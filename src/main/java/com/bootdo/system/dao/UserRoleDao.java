package com.bootdo.system.dao;

import com.bootdo.system.domain.UserRoleDO;
import java.util.List;

/**
 * Created by Lihq on 2018/10/9 10:33
 * Description:
 */
public interface UserRoleDao {

    void removeRoleById(Long userId);

    void batchSave(List<UserRoleDO> userRoleList);

    List<Long> findRoleByUserId(String id);

    void batchRemoveByUserId(String[] userIds);
}
