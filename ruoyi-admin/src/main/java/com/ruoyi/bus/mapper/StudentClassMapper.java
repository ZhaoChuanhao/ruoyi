package com.ruoyi.bus.mapper;

import com.ruoyi.bus.domain.StudentClass;
import java.util.List;	

/**
 * 班级 数据层
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
public interface StudentClassMapper 
{
	/**
     * 查询班级信息
     * 
     * @param id 班级ID
     * @return 班级信息
     */
	public StudentClass selectStudentClassById(Integer id);
	
	/**
     * 查询班级列表
     * 
     * @param studentClass 班级信息
     * @return 班级集合
     */
	public List<StudentClass> selectStudentClassList(StudentClass studentClass);
	
	/**
     * 新增班级
     * 
     * @param studentClass 班级信息
     * @return 结果
     */
	public int insertStudentClass(StudentClass studentClass);
	
	/**
     * 修改班级
     * 
     * @param studentClass 班级信息
     * @return 结果
     */
	public int updateStudentClass(StudentClass studentClass);
	
	/**
     * 删除班级
     * 
     * @param id 班级ID
     * @return 结果
     */
	public int deleteStudentClassById(Integer id);
	
	/**
     * 批量删除班级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteStudentClassByIds(String[] ids);
	
}