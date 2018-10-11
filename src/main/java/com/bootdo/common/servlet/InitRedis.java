package com.bootdo.common.servlet;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bootdo.common.service.RedisService;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.UserDO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import java.util.List;

/**
 * Created by Lihq on 2018/10/11 14:15
 * Description:
 */
@Configuration
public class InitRedis implements InitializingBean {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserDao userDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<UserDO> userList = userDao.list(null);
        redisService.delete("userList");
        redisService.set("userList", JSONObject.toJSONString(userList, SerializerFeature.WriteNullStringAsEmpty), 300L);
    }
}
