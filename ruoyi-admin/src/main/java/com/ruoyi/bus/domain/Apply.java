package com.ruoyi.bus.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 申请表 bus_apply
 * 
 * @author 赵传昊
 * @date 2019-05-05
 */
public class Apply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Integer id;
	/** 申请者id */
	private Integer stuId;
	/** 申请者学号 */
	private String studentNumber;
	/** 申请者姓名 */
	private String name;
	/** 申请者性别 */
	private String sex;
	/** 申请社团id */
	private Integer organizationId;
	/** 申请社团编码 */
	private String organizationCode;
	/** 申请社团名称 */
	private String organizationName;
	/** 申请类型 */
	private Integer applyType;
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
	public void setStudentNumber(String studentNumber) 
	{
		this.studentNumber = studentNumber;
	}

	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public String getStudentNumber()
	{
		return studentNumber;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setOrganizationCode(String organizationCode) 
	{
		this.organizationCode = organizationCode;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationCode()
	{
		return organizationCode;
	}
	public void setOrganizationName(String organizationName) 
	{
		this.organizationName = organizationName;
	}

	public String getOrganizationName() 
	{
		return organizationName;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
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
            .append("studentNumber", getStudentNumber())
            .append("name", getName())
            .append("organizationCode", getOrganizationCode())
            .append("organizationName", getOrganizationName())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
