package com.ruoyi.bus.mapper;

import com.ruoyi.bus.domain.OrganizationLevel;
import java.util.List;	

/**
 * 社团职级 数据层
 * 
 * @author 赵传昊
 * @date 2019-05-14
 */
public interface OrganizationLevelMapper 
{
	/**
     * 查询社团职级信息
     * 
     * @param id 社团职级ID
     * @return 社团职级信息
     */
	public OrganizationLevel selectOrganizationLevelById(Integer id);
	
	/**
     * 查询社团职级列表
     * 
     * @param organizationLevel 社团职级信息
     * @return 社团职级集合
     */
	public List<OrganizationLevel> selectOrganizationLevelList(OrganizationLevel organizationLevel);

	/**
	 * 查询社团职级列表
	 *
	 * @param organizationId 社团ID
	 * @return 社团职级集合
	 */
	public List<OrganizationLevel> selectOrganizationLevelListByOrganizationId(Integer organizationId);
	
	/**
     * 新增社团职级
     * 
     * @param organizationLevel 社团职级信息
     * @return 结果
     */
	public int insertOrganizationLevel(OrganizationLevel organizationLevel);
	
	/**
     * 修改社团职级
     * 
     * @param organizationLevel 社团职级信息
     * @return 结果
     */
	public int updateOrganizationLevel(OrganizationLevel organizationLevel);
	
	/**
     * 删除社团职级
     * 
     * @param id 社团职级ID
     * @return 结果
     */
	public int deleteOrganizationLevelById(Integer id);
	
	/**
     * 批量删除社团职级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrganizationLevelByIds(String[] ids);

	/**
	 * 根据职级查询职务名称
	 * @param level
	 * @return
	 */
	public String selectJobByLevel(String level);
	
}