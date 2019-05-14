package com.ruoyi.bus.controller;

import java.util.Date;
import java.util.List;

import com.ruoyi.bus.service.IUserService;
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
import com.ruoyi.bus.domain.Activity;
import com.ruoyi.bus.service.IActivityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 活动 信息操作处理
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
@Controller
@RequestMapping("/bus/activity")
public class ActivityController extends BaseController
{
    private String prefix = "bus/activity";
	
	@Autowired
	private IActivityService activityService;
	@Autowired
	private IUserService userService;
	
	@RequiresPermissions("bus:activity:view")
	@GetMapping()
	public String activity()
	{
	    return prefix + "/activity";
	}
	
	/**
	 * 查询活动列表
	 */
	@RequiresPermissions("bus:activity:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Activity activity)
	{
		startPage();
        List<Activity> list = activityService.selectActivityList(activity);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出活动列表
	 */
	@RequiresPermissions("bus:activity:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Activity activity)
    {
    	List<Activity> list = activityService.selectActivityList(activity);
        ExcelUtil<Activity> util = new ExcelUtil<Activity>(Activity.class);
        return util.exportExcel(list, "activity");
    }
	
	/**
	 * 新增活动
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存活动
	 */
	@RequiresPermissions("bus:activity:add")
	@Log(title = "活动", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Activity activity)
	{
		SysUser user = userService.getUser();
		activity.setCreateBy(user.getName());
		activity.setUpdateBy(user.getName());
		activity.setCreateTime(new Date());
		activity.setUpdateTime(activity.getCreateTime());
		return toAjax(activityService.insertActivity(activity));
	}

	/**
	 * 活动详情
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Activity activity = activityService.selectActivityById(id);
		mmap.put("activity", activity);
		return prefix + "/detail";
	}

	/**
	 * 修改活动
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Activity activity = activityService.selectActivityById(id);
		mmap.put("activity", activity);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存活动
	 */
	@RequiresPermissions("bus:activity:edit")
	@Log(title = "活动", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Activity activity)
	{
		SysUser user = userService.getUser();
		activity.setUpdateBy(user.getName());
		return toAjax(activityService.updateActivity(activity));
	}
	
	/**
	 * 删除活动
	 */
	@RequiresPermissions("bus:activity:remove")
	@Log(title = "活动", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(activityService.deleteActivityByIds(ids));
	}
	
}
