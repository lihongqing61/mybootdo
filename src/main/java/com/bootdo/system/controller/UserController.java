package com.bootdo.system.controller;

import com.bootdo.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Lihq on 2018/9/30 9:09
 * Description: 用户管理控制器
 */
@Controller
@RequestMapping("/sys/user/")
public class UserController extends BaseController {

    private String prefix = "system/user/";

    @GetMapping("")
    public String toUserPage() {
        return prefix + "user";
    }
}
