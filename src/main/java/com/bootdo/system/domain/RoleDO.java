package com.bootdo.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;

/**
 * 角色实体类
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDO {

	/**
	 * 角色id
	 */
	private Long roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色标识
	 */
	private String roleSign;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建用户id
	 */
	private Long userIdCreate;

	/**
	 * 创建时间
	 */
	private Timestamp gmtCreate;

	/**
	 * 修改时间
	 */
	private Timestamp gmtModified;

	/**
	 * 拥有的菜单集合
	 */
	private List<Long> menuIds;

}
