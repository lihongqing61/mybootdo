package com.bootdo.system.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.system.dao.MenuDao;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lihq on 2018/9/29 14:14
 * Description: 菜单业务层实现类
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Tree<MenuDO>> listMenuTree(Long userId) {

        List<Tree<MenuDO>> treeList = new ArrayList<>();
        List<MenuDO> menuList = menuDao.listMenuByUserId(userId);

        for (MenuDO menu : menuList) {
            Tree<MenuDO> tree = new Tree<MenuDO>();
            tree.setId(menu.getMenuId().toString());
            tree.setText(menu.getName());
            tree.setParentId(menu.getParentId().toString());

            Map<String, Object> attrMap = new HashMap<>();
            attrMap.put("url", menu.getUrl());
            attrMap.put("icon", menu.getIcon());
            tree.setAttributes(attrMap);
            treeList.add(tree);
        }
        return BuildTree.buildList(treeList, "0");
    }
}
