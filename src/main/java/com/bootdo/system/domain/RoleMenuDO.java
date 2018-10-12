package com.bootdo.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleMenuDO {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 角色id
	 */
	private Long  roleId;

	/**
	 * 菜单id
	 */
	private Long menuId;
}
