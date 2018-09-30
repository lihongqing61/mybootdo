package com.bootdo.common.service.impl;

import com.bootdo.common.dao.DeptDao;
import com.bootdo.common.domain.DeptDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.DeptService;
import com.bootdo.common.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lihq on 2018/9/30 11:13
 * Description: 部门业务层实现类
 */

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public Tree<DeptDO> deptTreeList() {
        List<Tree<DeptDO>> treeList = new ArrayList<>();
        List<DeptDO> deptList = deptDao.list(new DeptDO());
        for (DeptDO dept : deptList) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(dept.getDeptId().toString());
            tree.setText(dept.getName());
            tree.setParentId(dept.getParentId().toString());

            Map<String, Object> stateMap = new HashMap<>();
            stateMap.put("opened", true);
            tree.setState(stateMap);
            treeList.add(tree);
        }
        return BuildTree.build(treeList);
    }
}
