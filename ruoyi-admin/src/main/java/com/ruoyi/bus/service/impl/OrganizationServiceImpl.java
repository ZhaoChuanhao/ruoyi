package com.ruoyi.bus.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.bus.domain.Apply;
import com.ruoyi.bus.domain.Mail;
import com.ruoyi.bus.domain.OrganizationDetail;
import com.ruoyi.bus.service.IApplyService;
import com.ruoyi.bus.service.IOrganizationDetailService;
import com.ruoyi.bus.service.IUserService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.utils.MailUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bus.mapper.OrganizationMapper;
import com.ruoyi.bus.domain.Organization;
import com.ruoyi.bus.service.IOrganizationService;
import com.ruoyi.common.core.text.Convert;

import javax.annotation.Resource;

/**
 * 社团 服务层实现
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
@Service
public class OrganizationServiceImpl implements IOrganizationService 
{
	private static final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private IApplyService applyService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IOrganizationDetailService organizationDetailService;
	@Autowired
	private ISysUserService sysUserService;

	/**
     * 查询社团信息
     * 
     * @param id 社团ID
     * @return 社团信息
     */
    @Override
	public Organization selectOrganizationById(Integer id)
	{
	    return organizationMapper.selectOrganizationById(id);
	}

	@Override
	public Organization selectOrganizationByName(String name) {
		return organizationMapper.selectOrganizationByName(name);
	}

	/**
     * 查询社团列表
     * 
     * @param organization 社团信息
     * @return 社团集合
     */
	@Override
	public List<Organization> selectOrganizationList(Organization organization)
	{
	    return organizationMapper.selectOrganizationList(organization);
	}
	
    /**
     * 新增社团
     * 
     * @param organization 社团信息
     * @return 结果
     */
	@Override
	public int insertOrganization(Organization organization)
	{
	    return organizationMapper.insertOrganization(organization);
	}
	
	/**
     * 修改社团
     * 
     * @param organization 社团信息
     * @return 结果
     */
	@Override
	public int updateOrganization(Organization organization)
	{
	    return organizationMapper.updateOrganization(organization);
	}

	/**
     * 删除社团对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOrganizationByIds(String ids)
	{
		return organizationMapper.deleteOrganizationByIds(Convert.toStrArray(ids));
	}

	@Override
	public AjaxResult applyOrganization(Organization organization) throws Exception {
		SysUser user = userService.getUser();
		if (user == null){
			return AjaxResult.error("用户信息为空，申请失败！");
		}
		if (StringUtils.isNotBlank(user.getOrganizationName())){
			return AjaxResult.error("您已是社团成员，请勿申请社团！");
		}
		Apply apply = new Apply();
		apply.setStuId(user.getStuId());
		apply.setStudentNumber(user.getStudentNumber());
		apply.setName(user.getName());
		apply.setSex("0".equals(user.getSex()) ? "男" : "女");
		apply.setOrganizationId(organization.getId());
		apply.setOrganizationCode(organization.getOrganizationCode());
		apply.setOrganizationName(organization.getName());
		apply.setApplyType(0);

		if (CollectionUtils.isNotEmpty(applyService.selectApplyList(apply))){
			return AjaxResult.error("您已成功申请该社团，请勿重复申请！");
		}

		apply.setCreateBy(user.getName());
		apply.setUpdateBy(user.getName());
		apply.setCreateTime(new Date());
		apply.setUpdateTime(apply.getCreateTime());

		if (applyService.insertApply(apply) > 0){
			// 新建线程异步发送邮件
			new Thread(new Runnable() {
				@Override
				public void run() {
					// 发送申请成功邮件给社团团长
					OrganizationDetail organizationDetail = organizationDetailService.selectLeaderByOrganizationId(apply.getOrganizationId());
					SysUser sysUser = sysUserService.selectUserByStuId(organizationDetail.getStuId());
					Mail mail = new Mail();
					mail.setSend(Mail.sysSend);
					mail.setReceive(sysUser.getEmail());
					mail.setTheme("申请信息待处理");
					mail.setContent("尊敬的社长您好：<br/>" +
							"&emsp;&emsp;有新的同学申请您的社团啦！要及时处理哦！<br/><br/>" +
							"------------------------------------------------------------------<br/>" +
							"系统提醒邮件<br/>" +
							new Date().toString());
					// 发送邮件
					try{
						MailUtil.sendMail(mail);
					}catch (Exception e){
						e.printStackTrace();
						log.error("======邮件发送失败！======");
					}
				}
			}).start();
			return AjaxResult.success("申请成功！");
		}
		return AjaxResult.error("申请失败！");
	}

	@Override
	public Integer selectIdByName(String name) {
		return organizationMapper.selectIdByName(name);
	}

}
