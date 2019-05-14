package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysStudent;

import java.util.List;

/**
 * @Description TODO
 * @Author 赵传昊
 * @Date 2019/5/11 16:24
 * @Version 1.0
 */
public interface ISysStudentService {
    /**
     * 查询学生信息
     *
     * @param id 学生ID
     * @return 学生信息
     */
    public SysStudent selectStudentById(Integer id);

    /**
     * 查询学生信息
     *
     * @param studentNumber 学号
     * @return 学生信息
     */
    public SysStudent selectStudentByStudentNumber(String studentNumber);

    /**
     * 查询学生列表
     *
     * @param student 学生信息
     * @return 学生集合
     */
    public List<SysStudent> selectStudentList(SysStudent student);

    /**
     * 新增学生
     *
     * @param student 学生信息
     * @return 结果
     */
    public int insertStudent(SysStudent student);

    /**
     * 修改学生
     *
     * @param student 学生信息
     * @return 结果
     */
    public int updateStudent(SysStudent student);

    /**
     * 删除学生信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStudentByIds(String ids);
}
