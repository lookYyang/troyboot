package com.troyboot.java.system.po;

import java.io.Serializable;

public class SysRolePermission implements Serializable {
    private Integer id;

    private Integer permission_id;

    private Integer role_id;

    private static final long serialVersionUID = 1L;

    public SysRolePermission(Integer id, Integer permission_id, Integer role_id) {
        this.id = id;
        this.permission_id = permission_id;
        this.role_id = role_id;
    }

    public SysRolePermission() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Integer permission_id) {
        this.permission_id = permission_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysRolePermission other = (SysRolePermission) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPermission_id() == null ? other.getPermission_id() == null : this.getPermission_id().equals(other.getPermission_id()))
            && (this.getRole_id() == null ? other.getRole_id() == null : this.getRole_id().equals(other.getRole_id()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPermission_id() == null) ? 0 : getPermission_id().hashCode());
        result = prime * result + ((getRole_id() == null) ? 0 : getRole_id().hashCode());
        return result;
    }
}