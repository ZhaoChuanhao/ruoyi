package com.ruoyi.bus.controller;

import java.util.List;
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
import com.ruoyi.bus.domain.OrganizationLevel;
import com.ruoyi.bus.service.IOrganizationLevelService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 社团职级 信息操作处理
 * 
 * @author 赵传昊
 * @date 2019-05-14
 */
@Controller
@RequestMapping("/bus/organizationLevel")
public class OrganizationLevelController extends BaseController
{
    private String prefix = "bus/organizationLevel";
	
	@Autowired
	private IOrganizationLevelService organizationLevelService;
	
	@RequiresPermissions("bus:organizationLevel:view")
	@GetMapping()
	public String organizationLevel()
	{
	    return prefix + "/organizationLevel";
	}
	
	/**
	 * 查询社团职级列表
	 */
	@RequiresPermissions("bus:organizationLevel:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(OrganizationLevel organizationLevel)
	{
		startPage();
        List<OrganizationLevel> list = organizationLevelService.selectOrganizationLevelList(organizationLevel);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出社团职级列表
	 */
	@RequiresPermissions("bus:organizationLevel:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrganizationLevel organizationLevel)
    {
    	List<OrganizationLevel> list = organizationLevelService.selectOrganizationLevelList(organizationLevel);
        ExcelUtil<OrganizationLevel> util = new ExcelUtil<OrganizationLevel>(OrganizationLevel.class);
        return util.exportExcel(list, "organizationLevel");
    }
	
	/**
	 * 新增社团职级
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存社团职级
	 */
	@RequiresPermissions("bus:organizationLevel:add")
	@Log(title = "社团职级", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OrganizationLevel organizationLevel)
	{		
		return toAjax(organizationLevelService.insertOrganizationLevel(organizationLevel));
	}

	/**
	 * 修改社团职级
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		OrganizationLevel organizationLevel = organizationLevelService.selectOrganizationLevelById(id);
		mmap.put("organizationLevel", organizationLevel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存社团职级
	 */
	@RequiresPermissions("bus:organizationLevel:edit")
	@Log(title = "社团职级", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OrganizationLevel organizationLevel)
	{		
		return toAjax(organizationLevelService.updateOrganizationLevel(organizationLevel));
	}
	
	/**
	 * 删除社团职级
	 */
	@RequiresPermissions("bus:organizationLevel:remove")
	@Log(title = "社团职级", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(organizationLevelService.deleteOrganizationLevelByIds(ids));
	}
	
}
