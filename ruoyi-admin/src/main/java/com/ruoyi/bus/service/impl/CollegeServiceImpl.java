package com.ruoyi.bus.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bus.mapper.CollegeMapper;
import com.ruoyi.bus.domain.College;
import com.ruoyi.bus.service.ICollegeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 学院 服务层实现
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
@Service
public class CollegeServiceImpl implements ICollegeService 
{
	@Autowired
	private CollegeMapper collegeMapper;

	/**
     * 查询学院信息
     * 
     * @param id 学院ID
     * @return 学院信息
     */
    @Override
	public College selectCollegeById(Integer id)
	{
	    return collegeMapper.selectCollegeById(id);
	}
	
	/**
     * 查询学院列表
     * 
     * @param college 学院信息
     * @return 学院集合
     */
	@Override
	public List<College> selectCollegeList(College college)
	{
	    return collegeMapper.selectCollegeList(college);
	}
	
    /**
     * 新增学院
     * 
     * @param college 学院信息
     * @return 结果
     */
	@Override
	public int insertCollege(College college)
	{
	    return collegeMapper.insertCollege(college);
	}
	
	/**
     * 修改学院
     * 
     * @param college 学院信息
     * @return 结果
     */
	@Override
	public int updateCollege(College college)
	{
	    return collegeMapper.updateCollege(college);
	}

	/**
     * 删除学院对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCollegeByIds(String ids)
	{
		return collegeMapper.deleteCollegeByIds(Convert.toStrArray(ids));
	}
	
}
