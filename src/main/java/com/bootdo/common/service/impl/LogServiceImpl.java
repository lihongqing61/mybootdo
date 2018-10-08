package com.bootdo.common.service.impl;

import com.bootdo.common.dao.LogDao;
import com.bootdo.common.domain.LogDO;
import com.bootdo.common.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lihq on 2018/10/8 15:41
 * Description:
 */

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void save(LogDO log) {
        logDao.save(log);
    }
}
