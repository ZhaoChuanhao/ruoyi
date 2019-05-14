package com.ruoyi.bus.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bus.mapper.OrganizationLevelMapper;
import com.ruoyi.bus.domain.OrganizationLevel;
import com.ruoyi.bus.service.IOrganizationLevelService;
import com.ruoyi.common.core.text.Convert;

/**
 * 社团职级 服务层实现
 * 
 * @author 赵传昊
 * @date 2019-05-14
 */
@Service
public class OrganizationLevelServiceImpl implements IOrganizationLevelService 
{
	@Autowired
	private OrganizationLevelMapper organizationLevelMapper;

	/**
     * 查询社团职级信息
     * 
     * @param id 社团职级ID
     * @return 社团职级信息
     */
    @Override
	public OrganizationLevel selectOrganizationLevelById(Integer id)
	{
	    return organizationLevelMapper.selectOrganizationLevelById(id);
	}
	
	/**
     * 查询社团职级列表
     * 
     * @param organizationLevel 社团职级信息
     * @return 社团职级集合
     */
	@Override
	public List<OrganizationLevel> selectOrganizationLevelList(OrganizationLevel organizationLevel)
	{
	    return organizationLevelMapper.selectOrganizationLevelList(organizationLevel);
	}

	/**
	 * 查询社团职级列表
	 *
	 * @param organizationId 社团ID
	 * @return 社团职级集合
	 */
	@Override
	public List<OrganizationLevel> selectOrganizationLevelListByOrganizationId(Integer organizationId)
	{
		return organizationLevelMapper.selectOrganizationLevelListByOrganizationId(organizationId);
	}
	
    /**
     * 新增社团职级
     * 
     * @param organizationLevel 社团职级信息
     * @return 结果
     */
	@Override
	public int insertOrganizationLevel(OrganizationLevel organizationLevel)
	{
	    return organizationLevelMapper.insertOrganizationLevel(organizationLevel);
	}
	
	/**
     * 修改社团职级
     * 
     * @param organizationLevel 社团职级信息
     * @return 结果
     */
	@Override
	public int updateOrganizationLevel(OrganizationLevel organizationLevel)
	{
	    return organizationLevelMapper.updateOrganizationLevel(organizationLevel);
	}

	/**
     * 删除社团职级对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOrganizationLevelByIds(String ids)
	{
		return organizationLevelMapper.deleteOrganizationLevelByIds(Convert.toStrArray(ids));
	}

	@Override
	public String selectJobByLevel(String level) {
		return organizationLevelMapper.selectJobByLevel(level);
	}

}
