package com.bootdo.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 文件类型
     */
    private Integer type;

    /**
     *  URL地址
     */
    private String url;

    /**
     * 创建时间
     */
    private Date createDate;


}
