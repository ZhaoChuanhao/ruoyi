package com.ruoyi.bus.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.bus.domain.Mail;
import com.ruoyi.bus.domain.OrganizationLevel;
import com.ruoyi.bus.domain.Student;
import com.ruoyi.bus.service.IOrganizationLevelService;
import com.ruoyi.bus.service.IStudentService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.utils.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bus.mapper.OrganizationDetailMapper;
import com.ruoyi.bus.domain.OrganizationDetail;
import com.ruoyi.bus.service.IOrganizationDetailService;
import com.ruoyi.common.core.text.Convert;

/**
 * 社团成员 服务层实现
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
@Service
public class OrganizationDetailServiceImpl implements IOrganizationDetailService 
{
	private static final Logger log = LoggerFactory.getLogger(OrganizationDetailServiceImpl.class);

	@Autowired
	private OrganizationDetailMapper organizationDetailMapper;
	@Autowired
	private IOrganizationLevelService organizationLevelService;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private ISysUserService userService;

	/**
     * 查询社团成员信息
     * 
     * @param id 社团成员ID
     * @return 社团成员信息
     */
    @Override
	public OrganizationDetail selectOrganizationDetailById(Integer id)
	{
	    return organizationDetailMapper.selectOrganizationDetailById(id);
	}

	@Override
	public OrganizationDetail selectOrganizationDetailByStuId(Integer stuId) {
		return organizationDetailMapper.selectOrganizationDetailByStuId(stuId);
	}

	@Override
	public OrganizationDetail selectOrganizationDetailByOrganizationId(Integer organizationId) {
		return organizationDetailMapper.selectOrganizationDetailByOrganizationId(organizationId);
	}

	/**
     * 查询社团成员列表
     * 
     * @param organizationDetail 社团成员信息
     * @return 社团成员集合
     */
	@Override
	public List<OrganizationDetail> selectOrganizationDetailList(OrganizationDetail organizationDetail)
	{
	    return organizationDetailMapper.selectOrganizationDetailList(organizationDetail);
	}
	
    /**
     * 新增社团成员
     * 
     * @param organizationDetail 社团成员信息
     * @return 结果
     */
	@Override
	public int insertOrganizationDetail(OrganizationDetail organizationDetail)
	{
	    return organizationDetailMapper.insertOrganizationDetail(organizationDetail);
	}
	
	/**
     * 修改社团成员
     * 
     * @param organizationDetail 社团成员信息
     * @return 结果
     */
	@Override
	public int updateOrganizationDetail(OrganizationDetail organizationDetail)
	{
	    return organizationDetailMapper.updateOrganizationDetail(organizationDetail);
	}

	/**
     * 删除社团成员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOrganizationDetailByIds(String ids)
	{
		return organizationDetailMapper.deleteOrganizationDetailByIds(Convert.toStrArray(ids));
	}

	@Override
	public int deleteOrganizationDetailByStuId(Integer stuId) {
		return organizationDetailMapper.deleteOrganizationDetailByStuId(stuId);
	}

	@Override
	public OrganizationDetail selectLeaderByOrganizationId(Integer organizationId) {
		return organizationDetailMapper.selectLeaderByOrganizationId(organizationId);
	}

	@Override
	public AjaxResult promoteOrganizationDetailById(Integer id) {
		// 查询该成员信息
		OrganizationDetail organizationDetail = organizationDetailMapper.selectOrganizationDetailById(id);
		// 判断该成员等级是否是除了社团团长以外的最高职级，即是否可升职
		List<OrganizationLevel> organizationLevelList = organizationLevelService.selectOrganizationLevelList(new OrganizationLevel());
		// 这里集合中的第一个元素即为除了社团团长以外的最高职级
		OrganizationLevel organizationLevel = organizationLevelList.get(0);
		if (organizationDetail.getLevel().compareTo(organizationLevel.getLevel()) == 0){
			return AjaxResult.error("该成员已是最高职级，无法升职！");
		}
		// 执行升职操作
		organizationDetail.setLevel(String.valueOf(Integer.valueOf(organizationDetail.getLevel()) + 1));
		organizationDetail.setJob(organizationLevelService.selectJobByLevel(organizationDetail.getLevel()));
		if (organizationDetailMapper.updateOrganizationDetail(organizationDetail) > 0){
			// 新建线程异步发送邮件
			new Thread(new Runnable() {
				@Override
				public void run() {
					// 发送升职邮件
					SysUser user = userService.selectUserByStuId(organizationDetail.getStuId());
					// 封装升职邮件信息
					Mail mail = new Mail();
					mail.setSend(Mail.sysSend);
					mail.setReceive(user.getEmail());
					mail.setTheme("您已被升职");
					mail.setContent("尊敬的" + user.getName() + "同学，您好：<br/>" +
							"&emsp;&emsp;恭喜，您已被升职为" + organizationDetail.getJob() +"。<br/>" +
							"&emsp;&emsp;希望您以后能更加努力！<br/><br/>" +
							"--------------------------------------------------------------------<br/>" +
							user.getOrganizationName() + "<br/>" +
							new Date().toString());
					// 发送邮件
					try {
						MailUtil.sendMail(mail);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("======邮件发送失败！======");
					}
				}
			}).start();
			return AjaxResult.success("升职成功！");
		}
		return AjaxResult.error("升职失败！");
	}

	@Override
	public AjaxResult demoteOrganizationDetailById(Integer id) {
		// 查询该成员信息
		OrganizationDetail organizationDetail = organizationDetailMapper.selectOrganizationDetailById(id);
		// 判断该成员等级是否是最低职级，即是否可降职
		List<OrganizationLevel> organizationLevelList = organizationLevelService.selectOrganizationLevelList(new OrganizationLevel());
		// 这里集合中的最后一个元素即为最低职级
		OrganizationLevel organizationLevel = organizationLevelList.get(organizationLevelList.size() - 1);
		if (organizationDetail.getLevel().compareTo(organizationLevel.getLevel()) == 0){
			return AjaxResult.error("该成员已是最低职级，无法降职！");
		}
		// 执行降职操作
		organizationDetail.setLevel(String.valueOf(Integer.valueOf(organizationDetail.getLevel()) - 1));
		organizationDetail.setJob(organizationLevelService.selectJobByLevel(organizationDetail.getLevel()));
		if (organizationDetailMapper.updateOrganizationDetail(organizationDetail) > 0){
			// 新建线程异步发送邮件
			new Thread(new Runnable() {
				@Override
				public void run() {
					// 发送降职邮件
					SysUser user = userService.selectUserByStuId(organizationDetail.getStuId());
					// 封装降职邮件信息
					Mail mail = new Mail();
					mail.setSend(Mail.sysSend);
					mail.setReceive(user.getEmail());
					mail.setTheme("您已被降职");
					mail.setContent("尊敬的" + user.getName() + "同学，您好：<br/>" +
							"&emsp;&emsp;由于某些原因，您已被降职为" + organizationDetail.getJob() +"。<br/>" +
							"&emsp;&emsp;为此我深感抱歉，希望您以后能多加注意！<br/><br/>" +
							"--------------------------------------------------------------------<br/>" +
							user.getOrganizationName() + "<br/>" +
							new Date().toString());
					// 发送邮件
					try {
						MailUtil.sendMail(mail);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("======邮件发送失败！======");
					}
				}
			}).start();
			return AjaxResult.success("降职成功！");
		}
		return AjaxResult.error("降职失败！");
	}

	@Override
	public AjaxResult kickOrganizationDetailById(Integer id) {
		// 查询该成员信息
		OrganizationDetail organizationDetail = organizationDetailMapper.selectOrganizationDetailById(id);
		// 在该社团中删除该成员信息
		organizationDetailMapper.deleteOrganizationDetailById(id);
		// 同时更新该学生信息
		Student student = studentService.selectStudentById(organizationDetail.getStuId());
		student.setOrganizationId(null);
		if (studentService.updateStudentOrganizationId(student) > 0){
			// 新建线程异步发送邮件
			new Thread(new Runnable() {
				@Override
				public void run() {
					// 发送踢出邮件
					SysUser user = userService.selectUserByStuId(student.getId());
					// 封装踢出邮件信息
					Mail mail = new Mail();
					mail.setSend(Mail.sysSend);
					mail.setReceive(user.getEmail());
					mail.setTheme("您已被踢出" + student.getOrganizationName());
					mail.setContent("尊敬的" + student.getName() + "同学，您好：<br/>" +
							"&emsp;&emsp;由于某些原因，您已被踢出我们的社团。<br/>" +
							"&emsp;&emsp;为此我深感抱歉，希望您以后能多加注意！<br/><br/>" +
							"--------------------------------------------------------------------<br/>" +
							student.getOrganizationName() + "<br/>" +
							new Date().toString());
					// 发送邮件
					try {
						MailUtil.sendMail(mail);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("======邮件发送失败！======");
					}
				}
			}).start();
			return AjaxResult.success("踢出成功！");
		}
		return AjaxResult.error("踢出失败！");
	}

}
