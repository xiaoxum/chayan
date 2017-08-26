package com.vkl.ckts.rgsy.vehiclereview.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vkl.ckts.common.service.IService;
import com.vkl.ckts.rgsy.vehiclereview.dao.IBlackNameDao;
import com.vkl.ckts.rgsy.vehiclereview.entity.VehBlackName;

/**
 * 车辆黑名单
 * @author xujunhua
 * @date 2017年4月7日
 * @ClassName: IBlackNameService
 */
public interface IBlackNameService extends IService<IBlackNameDao, VehBlackName, String> {
	/**
	 * 查看车辆黑名单详情
	 * @param id 车辆黑名单编号
	 * @return 
	 */
	public VehBlackName bNameDetail(VehBlackName vn);
	/**
	 * 查验记录
	 * @param clsbdh 车辆识别代号
	 * @return
	 */
	public List<VehBlackName> checkRecord(String clsbdh);
	/**
	 * 解除黑名单
	 * @param clsbdh
	 * @throws Exception 
	 */
	public void changeWhite(String clsbdh, HttpServletRequest request) throws Exception;
}
