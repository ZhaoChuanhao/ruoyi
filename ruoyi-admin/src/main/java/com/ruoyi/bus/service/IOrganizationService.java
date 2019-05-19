package com.ruoyi.bus.service;

import com.ruoyi.bus.domain.Organization;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;

/**
 * 社团 服务层
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
public interface IOrganizationService 
{
	/**
     * 查询社团信息
     * 
     * @param id 社团ID
     * @return 社团信息
     */
	public Organization selectOrganizationById(Integer id);

	/**
	 * 查询社团信息
	 * @param name 社团名称
	 * @return 社团信息
	 */
	public Organization selectOrganizationByName(String name);
	
	/**
     * 查询社团列表
     * 
     * @param organization 社团信息
     * @return 社团集合
     */
	public List<Organization> selectOrganizationList(Organization organization);
	
	/**
     * 新增社团
     * 
     * @param organization 社团信息
     * @return 结果
     */
	public AjaxResult insertOrganization(Organization organization);
	
	/**
     * 修改社团
     * 
     * @param organization 社团信息
     * @return 结果
     */
	public int updateOrganization(Organization organization);
		
	/**
     * 删除社团信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public AjaxResult deleteOrganizationByIds(String ids);

	/**
	 * 申请社团
	 * @param organization 社团信息
	 * @return
	 */
	public AjaxResult applyOrganization(Organization organization) throws Exception;

	/**
	 * 根据社团名称查询社团ID
	 * @param name 社团名称
	 * @return
	 */
	public Integer selectIdByName(String name);
	
}
