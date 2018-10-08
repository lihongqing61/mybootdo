package com.bootdo.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogDO {
	private Long id;

	private Long userId;

	private String username;

	private String operation;

	private Integer time;

	private String method;

	private String params;

	private String ip;
//	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;

}