package com.bootdo.common.service.impl;

import com.bootdo.common.dao.FileDao;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lihq on 2018/9/29 15:29
 * Description: 文件业务层实现类
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Override
    public FileDO findFileById(Long picId) {
        return fileDao.findFileById(picId);
    }
}
