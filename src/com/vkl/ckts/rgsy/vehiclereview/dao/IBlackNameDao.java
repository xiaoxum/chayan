package com.vkl.ckts.rgsy.vehiclereview.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vkl.ckts.common.annotation.Mybatis;
import com.vkl.ckts.common.dao.Dao;
import com.vkl.ckts.rgsy.vehiclereview.entity.VehBlackName;
@Mybatis
public interface IBlackNameDao extends Dao<VehBlackName, String>{
	/**
	 * 查看车辆黑名单详情
	 * @param id 车辆黑名单编号
	 * @return 
	 */
	VehBlackName bNameDetail(VehBlackName vn);
	/**
	 * 查验记录
	 * @param clsbdh 车辆识别代号
	 * @return
	 */
	List<VehBlackName> checkRecord(@Param("clsbdh") String clsbdh);
	/**
	 * 解除黑名单
	 * @param clsbdh
	 */
	void changeWhite(@Param("clsbdh") String clsbdh);
}
