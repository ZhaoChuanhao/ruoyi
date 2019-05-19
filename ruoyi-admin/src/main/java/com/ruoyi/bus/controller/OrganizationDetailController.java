package com.ruoyi.bus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.bus.domain.Organization;
import com.ruoyi.bus.domain.Student;
import com.ruoyi.bus.service.IOrganizationService;
import com.ruoyi.bus.service.IStudentService;
import com.ruoyi.bus.service.IUserService;
import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.bus.domain.OrganizationDetail;
import com.ruoyi.bus.service.IOrganizationDetailService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 社团成员 信息操作处理
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
@Controller
@RequestMapping("/bus/organizationDetail")
public class OrganizationDetailController extends BaseController
{
    private String prefix = "bus/organizationDetail";
	
	@Autowired
	private IOrganizationDetailService organizationDetailService;
	@Autowired
	private IOrganizationService organizationService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IStudentService studentService;
	
	@RequiresPermissions("bus:organizationDetail:view")
	@GetMapping()
	public String organizationDetail()
	{
	    return prefix + "/organizationDetail";
	}
	
	/**
	 * 查询社团成员列表
	 */
	@RequiresPermissions("bus:organizationDetail:list")
	@RequestMapping("/list")
	@ResponseBody
	public TableDataInfo list(OrganizationDetail organizationDetail)
	{
		startPage();
		SysUser user = userService.getUser();
		List<OrganizationDetail> list = new ArrayList<>();
		// 如果不是管理员，则只能看到本社团成员信息
		if (!user.isAdmin()){
			// 如果该学生没有加入社团，则不能看到任何信息
			if (StringUtils.isBlank(user.getOrganizationName())){
				return getDataTable(list);
			}
			// 否则可以看到本社团的所有成员信息
			Integer organizationId = organizationService.selectIdByName(user.getOrganizationName());
			organizationDetail.setOrganizationId(organizationId);
		}
		// 管理员可以看到所有社团成员信息
        list = organizationDetailService.selectOrganizationDetailList(organizationDetail);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出社团成员列表
	 */
	@RequiresPermissions("bus:organizationDetail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrganizationDetail organizationDetail)
    {
    	List<OrganizationDetail> list = organizationDetailService.selectOrganizationDetailList(organizationDetail);
        ExcelUtil<OrganizationDetail> util = new ExcelUtil<OrganizationDetail>(OrganizationDetail.class);
        return util.exportExcel(list, "organizationDetail");
    }
	
	/**
	 * 新增社团成员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存社团成员
	 */
	@RequiresPermissions("bus:organizationDetail:add")
	@Log(title = "社团成员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OrganizationDetail organizationDetail)
	{
		SysUser user = userService.getUser();
		organizationDetail.setCreateBy(user.getName());
		organizationDetail.setUpdateBy(user.getName());
		organizationDetail.setCreateTime(new Date());
		organizationDetail.setUpdateTime(organizationDetail.getCreateTime());
		// 封装社团ID
		Organization organization = organizationService.selectOrganizationByName(organizationDetail.getOrganizationName());
		if (organization == null){
			return AjaxResult.error("没有该社团信息！");
		}
		organizationDetail.setOrganizationId(organization.getId());
		// 封装学生ID
		Student student = studentService.selectStudentByStudentNumber(organizationDetail.getStudentNumber());
		if (student == null){
			return AjaxResult.error("没有该学生信息！");
		}else if (student.getOrganizationId() != null){
			return AjaxResult.error("该学生已加入社团！");
		}
		organizationDetail.setStuId(student.getId());
		return toAjax(organizationDetailService.insertOrganizationDetail(organizationDetail));
	}

	/**
	 * 修改社团成员
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		OrganizationDetail organizationDetail = organizationDetailService.selectOrganizationDetailById(id);
		mmap.put("organizationDetail", organizationDetail);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存社团成员
	 */
	@RequiresPermissions("bus:organizationDetail:edit")
	@Log(title = "社团成员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OrganizationDetail organizationDetail)
	{
		SysUser user = userService.getUser();
		organizationDetail.setUpdateBy(user.getName());
		// 封装社团ID
		Organization organization = organizationService.selectOrganizationByName(organizationDetail.getOrganizationName());
		organizationDetail.setOrganizationId(organization.getId());
		// 封装学生ID
		Student student = studentService.selectStudentByStudentNumber(organizationDetail.getStudentNumber());
		organizationDetail.setStuId(student.getId());
		return toAjax(organizationDetailService.updateOrganizationDetail(organizationDetail));
	}
	
	/**
	 * 删除社团成员
	 */
	@RequiresPermissions("bus:organizationDetail:remove")
	@Log(title = "社团成员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(organizationDetailService.deleteOrganizationDetailByIds(ids));
	}

	/**
	 * 升职社团成员
	 */
	@RequiresPermissions("bus:organizationDetail:promote")
	@Log(title = "社团成员", businessType = BusinessType.DELETE)
	@PostMapping( "/promote")
	@ResponseBody
	public AjaxResult promote(Integer id)
	{
		return organizationDetailService.promoteOrganizationDetailById(id);
	}

	/**
	 * 降职社团成员
	 */
	@RequiresPermissions("bus:organizationDetail:demote")
	@Log(title = "社团成员", businessType = BusinessType.DELETE)
	@PostMapping( "/demote")
	@ResponseBody
	public AjaxResult demote(Integer id)
	{
		return organizationDetailService.demoteOrganizationDetailById(id);
	}

	/**
	 * 踢出社团成员
	 */
	@RequiresPermissions("bus:organizationDetail:kick")
	@Log(title = "社团成员", businessType = BusinessType.DELETE)
	@PostMapping( "/kick")
	@ResponseBody
	public AjaxResult kick(Integer id)
	{
		return organizationDetailService.kickOrganizationDetailById(id);
	}
	
}
