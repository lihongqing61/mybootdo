package com.bootdo.system.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.domain.MenuDO;
import java.util.List;
import java.util.Set;

/**
 * Created by Lihq on 2018/9/29 14:13
 * Description: 菜单业务层
 */
public interface MenuService {

    /**
     * 获取当前用户的菜单权限
     * @param userId
     * @return
     */
    List<Tree<MenuDO>> listMenuTree(Long userId);

    Set<String> listUserPerms(Long userId);
}
