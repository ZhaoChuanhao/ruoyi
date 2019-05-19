package com.ruoyi.bus.controller;

import java.util.Date;
import java.util.List;

import com.ruoyi.bus.domain.OrganizationDetail;
import com.ruoyi.bus.service.IOrganizationDetailService;
import com.ruoyi.bus.service.IUserService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.bus.domain.Organization;
import com.ruoyi.bus.service.IOrganizationService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 社团 信息操作处理
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
@Controller
@RequestMapping("/bus/organization")
public class OrganizationController extends BaseController
{
    private String prefix = "bus/organization";
	
	@Autowired
	private IOrganizationService organizationService;
	@Autowired
	private IOrganizationDetailService organizationDetailService;
	@Autowired
	private IUserService userService;
	
	@RequiresPermissions("bus:organization:view")
	@GetMapping()
	public String organization()
	{
	    return prefix + "/organization";
	}
	
	/**
	 * 查询社团列表
	 */
	@RequiresPermissions("bus:organization:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Organization organization)
	{
		startPage();
        List<Organization> list = organizationService.selectOrganizationList(organization);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出社团列表
	 */
	@RequiresPermissions("bus:organization:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Organization organization)
    {
    	List<Organization> list = organizationService.selectOrganizationList(organization);
        ExcelUtil<Organization> util = new ExcelUtil<Organization>(Organization.class);
        return util.exportExcel(list, "organization");
    }
	
	/**
	 * 新增社团
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存社团
	 */
	@RequiresPermissions("bus:organization:add")
	@Log(title = "社团", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Organization organization)
	{
		SysUser user = userService.getUser();
		organization.setCreateBy(user.getName());
		organization.setUpdateBy(user.getName());
		organization.setCreateTime(new Date());
		organization.setUpdateTime(organization.getCreateTime());
		return organizationService.insertOrganization(organization);
	}

	/**
	 * 申请社团
	 */
	@RequiresPermissions("bus:organization:apply")
	@Log(title = "社团", businessType = BusinessType.DELETE)
	@PostMapping( "/apply")
	@ResponseBody
	public AjaxResult apply(Integer id)
	{
		try {
			Organization organization = organizationService.selectOrganizationById(id);
			return organizationService.applyOrganization(organization);
		}catch (Exception e){
			e.printStackTrace();
		}
		return AjaxResult.error();
	}

	/**
	 * 社团详情
	 */
	/*@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, ModelMap mmap)
	{
		OrganizationDetail organizationDetail = organizationDetailService.selectOrganizationDetailByOrganizationId(id);
		mmap.put("organization", organizationDetail);
		return prefix + "/edit";
	}*/

	/**
	 * 修改社团
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Organization organization = organizationService.selectOrganizationById(id);
		mmap.put("organization", organization);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存社团
	 */
	@RequiresPermissions("bus:organization:edit")
	@Log(title = "社团", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Organization organization)
	{
		SysUser user = userService.getUser();
		organization.setUpdateBy(user.getName());
		return toAjax(organizationService.updateOrganization(organization));
	}
	
	/**
	 * 删除社团
	 */
	@RequiresPermissions("bus:organization:remove")
	@Log(title = "社团", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return organizationService.deleteOrganizationByIds(ids);
	}
	
}
