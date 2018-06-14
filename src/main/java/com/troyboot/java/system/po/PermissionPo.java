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
 * @Date 2018/6/7 1:47
 */
@Getter
@Setter
@Entity
@Table(name = "sys_permission", catalog = "")
// 懒加载方式配置
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PermissionPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long parent_id;

    private String name;

    private String permission;//menu:role.*;button:role:create,role:update

    private String url;

    //0目录;1菜单;2按钮
    private String type;

    private String icon;

    private int sort_no;

    private int is_enable = Constant.YesOrNo.YES.getValue();

    @ManyToMany
    @JoinTable(name = "sys_role_permission",
            joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<RolePo> rolePos;

}
