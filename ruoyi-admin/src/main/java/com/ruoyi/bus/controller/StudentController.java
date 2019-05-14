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
import com.ruoyi.bus.domain.Student;
import com.ruoyi.bus.service.IStudentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 学生 信息操作处理
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
@Controller
@RequestMapping("/bus/student")
public class StudentController extends BaseController
{
    private String prefix = "bus/student";
	
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IUserService userService;
	
	@RequiresPermissions("bus:student:view")
	@GetMapping()
	public String student()
	{
	    return prefix + "/student";
	}
	
	/**
	 * 查询学生列表
	 */
	@RequiresPermissions("bus:student:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Student student)
	{
		startPage();
        List<Student> list = studentService.selectStudentList(student);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出学生列表
	 */
	@RequiresPermissions("bus:student:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Student student)
    {
    	List<Student> list = studentService.selectStudentList(student);
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        return util.exportExcel(list, "student");
    }
	
	/**
	 * 新增学生
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存学生
	 */
	@RequiresPermissions("bus:student:add")
	@Log(title = "学生", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Student student)
	{
		SysUser user = userService.getUser();
		student.setCreateBy(user.getName());
		student.setUpdateBy(user.getName());
		student.setCreateTime(new Date());
		student.setUpdateTime(student.getCreateTime());
		return toAjax(studentService.insertStudent(student));
	}

	/**
	 * 修改学生
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Student student = studentService.selectStudentById(id);
		mmap.put("student", student);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存学生
	 */
	@RequiresPermissions("bus:student:edit")
	@Log(title = "学生", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Student student)
	{
		SysUser user = userService.getUser();
		student.setUpdateBy(user.getName());
		return toAjax(studentService.updateStudent(student));
	}
	
	/**
	 * 删除学生
	 */
	@RequiresPermissions("bus:student:remove")
	@Log(title = "学生", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(studentService.deleteStudentByIds(ids));
	}
	
}
