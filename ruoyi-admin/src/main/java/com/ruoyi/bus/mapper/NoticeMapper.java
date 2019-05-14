package com.ruoyi.bus.mapper;

import com.ruoyi.bus.domain.Notice;
import java.util.List;	

/**
 * 公告 数据层
 * 
 * @author 赵传昊
 * @date 2019-04-27
 */
public interface NoticeMapper 
{
	/**
     * 查询公告信息
     * 
     * @param id 公告ID
     * @return 公告信息
     */
	public Notice selectNoticeById(Integer id);
	
	/**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
	public List<Notice> selectNoticeList(Notice notice);
	
	/**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
	public int insertNotice(Notice notice);
	
	/**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
	public int updateNotice(Notice notice);
	
	/**
     * 删除公告
     * 
     * @param id 公告ID
     * @return 结果
     */
	public int deleteNoticeById(Integer id);
	
	/**
     * 批量删除公告
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteNoticeByIds(String[] ids);
	
}