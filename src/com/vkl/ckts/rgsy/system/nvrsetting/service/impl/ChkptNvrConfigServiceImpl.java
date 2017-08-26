package com.vkl.ckts.rgsy.system.nvrsetting.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vkl.ckts.common.entity.ChkptFileEntity;
import com.vkl.ckts.common.entity.DeptEntity;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.rgsy.system.nvrsetting.dao.IChkptNvrConfigDao;
import com.vkl.ckts.rgsy.system.nvrsetting.entity.ChkptNvrConfigDto;
import com.vkl.ckts.rgsy.system.nvrsetting.service.IChkptNvrConfigService;
/**
 * nvr 视频配置
 * 
 * @author X260
 *
 */
@Service
public class ChkptNvrConfigServiceImpl extends ServiceImpl<IChkptNvrConfigDao, ChkptNvrConfigDto, String> implements IChkptNvrConfigService{

	@Override
	public List<ChkptNvrConfigDto> findChkptNvrConfigs(String organCode) {
		// TODO Auto-generated method stub
		return super.dao.findChkptNvrConfigs(organCode);
	}

	@Override
	public void insertChkptNvrConfig(ChkptNvrConfigDto c) {
		// TODO Auto-generated method stub
		super.dao.insertChkptNvrConfig(c);
	}

	@Override
	public void updateChkptNvrInfo(ChkptNvrConfigDto c) {
		// TODO Auto-generated method stub
		super.dao.updateChkptNvrInfo(c);
	}

	@Override
	public void deleteChkptLineInfo(String organCode, String checkLineId) {
		// TODO Auto-generated method stub
		super.dao.deleteChkptLineInfo(organCode, checkLineId);
	}

	@Override
	public DeptEntity findDeptById(String id) {
		// TODO Auto-generated method stub
	     return super.dao.findDeptById(id);
	}

	@Override
	public ChkptNvrConfigDto findChNvrConfig(String organCode, String lineId) {
		// TODO Auto-generated method stub
		return super.dao.findChNvrConfig(organCode, lineId);
	}

	@Override
	public List<ChkptFileEntity> findChkptList(ChkptFileEntity chkptFileEntity)
			throws Exception {
		// TODO Auto-generated method stub
		return super.dao.findChkptList(chkptFileEntity);
	}

}
