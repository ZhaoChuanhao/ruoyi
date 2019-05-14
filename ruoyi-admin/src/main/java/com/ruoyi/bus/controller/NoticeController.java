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
import com.ruoyi.bus.domain.Notice;
import com.ruoyi.bus.service.INoticeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 公告 信息操作处理
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
@Controller
@RequestMapping("/bus/notice")
public class NoticeController extends BaseController
{
    private String prefix = "bus/notice";
	
	@Autowired
	private INoticeService noticeService;
	@Autowired
	private IUserService userService;
	
	@RequiresPermissions("bus:notice:view")
	@GetMapping()
	public String notice()
	{
	    return prefix + "/notice";
	}
	
	/**
	 * 查询公告列表
	 */
	@RequiresPermissions("bus:notice:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Notice notice)
	{
		startPage();
        List<Notice> list = noticeService.selectNoticeList(notice);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出公告列表
	 */
	@RequiresPermissions("bus:notice:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Notice notice)
    {
    	List<Notice> list = noticeService.selectNoticeList(notice);
        ExcelUtil<Notice> util = new ExcelUtil<Notice>(Notice.class);
        return util.exportExcel(list, "notice");
    }
	
	/**
	 * 新增公告
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存公告
	 */
	@RequiresPermissions("bus:notice:add")
	@Log(title = "公告", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Notice notice)
	{
		SysUser user = userService.getUser();
		notice.setCreateBy(user.getName());
		notice.setUpdateBy(user.getName());
		notice.setCreateTime(new Date());
		notice.setUpdateTime(notice.getCreateTime());
		return toAjax(noticeService.insertNotice(notice));
	}

	/**
	 * 公告详情
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Notice notice = noticeService.selectNoticeById(id);
		mmap.put("notice", notice);
		return prefix + "/detail";
	}

	/**
	 * 修改公告
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Notice notice = noticeService.selectNoticeById(id);
		mmap.put("notice", notice);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存公告
	 */
	@RequiresPermissions("bus:notice:edit")
	@Log(title = "公告", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Notice notice)
	{
		SysUser user = userService.getUser();
		notice.setUpdateBy(user.getName());
		return toAjax(noticeService.updateNotice(notice));
	}
	
	/**
	 * 删除公告
	 */
	@RequiresPermissions("bus:notice:remove")
	@Log(title = "公告", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(noticeService.deleteNoticeByIds(ids));
	}
	
}
