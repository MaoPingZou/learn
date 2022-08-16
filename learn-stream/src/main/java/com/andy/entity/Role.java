package com.andy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色实体
 *
 * @author MaoPing Zou
 * @date 2022/8/16 08:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    /**
     * 角色id
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
}
