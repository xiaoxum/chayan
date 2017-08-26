package com.vkl.ckts.cksy.servacpt.entity;



import java.util.Date;

import com.vkl.ckts.common.entity.IdEntity;
import com.vkl.ckts.common.utils.DateUtil;


/**
 * 1.车辆基本信息表
 * @author jiajun
 * @version 1.0
 */ 



public class TJbxx extends IdEntity  {
	private static final long serialVersionUID = 1L;

	/**
	 * 机动车序号
	 */
	private String xh;

	/**
	 * 不免检原因
	 */
	private String bmjyy;

	/**
	 * 保险终止日期
	 */
	private Date bxzzrq;

	/**
	 * 备注
	 */
	private String bz;

	/**
	 * 初次登记日期
	 */
	private Date ccdjrq;

	/**
	 * 出厂日期
	 */
	private Date ccrq;

	/**
	 * 车辆类型
	 */
	private String cllx;

	/**
	 * 车辆品牌
	 */
	private String clpp1;

	/**
	 * 英文品牌
	 */
	private String clpp2;
	
    /**
     * 车辆识别代号
     */
	private String clsbdh;

	/**
	 * 车辆型号
	 */
	private String clxh;

	/**
	 * 车辆用途
	 */
	private String clyt;

	/**
	 * 车身颜色
	 */
	private String csys;

	/**
	 * 车外廓长
	 */
	private Integer cwkc;

	/**
	 * 车外廓高
	 */
	private Integer cwkg;

	/**
	 * 车外廓宽
	 */
	private Integer cwkk;

	/**
	 * 最近定检日期
	 */
	private Date djrq;

	/**
	 * 抵押标记0-未抵押，1-已抵押
	 */
	private String dybj;

	/**
	 * 发动机号
	 */
	private String fdjh;

	/**
	 * 发动机型号
	 */
	private String fdjxh;

	/**
	 * 发证机关
	 */
	private String fzjg;

	/**
	 *钢板弹簧片数 
	 */
	private Integer gbthps;

	/**
	 * 国产/进口
	 */
	private String gcjk;

	/**
	 * 功率
	 */
	private Double gl;

	/**
	 * 管理部门
	 */
	private String glbm;

	/**
	 * 环保达标情况
	 */
	private String hbdbqk;

	/**
	 * 核定载客
	 */
	private Integer hdzk;

	/**
	 * 核定载质量
	 */
	private Double hdzzl;

	/**
	 * 后轮距
	 */
	private Integer hlj;

	/**
	 * 号牌号码
	 */
	private String hphm;

	/**
	 * 驾驶室后排载客人数
	 */
	private Integer hpzk;

	/**
	 *号牌种类 
	 */
	private String hpzl;

	/**
	 * 货箱内部长度
	 */
	private Integer hxnbcd;

	/**
	 * 货箱内部高度
	 */
	private Integer hxnbgd;

	/**
	 * 货箱内部宽度
	 */
	private Integer hxnbkd;

	/**
	 * 检验合格标志
	 */
	private String jyhgbzbh;

	/**
	 * 轮胎规格
	 */
	private String ltgg;

	/**
	 * 轮胎数
	 */
	private Integer lts;

	/**
	 * 排量
	 */
	private Integer pl;

	/**
	 * 前轮距
	 */
	private Integer qlj;

	/**
	 * 驾驶室前排载客人数
	 */
	private Integer qpzk;

	/**
	 * 强制报废期止
	 */
	private Date qzbfqz;

	/**
	 * 燃料种类
	 */
	private String rlzl;

	/**
	 * 是否免检
	 */
	private String sfmj;

	/**
	 * 是否新能源汽车 1-是 2否
	 */
	private String sfxny;

	/**
	 * 身份证明号码
	 */
	private String sfzmhm;

	/**
	 * 身份证明名称
	 */
	private String sfzmmc;

	/**
	 * 事故车损伤部位情况
	 */
	private String sgcssbwqk;

	/**
	 * 机动车所有人
	 */
	private String syr;

	/**
	 * 使用性质
	 */
	private String syxz;

	/**
	 * 新能源汽车种类 A-纯电动 B-燃料电
	 */
	private String xnyzl;

	/**
	 * 行驶证证芯编号
	 */
	private String xszbh;

	/**
	 * 管理辖区
	 */
	private String xzqh;

	/**
	 * 用途属性
	 */
	private String ytsx;

	/**
	 * 检验有效期止
	 */
	private Date yxqz;

	/**
	 *整备质量 
	 */
	private Integer zbzl;

	/**
	 * 轴距
	 */
	private Integer zj;

	/**
	 * 准牵引总质量
	 */
	private Integer zqyzl;

	/**
	 * 轴数
	 */
	private Integer zs;

	/**
	 * 住所地址行政区划
	 */
	private String zsxzqh;

	/**
	 *机动车状态 
	 */
	private String zt;

	/**
	 *转向形式 
	 */
	private String zxxs;

	/**
	 * 制造厂名称
	 */
	private String zzcmc;

	/**
	 * 制造国
	 */
	private String zzg;
	
	private String ccrqs;

	/**
	 * 总质量
	 */
	private Integer zzl;

	/**
	 * 联系地址行政区划
	 */
	private String zzxzqh;

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getBmjyy() {
		return bmjyy;
	}

	public void setBmjyy(String bmjyy) {
		this.bmjyy = bmjyy;
	}

	public Date getBxzzrq() {
		return bxzzrq;
	}

	public void setBxzzrq(Date bxzzrq) {
		this.bxzzrq = bxzzrq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Date getCcdjrq() {
		return ccdjrq;
	}

	public void setCcdjrq(Date ccdjrq) {
		this.ccdjrq = ccdjrq;
	}

	public Date getCcrq() {
		return ccrq;
	}

	public void setCcrq(Date ccrq) {
		if (ccrq!=null) {
			this.ccrqs=DateUtil.format(ccrq, DateUtil.DATE_FORMAT);
		}
		this.ccrq = ccrq;
	}

	public String getCllx() {
		return cllx;
	}

	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	public String getClpp1() {
		return clpp1;
	}

	public void setClpp1(String clpp1) {
		this.clpp1 = clpp1;
	}

	public String getClpp2() {
		return clpp2;
	}

	public void setClpp2(String clpp2) {
		this.clpp2 = clpp2;
	}

	public String getClsbdh() {
		return clsbdh;
	}

	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}

	public String getClxh() {
		return clxh;
	}

	public void setClxh(String clxh) {
		this.clxh = clxh;
	}

	public String getClyt() {
		return clyt;
	}

	public void setClyt(String clyt) {
		this.clyt = clyt;
	}

	public String getCsys() {
		return csys;
	}

	public void setCsys(String csys) {
		this.csys = csys;
	}

	public Integer getCwkc() {
		return cwkc;
	}

	public void setCwkc(Integer cwkc) {
		this.cwkc = cwkc;
	}

	public Integer getCwkg() {
		return cwkg;
	}

	public void setCwkg(Integer cwkg) {
		this.cwkg = cwkg;
	}

	public Integer getCwkk() {
		return cwkk;
	}

	public void setCwkk(Integer cwkk) {
		this.cwkk = cwkk;
	}

	public Date getDjrq() {
		return djrq;
	}

	public void setDjrq(Date djrq) {
		this.djrq = djrq;
	}

	public String getDybj() {
		return dybj;
	}

	public void setDybj(String dybj) {
		this.dybj = dybj;
	}

	public String getFdjh() {
		return fdjh;
	}

	public void setFdjh(String fdjh) {
		this.fdjh = fdjh;
	}

	public String getFdjxh() {
		return fdjxh;
	}

	public void setFdjxh(String fdjxh) {
		this.fdjxh = fdjxh;
	}

	public String getFzjg() {
		return fzjg;
	}

	public void setFzjg(String fzjg) {
		this.fzjg = fzjg;
	}

	public Integer getGbthps() {
		return gbthps;
	}

	public void setGbthps(Integer gbthps) {
		this.gbthps = gbthps;
	}

	public String getGcjk() {
		return gcjk;
	}

	public void setGcjk(String gcjk) {
		this.gcjk = gcjk;
	}

	public Double getGl() {
		return gl;
	}

	public void setGl(Double gl) {
		this.gl = gl;
	}

	public String getGlbm() {
		return glbm;
	}

	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}

	public String getHbdbqk() {
		return hbdbqk;
	}

	public void setHbdbqk(String hbdbqk) {
		this.hbdbqk = hbdbqk;
	}

	public Integer getHdzk() {
		return hdzk;
	}

	public void setHdzk(Integer hdzk) {
		this.hdzk = hdzk;
	}

	public Double getHdzzl() {
		return hdzzl;
	}

	public void setHdzzl(Double hdzzl) {
		this.hdzzl = hdzzl;
	}

	public Integer getHlj() {
		return hlj;
	}

	public void setHlj(Integer hlj) {
		this.hlj = hlj;
	}

	public String getHphm() {
		return hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public Integer getHpzk() {
		return hpzk;
	}

	public void setHpzk(Integer hpzk) {
		this.hpzk = hpzk;
	}

	public String getHpzl() {
		return hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}

	public Integer getHxnbcd() {
		return hxnbcd;
	}

	public void setHxnbcd(Integer hxnbcd) {
		this.hxnbcd = hxnbcd;
	}

	public Integer getHxnbgd() {
		return hxnbgd;
	}

	public void setHxnbgd(Integer hxnbgd) {
		this.hxnbgd = hxnbgd;
	}

	public Integer getHxnbkd() {
		return hxnbkd;
	}

	public void setHxnbkd(Integer hxnbkd) {
		this.hxnbkd = hxnbkd;
	}

	public String getJyhgbzbh() {
		return jyhgbzbh;
	}

	public void setJyhgbzbh(String jyhgbzbh) {
		this.jyhgbzbh = jyhgbzbh;
	}

	public String getLtgg() {
		return ltgg;
	}

	public void setLtgg(String ltgg) {
		this.ltgg = ltgg;
	}

	public Integer getLts() {
		return lts;
	}

	public void setLts(Integer lts) {
		this.lts = lts;
	}

	public Integer getPl() {
		return pl;
	}

	public void setPl(Integer pl) {
		this.pl = pl;
	}

	public Integer getQlj() {
		return qlj;
	}

	public void setQlj(Integer qlj) {
		this.qlj = qlj;
	}

	public Integer getQpzk() {
		return qpzk;
	}

	public void setQpzk(Integer qpzk) {
		this.qpzk = qpzk;
	}

	public Date getQzbfqz() {
		return qzbfqz;
	}

	public void setQzbfqz(Date qzbfqz) {
		this.qzbfqz = qzbfqz;
	}

	public String getRlzl() {
		return rlzl;
	}

	public void setRlzl(String rlzl) {
		this.rlzl = rlzl;
	}

	public String getSfmj() {
		return sfmj;
	}

	public void setSfmj(String sfmj) {
		this.sfmj = sfmj;
	}

	public String getSfxny() {
		return sfxny;
	}

	public void setSfxny(String sfxny) {
		this.sfxny = sfxny;
	}

	public String getSfzmhm() {
		return sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public String getSfzmmc() {
		return sfzmmc;
	}

	public void setSfzmmc(String sfzmmc) {
		this.sfzmmc = sfzmmc;
	}

	public String getSgcssbwqk() {
		return sgcssbwqk;
	}

	public void setSgcssbwqk(String sgcssbwqk) {
		this.sgcssbwqk = sgcssbwqk;
	}

	public String getSyr() {
		return syr;
	}

	public void setSyr(String syr) {
		this.syr = syr;
	}

	public String getSyxz() {
		return syxz;
	}

	public void setSyxz(String syxz) {
		this.syxz = syxz;
	}

	public String getXnyzl() {
		return xnyzl;
	}

	public void setXnyzl(String xnyzl) {
		this.xnyzl = xnyzl;
	}

	public String getXszbh() {
		return xszbh;
	}

	public void setXszbh(String xszbh) {
		this.xszbh = xszbh;
	}

	public String getXzqh() {
		return xzqh;
	}

	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}

	public String getYtsx() {
		return ytsx;
	}

	public void setYtsx(String ytsx) {
		this.ytsx = ytsx;
	}

	public Date getYxqz() {
		return yxqz;
	}

	public void setYxqz(Date yxqz) {
		this.yxqz = yxqz;
	}

	public Integer getZbzl() {
		return zbzl;
	}

	public void setZbzl(Integer zbzl) {
		this.zbzl = zbzl;
	}

	public Integer getZj() {
		return zj;
	}

	public void setZj(Integer zj) {
		this.zj = zj;
	}

	public Integer getZqyzl() {
		return zqyzl;
	}

	public void setZqyzl(Integer zqyzl) {
		this.zqyzl = zqyzl;
	}

	public Integer getZs() {
		return zs;
	}

	public void setZs(Integer zs) {
		this.zs = zs;
	}

	public String getZsxzqh() {
		return zsxzqh;
	}

	public void setZsxzqh(String zsxzqh) {
		this.zsxzqh = zsxzqh;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getZxxs() {
		return zxxs;
	}

	public void setZxxs(String zxxs) {
		this.zxxs = zxxs;
	}

	public String getZzcmc() {
		return zzcmc;
	}

	public void setZzcmc(String zzcmc) {
		this.zzcmc = zzcmc;
	}

	public String getZzg() {
		return zzg;
	}

	public void setZzg(String zzg) {
		this.zzg = zzg;
	}

	public Integer getZzl() {
		return zzl;
	}

	public void setZzl(Integer zzl) {
		this.zzl = zzl;
	}

	public String getZzxzqh() {
		return zzxzqh;
	}

	public void setZzxzqh(String zzxzqh) {
		this.zzxzqh = zzxzqh;
	}
	public String getCcrqs() {
		return ccrqs;
	}

	public void setCcrqs(String ccrqs) {
		this.ccrqs = ccrqs;
	}


	
}