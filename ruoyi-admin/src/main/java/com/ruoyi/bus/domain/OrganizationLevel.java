package com.ruoyi.bus.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 社团职级表 bus_organization_level
 * 
 * @author 赵传昊
 * @date 2019-05-14
 */
public class OrganizationLevel extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Integer id;
	/** 社团id（0代表公共职级） */
	private Integer organizationId;
	/** 职级 */
	private String level;
	/** 职务 */
	private String job;
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setOrganizationId(Integer organizationId) 
	{
		this.organizationId = organizationId;
	}

	public Integer getOrganizationId() 
	{
		return organizationId;
	}
	public void setLevel(String level) 
	{
		this.level = level;
	}

	public String getLevel() 
	{
		return level;
	}
	public void setJob(String job) 
	{
		this.job = job;
	}

	public String getJob() 
	{
		return job;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("organizationId", getOrganizationId())
            .append("level", getLevel())
            .append("job", getJob())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
