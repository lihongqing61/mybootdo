package com.bootdo.common.dao;

import com.bootdo.common.domain.FileDO;

/**
 * Created by Lihq on 2018/9/29 15:34
 * Description: 文件持久层
 */
public interface FileDao {

    FileDO findFileById(Long picId);
}
