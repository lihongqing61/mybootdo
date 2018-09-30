package com.bootdo.common.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Lihq on 2018/9/29 16:51
 * Description: 字典控制器
 */
@Controller
@RequestMapping("/common/dict")
public class DictController extends BaseController {

    @GetMapping()
    @RequiresPermissions("common:dict:dict")
    String dict() {
        return "common/dict/dict";
    }
}
