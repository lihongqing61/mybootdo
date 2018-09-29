package com.bootdo.common.controller;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;
import org.springframework.stereotype.Controller;

/**
 * @Auther: Lihq
 * @Date: 2018/9/28 21:02
 * @Description: 基础控制器
 */
@Controller
public class BaseController {

    /**
     * 获取当前用户
     * @return
     */
    public UserDO getUser() {
        return ShiroUtils.getUser();
    }

    /**
     * 获取当前用户id
     * @return
     */
    public Long getUserId() {
        return getUser().getUserId();
    }

    /**
     * 获取当前用户名称
     * @return
     */
    public String getUsername() {
        return getUser().getUsername();
    }

    /**
     * 获取当前用户姓名
     * @return
     */
    public String getName() {
        return getUser().getName();
    }
}
