package com.troyboot.java.system.po;

import java.io.Serializable;
import java.util.Date;

public class SysOrg implements Serializable {
    private Integer id;

    private String name;

    private Integer parentId;

    private String cascadeId;

    private String isExpanded;

    private Integer sortNo;

    private String createBy;

    private Date createTime;

    private String remark;

    private static final long serialVersionUID = 1L;

    public SysOrg(Integer id, String name, Integer parentId, String cascadeId, String isExpanded, Integer sortNo, String createBy, Date createTime, String remark) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.cascadeId = cascadeId;
        this.isExpanded = isExpanded;
        this.sortNo = sortNo;
        this.createBy = createBy;
        this.createTime = createTime;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCascadeId() {
        return cascadeId;
    }

    public void setCascadeId(String cascadeId) {
        this.cascadeId = cascadeId == null ? null : cascadeId.trim();
    }

    public String getIsExpanded() {
        return isExpanded;
    }

    public void setIsExpanded(String isExpanded) {
        this.isExpanded = isExpanded == null ? null : isExpanded.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getCascadeId() == null ? other.getCascadeId() == null : this.getCascadeId().equals(other.getCascadeId()))
            && (this.getIsExpanded() == null ? other.getIsExpanded() == null : this.getIsExpanded().equals(other.getIsExpanded()))
            && (this.getSortNo() == null ? other.getSortNo() == null : this.getSortNo().equals(other.getSortNo()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getCascadeId() == null) ? 0 : getCascadeId().hashCode());
        result = prime * result + ((getIsExpanded() == null) ? 0 : getIsExpanded().hashCode());
        result = prime * result + ((getSortNo() == null) ? 0 : getSortNo().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}