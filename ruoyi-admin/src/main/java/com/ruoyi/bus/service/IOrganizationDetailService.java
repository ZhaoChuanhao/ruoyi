package com.ruoyi.bus.service;

import com.ruoyi.bus.domain.OrganizationDetail;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;

/**
 * 社团成员 服务层
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
public interface IOrganizationDetailService 
{
	/**
     * 查询社团成员信息
     * 
     * @param id 社团成员ID
     * @return 社团成员信息
     */
	public OrganizationDetail selectOrganizationDetailById(Integer id);

	/**
	 * 查询社团成员信息
	 *
	 * @param stuId 学生ID
	 * @return 社团成员信息
	 */
	public OrganizationDetail selectOrganizationDetailByStuId(Integer stuId);

	/**
	 * 查询社团成员信息
	 *
	 * @param organizationId 社团ID
	 * @return 社团成员信息
	 */
	public OrganizationDetail selectOrganizationDetailByOrganizationId(Integer organizationId);
	
	/**
     * 查询社团成员列表
     * 
     * @param organizationDetail 社团成员信息
     * @return 社团成员集合
     */
	public List<OrganizationDetail> selectOrganizationDetailList(OrganizationDetail organizationDetail);
	
	/**
     * 新增社团成员
     * 
     * @param organizationDetail 社团成员信息
     * @return 结果
     */
	public int insertOrganizationDetail(OrganizationDetail organizationDetail);
	
	/**
     * 修改社团成员
     * 
     * @param organizationDetail 社团成员信息
     * @return 结果
     */
	public int updateOrganizationDetail(OrganizationDetail organizationDetail);
		
	/**
     * 删除社团成员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrganizationDetailByIds(String ids);

	/**
	 * 根据学生ID删除该社团中学生信息
	 * @param stuId
	 * @return
	 */
	public int deleteOrganizationDetailByStuId(Integer stuId);

	/**
	 * 根据社团id查询社团团长信息
	 * @param organizationId
	 * @return
	 */
	public OrganizationDetail selectLeaderByOrganizationId(Integer organizationId);

	/**
	 * 升职社团成员
	 * @param id 社团成员ID
	 * @return
	 */
	public AjaxResult promoteOrganizationDetailById(Integer id);

	/**
	 * 降职社团成员
	 * @param id 社团成员ID
	 * @return
	 */
	public AjaxResult demoteOrganizationDetailById(Integer id);

	/**
	 * 踢出社团成员
	 * @param id 社团成员ID
	 * @return
	 */
	public AjaxResult kickOrganizationDetailById(Integer id);
	
}
