package com.bootdo.common.service;

import com.bootdo.common.domain.DeptDO;
import com.bootdo.common.domain.Tree;

/**
 * Created by Lihq on 2018/9/30 11:12
 * Description: 部门业务层
 */

public interface DeptService {

    Tree<DeptDO> deptTreeList();
}
