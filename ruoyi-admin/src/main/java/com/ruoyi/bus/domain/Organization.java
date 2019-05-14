package com.ruoyi.bus.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 社团表 bus_organization
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
public class Organization extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Integer id;
	/** 社团编码 */
	private String organizationCode;
	/** 社团名称 */
	private String name;
	/** 团长学号 */
	private String studentNumber;
	/** 社团团长 */
	private String leader;
	/** 社团人数 */
	private Integer size;
	/** 社团经费 */
	private Double funds;
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
	public void setOrganizationCode(String organizationCode) 
	{
		this.organizationCode = organizationCode;
	}

	public String getOrganizationCode() 
	{
		return organizationCode;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setStudentNumber(String studentNumber) 
	{
		this.studentNumber = studentNumber;
	}

	public String getStudentNumber() 
	{
		return studentNumber;
	}
	public void setLeader(String leader) 
	{
		this.leader = leader;
	}

	public String getLeader() 
	{
		return leader;
	}
	public void setSize(Integer size) 
	{
		this.size = size;
	}

	public Integer getSize() 
	{
		return size;
	}
	public void setFunds(Double funds) 
	{
		this.funds = funds;
	}

	public Double getFunds() 
	{
		return funds;
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
            .append("organizationCode", getOrganizationCode())
            .append("name", getName())
            .append("studentNumber", getStudentNumber())
            .append("leader", getLeader())
            .append("size", getSize())
            .append("funds", getFunds())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
