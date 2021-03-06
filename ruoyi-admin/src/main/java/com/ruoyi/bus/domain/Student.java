package com.ruoyi.bus.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生表 bus_student
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
public class Student extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Integer id;
	/** 学号 */
	@Excel(name = "学号")
	private String studentNumber;
	/** 姓名 */
	@Excel(name = "姓名")
	private String name;
	/** 性别 */
	@Excel(name = "性别")
	private String sex;
	/** 学院 */
	private String collegeId;
	/** 班级 */
	private String classId;
	/** 所属社团 */
	private Integer organizationId;
	/** 所属社团名称 */
	@Excel(name = "所属社团")
	private String organizationName;
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
	public void setSex(String sex) 
	{
		this.sex = sex;
	}

	public String getSex() 
	{
		return sex;
	}
	public void setCollegeId(String collegeId) 
	{
		this.collegeId = collegeId;
	}

	public String getCollegeId() 
	{
		return collegeId;
	}
	public void setClassId(String classId) 
	{
		this.classId = classId;
	}

	public String getClassId() 
	{
		return classId;
	}
	public void setOrganizationId(Integer organizationId) 
	{
		this.organizationId = organizationId;
	}

	public Integer getOrganizationId() 
	{
		return organizationId;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
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
            .append("sex", getSex())
            .append("collegeId", getCollegeId())
            .append("classId", getClassId())
            .append("organizationId", getOrganizationId())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
