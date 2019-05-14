package com.ruoyi.bus.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.bus.domain.*;
import com.ruoyi.bus.service.*;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.utils.MailUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bus.mapper.ApplyMapper;
import com.ruoyi.common.core.text.Convert;

import javax.annotation.Resource;

/**
 * 申请 服务层实现
 * 
 * @author 赵传昊
 * @date 2019-05-05
 */
@Service
public class ApplyServiceImpl implements IApplyService 
{
	private static final Logger log = LoggerFactory.getLogger(ApplyServiceImpl.class);

	@Autowired
	private ApplyMapper applyMapper;
	@Autowired
	private IOrganizationDetailService organizationDetailService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IOrganizationService organizationService;

	/**
     * 查询申请信息
     * 
     * @param id 申请ID
     * @return 申请信息
     */
    @Override
	public Apply selectApplyById(Integer id)
	{
	    return applyMapper.selectApplyById(id);
	}
	
	/**
     * 查询申请列表
     * 
     * @param apply 申请信息
     * @return 申请集合
     */
	@Override
	public List<Apply> selectApplyList(Apply apply)
	{
		return applyMapper.selectApplyList(apply);
	}
	
    /**
     * 新增申请
     * 
     * @param apply 申请信息
     * @return 结果
     */
	@Override
	public int insertApply(Apply apply)
	{
	    return applyMapper.insertApply(apply);
	}
	
	/**
     * 修改申请
     * 
     * @param apply 申请信息
     * @return 结果
     */
	@Override
	public int updateApply(Apply apply)
	{
	    return applyMapper.updateApply(apply);
	}

	/**
     * 删除申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteApplyByIds(String ids)
	{
		return applyMapper.deleteApplyByIds(Convert.toStrArray(ids));
	}

	@Override
	public AjaxResult agreeApplyByIds(String idStr) {
		SysUser user = userService.getUser();
		String[] ids = Convert.toStrArray(idStr);
		StringBuilder message = new StringBuilder();
		int count = 0;
		// 把申请者信息添加到社团成员表中
		for (String id : ids){
			Apply apply = applyMapper.selectApplyById(Integer.valueOf(id));
			// 查询该学生信息及其用户信息
			Student student = studentService.selectStudentById(apply.getStuId());
			SysUser sysUser = sysUserService.selectUserByStuId(apply.getStuId());
			Mail mail = new Mail();
			// 判断该学生是加入申请还是退出申请
			if (apply.getApplyType() == 0){

				if (student.getOrganizationId() != null){
					// 该学生已加入其它社团
					count++;
					message.append("\n" + student.getName() + "已加入其它社团！");
					continue;
				}
				OrganizationDetail organizationDetail = new OrganizationDetail();
				organizationDetail.setOrganizationId(apply.getOrganizationId());
				organizationDetail.setStuId(apply.getStuId());
				organizationDetail.setStudentNumber(apply.getStudentNumber());
				organizationDetail.setName(apply.getName());
				organizationDetail.setLevel("1");
				organizationDetail.setJob("普通成员");
				organizationDetail.setCreateBy(user.getName());
				organizationDetail.setUpdateBy(user.getName());
				organizationDetail.setCreateTime(new Date());
				organizationDetail.setUpdateTime(organizationDetail.getCreateTime());
				organizationDetailService.insertOrganizationDetail(organizationDetail);

				// 同时更新该学生信息
				student.setOrganizationId(apply.getOrganizationId());
				studentService.updateStudentOrganizationId(student);

				// 封装同意邮件信息
				mail.setSend(Mail.sysSend);
				mail.setReceive(sysUser.getEmail());
				mail.setTheme("社团申请已通过");
				mail.setContent("尊敬的" + sysUser.getName() + "同学，您好：<br/>" +
						"&emsp;&emsp;您的申请已通过审核，非常感谢您选择申请我们社团。<br/>" +
						"&emsp;&emsp;很荣幸您能加入我们社团，希望我们能在今后的校园生活中一起成长！<br/><br/>" +
						"------------------------------------------------------------------<br/>" +
						apply.getOrganizationName() + "<br/>" +
						new Date().toString());

			}else {
				// 删除该学生在社团中的信息
				organizationDetailService.deleteOrganizationDetailByStuId(student.getId());

				// 同时更新该学生信息
				student.setOrganizationId(null);
				studentService.updateStudentOrganizationId(student);

				// 封装同意邮件信息
				mail.setSend(Mail.sysSend);
				mail.setReceive(sysUser.getEmail());
				mail.setTheme("退出申请已通过");
				mail.setContent("尊敬的" + sysUser.getName() + "同学，您好：<br/>" +
						"&emsp;&emsp;您的申请已通过审核，非常感谢在此期间您为我们社团做出的贡献。<br/>" +
						"&emsp;&emsp;希望您能在今后能有更好的成长空间！<br/><br/>" +
						"------------------------------------------------------------------<br/>" +
						apply.getOrganizationName() + "<br/>" +
						new Date().toString());
			}

			// 发送邮件
			try {
				MailUtil.sendMail(mail);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("======邮件发送失败！======");
			}
		}
		// 删除申请者信息
		applyMapper.deleteApplyByIds(ids);
		if (count > 0 && count < ids.length){
			return AjaxResult.success((ids.length - count) + "条记录操作成功！\n" + count + "条记录操作失败！" + message);
		}
		return AjaxResult.success("操作成功！");
	}

	@Override
	public int rejectApplyByIds(String idStr) {
		String[] ids = Convert.toStrArray(idStr);
		// 发送拒绝信息邮件
		for (String id : ids){
			Apply apply = applyMapper.selectApplyById(Integer.valueOf(id));
			SysUser user = sysUserService.selectUserByStuId(apply.getStuId());

			// 封装拒绝邮件信息
			Mail mail = new Mail();
			mail.setSend(Mail.sysSend);
			mail.setReceive(user.getEmail());
			if (apply.getApplyType() == 0){
				mail.setTheme("社团申请被拒绝");
				mail.setContent("尊敬的" + user.getName() + "同学，您好：<br/>" +
						"&emsp;&emsp;非常感谢您选择申请我们社团，但是很遗憾，由于某些原因，您没有通过我们的审核。<br/>" +
						"&emsp;&emsp;所以您不能加入我们社团，为此我深感抱歉，希望您能有更好的选择！<br/><br/>" +
						"--------------------------------------------------------------------<br/>" +
						apply.getOrganizationName() + "<br/>" +
						new Date().toString());
			}else {
				mail.setTheme("退出申请被拒绝");
				mail.setContent("尊敬的" + user.getName() + "同学，您好：<br/>" +
						"&emsp;&emsp;非常抱歉让您在本社团中感到不悦，但是我们以后一定会做的更好！<br/>" +
						"&emsp;&emsp;所以希望您不要退出本社团，为此我深感抱歉，希望您再三考虑！<br/><br/>" +
						"--------------------------------------------------------------------<br/>" +
						apply.getOrganizationName() + "<br/>" +
						new Date().toString());
			}

			// 发送邮件
			try {
				MailUtil.sendMail(mail);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("======邮件发送失败！======");
			}
		}

		// 删除申请者信息
		return applyMapper.deleteApplyByIds(ids);
	}

	@Override
	public AjaxResult exitOrganizationApply() {
		SysUser user = userService.getUser();
		Student student = studentService.selectStudentById(user.getStuId());
		Organization organization = organizationService.selectOrganizationById(student.getOrganizationId());
		if(student.getOrganizationId() == null){
			return AjaxResult.error("您还未加入任何社团！");
		}
		Apply apply = new Apply();
		apply.setStuId(student.getId());
		apply.setStudentNumber(student.getStudentNumber());
		apply.setName(student.getName());
		apply.setOrganizationId(student.getOrganizationId());
		apply.setOrganizationCode(organization.getOrganizationCode());
		apply.setOrganizationName(student.getOrganizationName());
		apply.setSex(student.getSex());
		apply.setApplyType(1);

		if (CollectionUtils.isNotEmpty(applyMapper.selectApplyList(apply))){
			return AjaxResult.error("您已发送退出社团申请，请勿重复申请！");
		}

		apply.setCreateBy(user.getName());
		apply.setUpdateBy(user.getName());
		apply.setCreateTime(new Date());
		apply.setUpdateTime(apply.getCreateTime());

		if (applyMapper.insertApply(apply) > 0){
			// 新建线程异步执行发送邮件操作
			new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						// 发送退出申请邮件给社团团长
						OrganizationDetail organizationDetail = organizationDetailService.selectLeaderByOrganizationId(apply.getOrganizationId());
						SysUser sysUser = sysUserService.selectUserByStuId(organizationDetail.getStuId());
						Mail mail = new Mail();
						mail.setSend(Mail.sysSend);
						mail.setReceive(sysUser.getEmail());
						mail.setTheme("退出社团申请待处理");
						mail.setContent("尊敬的社长您好：<br/>" +
								"&emsp;&emsp;有同学要退出您的社团啦！要及时处理哦！<br/><br/>" +
								"------------------------------------------------------------------<br/>" +
								"系统提醒邮件<br/>" +
								new Date().toString());
						MailUtil.sendMail(mail);
					}catch (Exception e){
						e.printStackTrace();
						log.error("======邮件发送失败！======");
					}
				}
			}).start();
			return AjaxResult.success("退出申请已发送！");
		}

		return AjaxResult.error();
	}

}
