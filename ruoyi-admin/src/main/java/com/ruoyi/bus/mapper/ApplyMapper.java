package com.ruoyi.bus.mapper;

import com.ruoyi.bus.domain.Apply;
import java.util.List;	

/**
 * 申请 数据层
 * 
 * @author 赵传昊
 * @date 2019-05-05
 */
public interface ApplyMapper 
{
	/**
     * 查询申请信息
     * 
     * @param id 申请ID
     * @return 申请信息
     */
	public Apply selectApplyById(Integer id);
	
	/**
     * 查询申请列表
     * 
     * @param apply 申请信息
     * @return 申请集合
     */
	public List<Apply> selectApplyList(Apply apply);
	
	/**
     * 新增申请
     * 
     * @param apply 申请信息
     * @return 结果
     */
	public int insertApply(Apply apply);
	
	/**
     * 修改申请
     * 
     * @param apply 申请信息
     * @return 结果
     */
	public int updateApply(Apply apply);
	
	/**
     * 删除申请
     * 
     * @param id 申请ID
     * @return 结果
     */
	public int deleteApplyById(Integer id);
	
	/**
     * 批量删除申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteApplyByIds(String[] ids);
	
}