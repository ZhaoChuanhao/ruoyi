package com.ruoyi.bus.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 班级表 bus_student_class
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
public class StudentClass extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Integer id;
	/** 班级编号 */
	private Integer classNumber;
	/** 班级人数 */
	private Integer size;
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
	public void setClassNumber(Integer classNumber) 
	{
		this.classNumber = classNumber;
	}

	public Integer getClassNumber() 
	{
		return classNumber;
	}
	public void setSize(Integer size) 
	{
		this.size = size;
	}

	public Integer getSize() 
	{
		return size;
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
            .append("classNumber", getClassNumber())
            .append("size", getSize())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
