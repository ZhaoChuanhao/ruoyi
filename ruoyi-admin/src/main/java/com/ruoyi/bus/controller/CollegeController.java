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
import com.ruoyi.bus.domain.College;
import com.ruoyi.bus.service.ICollegeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 学院 信息操作处理
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
@Controller
@RequestMapping("/bus/college")
public class CollegeController extends BaseController
{
    private String prefix = "bus/college";
	
	@Autowired
	private ICollegeService collegeService;
	
	@RequiresPermissions("bus:college:view")
	@GetMapping()
	public String college()
	{
	    return prefix + "/college";
	}
	
	/**
	 * 查询学院列表
	 */
	@RequiresPermissions("bus:college:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(College college)
	{
		startPage();
        List<College> list = collegeService.selectCollegeList(college);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出学院列表
	 */
	@RequiresPermissions("bus:college:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(College college)
    {
    	List<College> list = collegeService.selectCollegeList(college);
        ExcelUtil<College> util = new ExcelUtil<College>(College.class);
        return util.exportExcel(list, "college");
    }
	
	/**
	 * 新增学院
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存学院
	 */
	@RequiresPermissions("bus:college:add")
	@Log(title = "学院", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(College college)
	{		
		return toAjax(collegeService.insertCollege(college));
	}

	/**
	 * 修改学院
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		College college = collegeService.selectCollegeById(id);
		mmap.put("college", college);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存学院
	 */
	@RequiresPermissions("bus:college:edit")
	@Log(title = "学院", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(College college)
	{		
		return toAjax(collegeService.updateCollege(college));
	}
	
	/**
	 * 删除学院
	 */
	@RequiresPermissions("bus:college:remove")
	@Log(title = "学院", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(collegeService.deleteCollegeByIds(ids));
	}
	
}
