package com.bootdo.system.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Lihq on 2018/10/11 16:18
 * Description: 菜单控制器
 */

@Controller
@RequestMapping("/sys/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/tree")
    @ResponseBody
    public List<Tree<MenuDO>> tree() {

        return menuService.listMenuTree(ShiroUtils.getUserId());
    }
}
