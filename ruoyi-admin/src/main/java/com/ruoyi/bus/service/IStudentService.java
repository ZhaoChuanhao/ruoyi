package com.ruoyi.bus.service;

import com.ruoyi.bus.domain.Student;
import java.util.List;

/**
 * 学生 服务层
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
public interface IStudentService 
{
	/**
     * 查询学生信息
     * 
     * @param id 学生ID
     * @return 学生信息
     */
	public Student selectStudentById(Integer id);

	/**
	 * 查询学生信息
	 *
	 * @param studentNumber 学号
	 * @return 学生信息
	 */
	public Student selectStudentByStudentNumber(String studentNumber);
	
	/**
     * 查询学生列表
     * 
     * @param student 学生信息
     * @return 学生集合
     */
	public List<Student> selectStudentList(Student student);
	
	/**
     * 新增学生
     * 
     * @param student 学生信息
     * @return 结果
     */
	public int insertStudent(Student student);
	
	/**
     * 修改学生
     * 
     * @param student 学生信息
     * @return 结果
     */
	public int updateStudent(Student student);

	/**
	 * 修改学生所属社团
	 *
	 * @param student 学生信息
	 * @return 结果
	 */
	public int updateStudentOrganizationId(Student student);
		
	/**
     * 删除学生信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteStudentByIds(String ids);
	
}
