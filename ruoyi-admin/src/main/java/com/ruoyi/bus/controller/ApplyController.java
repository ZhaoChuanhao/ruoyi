package com.ruoyi.bus.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.bus.domain.OrganizationDetail;
import com.ruoyi.bus.domain.Student;
import com.ruoyi.bus.service.*;
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
import com.ruoyi.bus.domain.Apply;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 申请 信息操作处理
 * 
 * @author 赵传昊
 * @date 2019-05-05
 */
@Controller
@RequestMapping("/bus/apply")
public class ApplyController extends BaseController
{
    private String prefix = "bus/apply";
	
	@Autowired
	private IApplyService applyService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IOrganizationDetailService organizationDetailService;
	
	@RequiresPermissions("bus:apply:view")
	@GetMapping()
	public String apply()
	{
	    return prefix + "/apply";
	}
	
	/**
	 * 查询申请列表
	 */
	@RequiresPermissions("bus:apply:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Apply apply)
	{
		startPage();
		SysUser user = userService.getUser();
		if (!user.isAdmin()){
			OrganizationDetail organizationDetail = organizationDetailService.selectOrganizationDetailByStuId(user.getStuId());
			if (organizationDetail != null){
				// 如果是社团团长，则可以看到申请本社团的申请信息
				if ("999".equals(organizationDetail.getLevel())){
					apply.setOrganizationName(user.getOrganizationName());
				}else {
					// 否则是社团团员，只能看到自己的申请信息
					apply.setStuId(user.getStuId());
				}
			}else {
				// 否则该学生还不是社团成员，只能看到自己的申请信息
				apply.setStuId(user.getStuId());
			}
		}
		List<Apply> list = applyService.selectApplyList(apply);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出申请列表
	 */
	@RequiresPermissions("bus:apply:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Apply apply)
    {
    	List<Apply> list = applyService.selectApplyList(apply);
        ExcelUtil<Apply> util = new ExcelUtil<Apply>(Apply.class);
        return util.exportExcel(list, "apply");
    }
	
	/**
	 * 新增申请
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存申请
	 */
	@RequiresPermissions("bus:apply:add")
	@Log(title = "申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Apply apply)
	{		
		return toAjax(applyService.insertApply(apply));
	}

	/**
	 * 修改申请
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Apply apply = applyService.selectApplyById(id);
		mmap.put("apply", apply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存申请
	 */
	@RequiresPermissions("bus:apply:edit")
	@Log(title = "申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Apply apply)
	{		
		return toAjax(applyService.updateApply(apply));
	}
	
	/**
	 * 删除申请
	 */
	@RequiresPermissions("bus:apply:remove")
	@Log(title = "申请", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(applyService.deleteApplyByIds(ids));
	}

	/**
	 * 同意申请
	 */
	@RequiresPermissions("bus:apply:agree")
	@Log(title = "申请", businessType = BusinessType.UPDATE)
	@PostMapping( "/agree")
	@ResponseBody
	public AjaxResult agree(String ids)
	{
		return applyService.agreeApplyByIds(ids);
	}

	/**
	 * 拒绝申请
	 */
	@RequiresPermissions("bus:apply:reject")
	@Log(title = "申请", businessType = BusinessType.UPDATE)
	@PostMapping( "/reject")
	@ResponseBody
	public AjaxResult reject(String ids)
	{
		return toAjax(applyService.rejectApplyByIds(ids));
	}

	/**
	 * 退出社团申请
	 */
	@RequiresPermissions("bus:apply:exit")
	@Log(title = "申请", businessType = BusinessType.DELETE)
	@RequestMapping( "/exit")
	@ResponseBody
	public AjaxResult exit()
	{
		return applyService.exitOrganizationApply();
	}

}
