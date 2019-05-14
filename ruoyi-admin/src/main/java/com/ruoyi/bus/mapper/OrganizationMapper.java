package com.ruoyi.bus.mapper;

import com.ruoyi.bus.domain.Organization;
import java.util.List;	

/**
 * 社团 数据层
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
public interface OrganizationMapper 
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
	 * @return
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
	public int insertOrganization(Organization organization);
	
	/**
     * 修改社团
     * 
     * @param organization 社团信息
     * @return 结果
     */
	public int updateOrganization(Organization organization);
	
	/**
     * 删除社团
     * 
     * @param id 社团ID
     * @return 结果
     */
	public int deleteOrganizationById(Integer id);
	
	/**
     * 批量删除社团
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrganizationByIds(String[] ids);

	/**
	 * 根据社团名称查询社团ID
	 * @param name 社团名称
	 * @return
	 */
	public Integer selectIdByName(String name);
	
}