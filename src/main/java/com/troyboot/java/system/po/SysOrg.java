package com.troyboot.java.system.po;

import java.io.Serializable;
import java.util.Date;

public class SysOrg implements Serializable {
    private Integer id;

    private String name;

    private Integer parent_id;

    private String cascade_id;

    private String is_expanded;

    private Integer sort_no;

    private String create_by;

    private Date create_time;

    private String remark;

    private static final long serialVersionUID = 1L;

    public SysOrg(Integer id, String name, Integer parent_id, String cascade_id, String is_expanded, Integer sort_no, String create_by, Date create_time, String remark) {
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
        this.cascade_id = cascade_id;
        this.is_expanded = is_expanded;
        this.sort_no = sort_no;
        this.create_by = create_by;
        this.create_time = create_time;
        this.remark = remark;
    }

    public SysOrg() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getCascade_id() {
        return cascade_id;
    }

    public void setCascade_id(String cascade_id) {
        this.cascade_id = cascade_id == null ? null : cascade_id.trim();
    }

    public String getIs_expanded() {
        return is_expanded;
    }

    public void setIs_expanded(String is_expanded) {
        this.is_expanded = is_expanded == null ? null : is_expanded.trim();
    }

    public Integer getSort_no() {
        return sort_no;
    }

    public void setSort_no(Integer sort_no) {
        this.sort_no = sort_no;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by == null ? null : create_by.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        SysOrg other = (SysOrg) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getParent_id() == null ? other.getParent_id() == null : this.getParent_id().equals(other.getParent_id()))
            && (this.getCascade_id() == null ? other.getCascade_id() == null : this.getCascade_id().equals(other.getCascade_id()))
            && (this.getIs_expanded() == null ? other.getIs_expanded() == null : this.getIs_expanded().equals(other.getIs_expanded()))
            && (this.getSort_no() == null ? other.getSort_no() == null : this.getSort_no().equals(other.getSort_no()))
            && (this.getCreate_by() == null ? other.getCreate_by() == null : this.getCreate_by().equals(other.getCreate_by()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getParent_id() == null) ? 0 : getParent_id().hashCode());
        result = prime * result + ((getCascade_id() == null) ? 0 : getCascade_id().hashCode());
        result = prime * result + ((getIs_expanded() == null) ? 0 : getIs_expanded().hashCode());
        result = prime * result + ((getSort_no() == null) ? 0 : getSort_no().hashCode());
        result = prime * result + ((getCreate_by() == null) ? 0 : getCreate_by().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}