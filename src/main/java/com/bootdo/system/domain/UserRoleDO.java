package com.bootdo.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRoleDO {
    private Long id;
    private Long userId;
    private Long roleId;

}
