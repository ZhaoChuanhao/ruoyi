package com.ruoyi.bus.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学院表 bus_college
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
public class College extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Integer id;
	/** 学院编码 */
	private String collegeCode;
	/** 学院名称 */
	private String name;
	/** 学院院长 */
	private String leader;
	/** 辅导员 */
	private String instructor;
	/** 教师人数 */
	private Integer teacherCount;
	/** 学生人数 */
	private Integer studentCount;
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
	public void setCollegeCode(String collegeCode) 
	{
		this.collegeCode = collegeCode;
	}

	public String getCollegeCode() 
	{
		return collegeCode;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setLeader(String leader) 
	{
		this.leader = leader;
	}

	public String getLeader() 
	{
		return leader;
	}
	public void setInstructor(String instructor) 
	{
		this.instructor = instructor;
	}

	public String getInstructor() 
	{
		return instructor;
	}
	public void setTeacherCount(Integer teacherCount) 
	{
		this.teacherCount = teacherCount;
	}

	public Integer getTeacherCount() 
	{
		return teacherCount;
	}
	public void setStudentCount(Integer studentCount) 
	{
		this.studentCount = studentCount;
	}

	public Integer getStudentCount() 
	{
		return studentCount;
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
            .append("collegeCode", getCollegeCode())
            .append("name", getName())
            .append("leader", getLeader())
            .append("instructor", getInstructor())
            .append("teacherCount", getTeacherCount())
            .append("studentCount", getStudentCount())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
