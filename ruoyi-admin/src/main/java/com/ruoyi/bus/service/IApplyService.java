package com.ruoyi.bus.service;

import com.ruoyi.bus.domain.Apply;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;

/**
 * 申请 服务层
 * 
 * @author 赵传昊
 * @date 2019-05-05
 */
public interface IApplyService 
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
     * 删除申请信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteApplyByIds(String ids);

	/**
	 * 同意申请信息
	 * @param ids
	 * @return
	 */
	public AjaxResult agreeApplyByIds(String ids);

	/**
	 * 拒绝申请信息
	 * @param ids
	 * @return
	 */
	public int rejectApplyByIds(String ids);

	/**
	 * 退出社团申请
	 * @return
	 */
	public AjaxResult exitOrganizationApply();
	
}
