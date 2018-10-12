package com.bootdo.system.dao;

import com.bootdo.system.domain.RoleMenuDO;
import java.util.List;

/**
 * Created by Lihq on 2018/10/12 10:20
 * Description:
 */
public interface RoleMenuDao {

    void batchSave(List<RoleMenuDO> rmList);
}
