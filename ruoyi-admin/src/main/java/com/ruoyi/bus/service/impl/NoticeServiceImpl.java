package com.ruoyi.bus.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bus.mapper.NoticeMapper;
import com.ruoyi.bus.domain.Notice;
import com.ruoyi.bus.service.INoticeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 公告 服务层实现
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
@Service
public class NoticeServiceImpl implements INoticeService 
{
	@Autowired
	private NoticeMapper noticeMapper;

	/**
     * 查询公告信息
     * 
     * @param id 公告ID
     * @return 公告信息
     */
    @Override
	public Notice selectNoticeById(Integer id)
	{
	    return noticeMapper.selectNoticeById(id);
	}
	
	/**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
	@Override
	public List<Notice> selectNoticeList(Notice notice)
	{
	    return noticeMapper.selectNoticeList(notice);
	}
	
    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
	@Override
	public int insertNotice(Notice notice)
	{
	    return noticeMapper.insertNotice(notice);
	}
	
	/**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
	@Override
	public int updateNotice(Notice notice)
	{
	    return noticeMapper.updateNotice(notice);
	}

	/**
     * 删除公告对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteNoticeByIds(String ids)
	{
		return noticeMapper.deleteNoticeByIds(Convert.toStrArray(ids));
	}
	
}
