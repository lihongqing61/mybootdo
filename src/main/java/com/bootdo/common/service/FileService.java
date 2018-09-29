package com.bootdo.common.service;

import com.bootdo.common.domain.FileDO;

/**
 * Created by Lihq on 2018/9/29 15:29
 * Description: 文件业务层
 */
public interface FileService {

    /**
     * 根据主键查询文件信息
     * @param picId
     * @return
     */
    FileDO findFileById(Long picId);
}
