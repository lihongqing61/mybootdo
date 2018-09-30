package com.bootdo.system.dao;

import com.bootdo.system.domain.MenuDO;

import java.util.List;

/**
 * Created by Lihq on 2018/9/29 14:28
 * Description: 菜单持久层
 */
public interface MenuDao {

    List<MenuDO> listMenuByUserId(Long userId);

    List<String> listUserPerms(Long userId);
}
