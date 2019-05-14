package com.ruoyi.bus.mapper;

import com.ruoyi.bus.domain.Activity;
import java.util.List;	

/**
 * 活动 数据层
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
public interface ActivityMapper 
{
	/**
     * 查询活动信息
     * 
     * @param id 活动ID
     * @return 活动信息
     */
	public Activity selectActivityById(Integer id);
	
	/**
     * 查询活动列表
     * 
     * @param activity 活动信息
     * @return 活动集合
     */
	public List<Activity> selectActivityList(Activity activity);
	
	/**
     * 新增活动
     * 
     * @param activity 活动信息
     * @return 结果
     */
	public int insertActivity(Activity activity);
	
	/**
     * 修改活动
     * 
     * @param activity 活动信息
     * @return 结果
     */
	public int updateActivity(Activity activity);
	
	/**
     * 删除活动
     * 
     * @param id 活动ID
     * @return 结果
     */
	public int deleteActivityById(Integer id);
	
	/**
     * 批量删除活动
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteActivityByIds(String[] ids);
	
}