package com.ruoyi.bus.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bus.mapper.StudentMapper;
import com.ruoyi.bus.domain.Student;
import com.ruoyi.bus.service.IStudentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 学生 服务层实现
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
@Service
public class StudentServiceImpl implements IStudentService 
{
	@Autowired
	private StudentMapper studentMapper;

	/**
     * 查询学生信息
     * 
     * @param id 学生ID
     * @return 学生信息
     */
    @Override
	public Student selectStudentById(Integer id)
	{
	    return studentMapper.selectStudentById(id);
	}

	@Override
	public Student selectStudentByStudentNumber(String studentNumber) {
		return studentMapper.selectStudentByStudentNumber(studentNumber);
	}
	
	/**
     * 查询学生列表
     * 
     * @param student 学生信息
     * @return 学生集合
     */
	@Override
	public List<Student> selectStudentList(Student student)
	{
	    return studentMapper.selectStudentList(student);
	}
	
    /**
     * 新增学生
     * 
     * @param student 学生信息
     * @return 结果
     */
	@Override
	public int insertStudent(Student student)
	{
	    return studentMapper.insertStudent(student);
	}
	
	/**
     * 修改学生
     * 
     * @param student 学生信息
     * @return 结果
     */
	@Override
	public int updateStudent(Student student)
	{
	    return studentMapper.updateStudent(student);
	}

	/**
	 * 修改学生所属社团
	 *
	 * @param student 学生信息
	 * @return 结果
	 */
	@Override
	public int updateStudentOrganizationId(Student student)
	{
		return studentMapper.updateStudentOrganizationId(student);
	}

	/**
     * 删除学生对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteStudentByIds(String ids)
	{
		return studentMapper.deleteStudentByIds(Convert.toStrArray(ids));
	}
	
}
