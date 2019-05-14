package com.ruoyi.bus.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bus.mapper.StudentClassMapper;
import com.ruoyi.bus.domain.StudentClass;
import com.ruoyi.bus.service.IStudentClassService;
import com.ruoyi.common.core.text.Convert;

/**
 * 班级 服务层实现
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
@Service
public class StudentClassServiceImpl implements IStudentClassService 
{
	@Autowired
	private StudentClassMapper studentClassMapper;

	/**
     * 查询班级信息
     * 
     * @param id 班级ID
     * @return 班级信息
     */
    @Override
	public StudentClass selectStudentClassById(Integer id)
	{
	    return studentClassMapper.selectStudentClassById(id);
	}
	
	/**
     * 查询班级列表
     * 
     * @param studentClass 班级信息
     * @return 班级集合
     */
	@Override
	public List<StudentClass> selectStudentClassList(StudentClass studentClass)
	{
	    return studentClassMapper.selectStudentClassList(studentClass);
	}
	
    /**
     * 新增班级
     * 
     * @param studentClass 班级信息
     * @return 结果
     */
	@Override
	public int insertStudentClass(StudentClass studentClass)
	{
	    return studentClassMapper.insertStudentClass(studentClass);
	}
	
	/**
     * 修改班级
     * 
     * @param studentClass 班级信息
     * @return 结果
     */
	@Override
	public int updateStudentClass(StudentClass studentClass)
	{
	    return studentClassMapper.updateStudentClass(studentClass);
	}

	/**
     * 删除班级对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteStudentClassByIds(String ids)
	{
		return studentClassMapper.deleteStudentClassByIds(Convert.toStrArray(ids));
	}
	
}
