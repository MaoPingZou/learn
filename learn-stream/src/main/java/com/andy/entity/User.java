package com.andy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户实体
 *
 * @author MaoPing Zou
 * @date 2022/8/16 08:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode // 用于重写equals方法
public class User {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户年龄
     */
    private Integer age;
    /**
     * 用户角色
     */
    private List<Role> roleList;
}
