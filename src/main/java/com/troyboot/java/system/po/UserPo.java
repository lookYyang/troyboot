package com.troyboot.java.system.po;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.troyboot.java.system.common.Constant;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


/**
 * lombok 节省代码量
 */
@Getter
@Setter
@Entity
@Table(name = "sys_user", catalog = "")
// 懒加载方式配置
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "name不能为空")
    private String account;

    private String name;

    private String password;

    private int status = Constant.YesOrNo.YES.getValue();

    private int is_delete = Constant.YesOrNo.NO.getValue();

    @ManyToMany
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<RolePo> rolePos;
}
