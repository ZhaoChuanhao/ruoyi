package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.SysStudent;
import com.ruoyi.system.mapper.SysStudentMapper;
import com.ruoyi.system.service.ISysStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author 赵传昊
 * @Date 2019/5/11 16:25
 * @Version 1.0
 */
@Service
public class SysStudentServiceImpl implements ISysStudentService {

    @Autowired
    private SysStudentMapper studentMapper;

    /**
     * 查询学生信息
     *
     * @param id 学生ID
     * @return 学生信息
     */
    @Override
    public SysStudent selectStudentById(Integer id)
    {
        return studentMapper.selectStudentById(id);
    }

    @Override
    public SysStudent selectStudentByStudentNumber(String studentNumber) {
        return studentMapper.selectStudentByStudentNumber(studentNumber);
    }

    /**
     * 查询学生列表
     *
     * @param student 学生信息
     * @return 学生集合
     */
    @Override
    public List<SysStudent> selectStudentList(SysStudent student)
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
    public int insertStudent(SysStudent student)
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
    public int updateStudent(SysStudent student)
    {
        return studentMapper.updateStudent(student);
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
