package com.bootdo.common.dao;

import com.bootdo.common.domain.DeptDO;
import java.util.List;

/**
 * Created by Lihq on 2018/9/30 11:14
 * Description: 部门持久层
 */
public interface DeptDao {

    List<DeptDO> list(DeptDO dept);
}
