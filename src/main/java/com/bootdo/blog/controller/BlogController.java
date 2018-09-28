package com.bootdo.blog.controller;

import com.bootdo.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: Lihq
 * @Date: 2018/9/28 21:02
 * @Description: Blog控制器
 */
@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController {

    @GetMapping("")
    public String blog() {
        return "blog/index/main";
    }
}
