package com.ruoyi.bus.mapper;

import com.ruoyi.bus.domain.College;
import java.util.List;	

/**
 * 学院 数据层
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
public interface CollegeMapper 
{
	/**
     * 查询学院信息
     * 
     * @param id 学院ID
     * @return 学院信息
     */
	public College selectCollegeById(Integer id);
	
	/**
     * 查询学院列表
     * 
     * @param college 学院信息
     * @return 学院集合
     */
	public List<College> selectCollegeList(College college);
	
	/**
     * 新增学院
     * 
     * @param college 学院信息
     * @return 结果
     */
	public int insertCollege(College college);
	
	/**
     * 修改学院
     * 
     * @param college 学院信息
     * @return 结果
     */
	public int updateCollege(College college);
	
	/**
     * 删除学院
     * 
     * @param id 学院ID
     * @return 结果
     */
	public int deleteCollegeById(Integer id);
	
	/**
     * 批量删除学院
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCollegeByIds(String[] ids);
	
}