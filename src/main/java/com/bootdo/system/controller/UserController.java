package com.bootdo.system.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * Created by Lihq on 2018/9/30 9:09
 * Description: 用户管理控制器
 */
@Controller
@RequestMapping("/sys/user/")
public class UserController extends BaseController {

    private String prefix = "system/user/";


    @Autowired
    private UserService userService;

    /**
     * 返回到用户页面
     * @return
     */
    @GetMapping("")
    public String toUserPage() {
        return prefix + "user";
    }

    /**
     * 查询用户列表
     * @param paramMap
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public PageUtils list(@RequestParam Map<String, Object> paramMap) {
        Query query = new Query(paramMap);
        return userService.list(query);
    }
}
