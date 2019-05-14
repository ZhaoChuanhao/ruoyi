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
import com.ruoyi.bus.domain.StudentClass;
import com.ruoyi.bus.service.IStudentClassService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 班级 信息操作处理
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
@Controller
@RequestMapping("/bus/studentClass")
public class StudentClassController extends BaseController
{
    private String prefix = "bus/studentClass";
	
	@Autowired
	private IStudentClassService studentClassService;
	
	@RequiresPermissions("bus:studentClass:view")
	@GetMapping()
	public String studentClass()
	{
	    return prefix + "/studentClass";
	}
	
	/**
	 * 查询班级列表
	 */
	@RequiresPermissions("bus:studentClass:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(StudentClass studentClass)
	{
		startPage();
        List<StudentClass> list = studentClassService.selectStudentClassList(studentClass);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出班级列表
	 */
	@RequiresPermissions("bus:studentClass:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StudentClass studentClass)
    {
    	List<StudentClass> list = studentClassService.selectStudentClassList(studentClass);
        ExcelUtil<StudentClass> util = new ExcelUtil<StudentClass>(StudentClass.class);
        return util.exportExcel(list, "studentClass");
    }
	
	/**
	 * 新增班级
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存班级
	 */
	@RequiresPermissions("bus:studentClass:add")
	@Log(title = "班级", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(StudentClass studentClass)
	{		
		return toAjax(studentClassService.insertStudentClass(studentClass));
	}

	/**
	 * 修改班级
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		StudentClass studentClass = studentClassService.selectStudentClassById(id);
		mmap.put("studentClass", studentClass);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存班级
	 */
	@RequiresPermissions("bus:studentClass:edit")
	@Log(title = "班级", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(StudentClass studentClass)
	{		
		return toAjax(studentClassService.updateStudentClass(studentClass));
	}
	
	/**
	 * 删除班级
	 */
	@RequiresPermissions("bus:studentClass:remove")
	@Log(title = "班级", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(studentClassService.deleteStudentClassByIds(ids));
	}
	
}
