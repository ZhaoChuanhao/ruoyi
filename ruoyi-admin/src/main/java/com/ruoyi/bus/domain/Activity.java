package com.ruoyi.bus.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 活动表 bus_activity
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
public class Activity extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Integer id;
	/** 活动主题 */
	private String title;
	/** 活动内容 */
	private String content;
	/** 活动时间 */
	private Date date;
	/** 活动地点 */
	private String place;
	/** 活动负责人 */
	private String leader;
	/** 活动负责人联系方式 */
	private String phone;
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
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setDate(Date date) 
	{
		this.date = date;
	}

	public Date getDate() 
	{
		return date;
	}
	public void setPlace(String place) 
	{
		this.place = place;
	}

	public String getPlace() 
	{
		return place;
	}
	public void setLeader(String leader) 
	{
		this.leader = leader;
	}

	public String getLeader() 
	{
		return leader;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDelFlag()
	{
		return delFlag;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("date", getDate())
            .append("place", getPlace())
            .append("leader", getLeader())
            .append("phone", getPhone())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
