package com.vkl.ckts.cksy.vehicleinformation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.ckts.cksy.vehicleinformation.dao.IVedioRecordDaos;
import com.vkl.ckts.cksy.vehicleinformation.entity.ChkptNvrConfigDto;
import com.vkl.ckts.cksy.vehicleinformation.entity.CkInfoEntityDto;
import com.vkl.ckts.cksy.vehicleinformation.entity.VedioRecordDto;
import com.vkl.ckts.cksy.vehicleinformation.nvr.TestNVR;
import com.vkl.ckts.cksy.vehicleinformation.service.IVedioRecordServices;
import com.vkl.ckts.common.controller.SpringContextHolder;
import com.vkl.ckts.common.service.impl.ServiceImpl;
import com.vkl.ckts.common.utils.XMLUtils;
/**
 * 查验视频记录Service实现类
 * 
 * @author xiaoxu
 *
 */
@Service
@Transactional
public class VedioRecordServicesImpl extends ServiceImpl<IVedioRecordDaos,VedioRecordDto,String>
		implements IVedioRecordServices{

	@Override
	public List<VedioRecordDto> findBySrln(String srln,String rckCount) {
		return super.dao.findBySrln(srln,rckCount);
	}
    /**
     * 获取查验信息表
     */
	@Override
	public String videoDownLoad(String srln, String rckCount,
			String videoAngle) {
		// TODO Auto-generated method stub
		CkInfoEntityDto ckInfoEntityDto=super.dao.findckinfobysrln(srln, rckCount);
		if (ckInfoEntityDto==null) {
			return "no-chinfo";
		}
		ChkptNvrConfigDto chkptNvrConfigDto = super.dao.findVideoConfigByBh(ckInfoEntityDto.getOrganCode(), ckInfoEntityDto.getCyxbh());
		if (chkptNvrConfigDto!=null) {
			if ("1".equals(videoAngle)) {
				chkptNvrConfigDto.setStartTime(ckInfoEntityDto.getPdaStartTime());
				chkptNvrConfigDto.setEndTime(ckInfoEntityDto.getPdaEndTime());
			}
           if ("2".equals(videoAngle)) {
        	   chkptNvrConfigDto.setStartTime(ckInfoEntityDto.getPdaPzStartTime());
				chkptNvrConfigDto.setEndTime(ckInfoEntityDto.getPdaPzEndTime());
			}
            final String fileName=srln+"_"+rckCount+"_"+videoAngle+".mp4";
	    	final  ChkptNvrConfigDto chkptInfoDto1=chkptNvrConfigDto;
	    	final String rckCount1=rckCount;
	    	final String videoAngle1=videoAngle;
	    	final String srln1=srln;
	    	 new Thread(new  Runnable() {
	 			public void run() {
	 				IVedioRecordDaos vIVedioRecordDaos=SpringContextHolder.getBean(IVedioRecordDaos.class);
	 				VedioRecordDto vidRecordDto = vIVedioRecordDaos.findVideoInfo(srln1, rckCount1, videoAngle1);
	 				String filePath = TestNVR.uploadDvrVedio2(chkptInfoDto1, srln1,fileName);
                    if (vidRecordDto==null) {
                    	VedioRecordDto vedioRecordDto = new VedioRecordDto();
    	 				vedioRecordDto.setDvrChannel(Integer.parseInt(chkptInfoDto1.getVideoDownLoadChannel()));
    	 				vedioRecordDto.setDvrProt(chkptInfoDto1.getServPort());
    	 				vedioRecordDto.setDvrUser(chkptInfoDto1.getNvrUser());
    	 				vedioRecordDto.setDvrPwd(chkptInfoDto1.getNvrPwd());
    	 				vedioRecordDto.setEndTime(chkptInfoDto1.getEndTime());
    	 				vedioRecordDto.setSrln(srln1);
    	 				vedioRecordDto.setStartTime(chkptInfoDto1.getStartTime());
    	 				vedioRecordDto.setVedioAngle(videoAngle1);
    	 				vedioRecordDto.setVedioUrl(filePath+"/"+fileName);
    	 				vedioRecordDto.setRdkCount(Integer.parseInt(rckCount1));
    	 				vIVedioRecordDaos.insertVideoInfo(vedioRecordDto);
					}
	 			}
	 		}).start();
           
           
           
           
           
		}
		
		
		
		
		
		
		
		return null;
	}

}
