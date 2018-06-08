package com.troyboot.java.system.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.troyboot.java.system.common.Constant;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Authour YangYang
 * @Date 2018/6/7 1:27
 */
@Getter
@Setter
@Entity
@Table(name = "sys_role", catalog = "")
// 懒加载方式配置
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RolePo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private int is_enable = Constant.YesOrNo.YES.getValue();

    /**
     * 排序号
     */
    private Integer sort_no;

    @ManyToMany
    @JoinTable(name = "sys_role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<PermissionPo> permissionPos;

    @ManyToMany
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<UserPo> userPos;
}
