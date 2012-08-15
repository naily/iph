package cn.fam1452.action.bo;

public class MetaDataBo {
	private String ids ;
	private String thumbnailFilePath ; //元数据缩略图文件路径
	
	public String getThumbnailFilePath() {
		return thumbnailFilePath;
	}
	public void setThumbnailFilePath(String thumbnailFilePath) {
		this.thumbnailFilePath = thumbnailFilePath;
	}
	//---------------------------
	public String getTpCat(){
		return this.cateName + this.cateCode + this.cateStd ;
	}
	private String IdInfo ;
	private String datId ;
	private String resTitle ;
	private String engTitle ;
	private String language ;
	private String keyword ;
	private String uptDate ; //最新修改日期
	private String pubDate ; //发布日期
	
	private String contInfo ; //提供电离层数据库数据集内容特征的描述信息
	private String abstract1 ;
	private String tpCat ; //数据集分类
	private String cateName ;
	private String cateCode ;
	private String cateStd ; // 分类标准
	
	private String spatialCov ; //空间范围
	private String planeCov ; //7.2.2.3.1.	平面范围
	private String coordCov ; //7.2.2.3.1.1.	坐标范围
	private String lftBtmCoord ;
	private String rightTopCoord ;
	private String coordPoint ;
	private String corSys ;
	private String proSys ;
	private String spaRes ;
	private String equScale ;
	private String gridSize ;
	private String posDes ;
	private String verCov ;
	private String altCov ;
	private String topVer ;
	private String lowVer ;
	private String altPoint ;
	private String altDesc ;
	private String atiSys ;
	private String atiInterval ;
	private String timeCov ;
	private String timePeriod ;
	private String begDate ;
	private String endDate ;
	private String timePoint ;
	private String timeInterval ;
	private String dataQuant ; //数据量
	
	private String srcInfo ;
	private String srcType ;
	private String site ;
	private String siteID ;
	private String siteName ;
	private String siteCoordinate ;
	private String sitePosDesc ;
	private String siteContact ;
	private String instrument ; //观测仪器
	private String instID ;
	private String instName ;
	private String instCat ;
	private String instContact ;
	
	private String prcMethod ;
	private String methodName ;
	private String methodDesc ;
	private String originalSrc ;
	private String originalDesc ;
	private String originalContact ;
	private String qaDesc ;
	private String prdContact ;
	
	private String dissInfo ;
	private String dataStorage ;
	private String dataType ;
	private String dataFormat ;
	private String formatVersion ;
	private String serviceInfo ;
	private String serviceType ;
	private String serviceParas ;
	private String paraName ;
	private String paraValue  ;
	private String accessClass ;
	private String useDesc ;
	private String copyRight ;
	private String copyRightDesc ;
	private String citation ;
	private String dissContact ;
	private String mdDesp ;
	private String mdId ;
	private String mdContact ;
	private String mdDateUpd ;
	
	private String pointCoordType ;
	private String pointLong ;
	private String pointLat ;
	private String respParty ;
	private String orgName ;
	private String phone ;
	private String address ;
	private String postcode ;
	private String eMail ;
	
	
	
	
	
	
	
	
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getIdInfo() {
		return IdInfo;
	}
	public void setIdInfo(String idInfo) {
		IdInfo = idInfo;
	}
	public String getDatId() {
		return datId;
	}
	public void setDatId(String datId) {
		this.datId = datId;
	}
	public String getResTitle() {
		return resTitle;
	}
	public void setResTitle(String resTitle) {
		this.resTitle = resTitle;
	}
	public String getEngTitle() {
		return engTitle;
	}
	public void setEngTitle(String engTitle) {
		this.engTitle = engTitle;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getUptDate() {
		return uptDate;
	}
	public void setUptDate(String uptDate) {
		this.uptDate = uptDate;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getContInfo() {
		return contInfo;
	}
	public void setContInfo(String contInfo) {
		this.contInfo = contInfo;
	}
	public String getAbstract1() {
		return abstract1;
	}
	public void setAbstract1(String abstract1) {
		this.abstract1 = abstract1;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getCateCode() {
		return cateCode;
	}
	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}
	public String getCateStd() {
		return cateStd;
	}
	public void setCateStd(String cateStd) {
		this.cateStd = cateStd;
	}
	public String getSpatialCov() {
		return spatialCov;
	}
	public void setSpatialCov(String spatialCov) {
		this.spatialCov = spatialCov;
	}
	public String getPlaneCov() {
		return planeCov;
	}
	public void setPlaneCov(String planeCov) {
		this.planeCov = planeCov;
	}
	public String getCoordCov() {
		return coordCov;
	}
	public void setCoordCov(String coordCov) {
		this.coordCov = coordCov;
	}
	public String getLftBtmCoord() {
		return lftBtmCoord;
	}
	public void setLftBtmCoord(String lftBtmCoord) {
		this.lftBtmCoord = lftBtmCoord;
	}
	public String getRightTopCoord() {
		return rightTopCoord;
	}
	public void setRightTopCoord(String rightTopCoord) {
		this.rightTopCoord = rightTopCoord;
	}
	public String getCoordPoint() {
		return coordPoint;
	}
	public void setCoordPoint(String coordPoint) {
		this.coordPoint = coordPoint;
	}
	public String getCorSys() {
		return corSys;
	}
	public void setCorSys(String corSys) {
		this.corSys = corSys;
	}
	public String getProSys() {
		return proSys;
	}
	public void setProSys(String proSys) {
		this.proSys = proSys;
	}
	public String getSpaRes() {
		return spaRes;
	}
	public void setSpaRes(String spaRes) {
		this.spaRes = spaRes;
	}
	public String getEquScale() {
		return equScale;
	}
	public void setEquScale(String equScale) {
		this.equScale = equScale;
	}
	public String getGridSize() {
		return gridSize;
	}
	public void setGridSize(String gridSize) {
		this.gridSize = gridSize;
	}
	public String getPosDes() {
		return posDes;
	}
	public void setPosDes(String posDes) {
		this.posDes = posDes;
	}
	public String getVerCov() {
		return verCov;
	}
	public void setVerCov(String verCov) {
		this.verCov = verCov;
	}
	public String getAltCov() {
		return altCov;
	}
	public void setAltCov(String altCov) {
		this.altCov = altCov;
	}
	public String getTopVer() {
		return topVer;
	}
	public void setTopVer(String topVer) {
		this.topVer = topVer;
	}
	public String getLowVer() {
		return lowVer;
	}
	public void setLowVer(String lowVer) {
		this.lowVer = lowVer;
	}
	public String getAltPoint() {
		return altPoint;
	}
	public void setAltPoint(String altPoint) {
		this.altPoint = altPoint;
	}
	public String getAltDesc() {
		return altDesc;
	}
	public void setAltDesc(String altDesc) {
		this.altDesc = altDesc;
	}
	public String getAtiSys() {
		return atiSys;
	}
	public void setAtiSys(String atiSys) {
		this.atiSys = atiSys;
	}
	public String getAtiInterval() {
		return atiInterval;
	}
	public void setAtiInterval(String atiInterval) {
		this.atiInterval = atiInterval;
	}
	public String getTimeCov() {
		return timeCov;
	}
	public void setTimeCov(String timeCov) {
		this.timeCov = timeCov;
	}
	public String getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}
	public String getBegDate() {
		return begDate;
	}
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTimePoint() {
		return timePoint;
	}
	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}
	public String getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}
	public String getDataQuant() {
		return dataQuant;
	}
	public void setDataQuant(String dataQuant) {
		this.dataQuant = dataQuant;
	}
	public String getSrcInfo() {
		return srcInfo;
	}
	public void setSrcInfo(String srcInfo) {
		this.srcInfo = srcInfo;
	}
	public String getSrcType() {
		return srcType;
	}
	public void setSrcType(String srcType) {
		this.srcType = srcType;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getSiteID() {
		return siteID;
	}
	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteCoordinate() {
		return siteCoordinate;
	}
	public void setSiteCoordinate(String siteCoordinate) {
		this.siteCoordinate = siteCoordinate;
	}
	public String getSitePosDesc() {
		return sitePosDesc;
	}
	public void setSitePosDesc(String sitePosDesc) {
		this.sitePosDesc = sitePosDesc;
	}
	public String getSiteContact() {
		return siteContact;
	}
	public void setSiteContact(String siteContact) {
		this.siteContact = siteContact;
	}
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	public String getInstID() {
		return instID;
	}
	public void setInstID(String instID) {
		this.instID = instID;
	}
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}
	public String getInstCat() {
		return instCat;
	}
	public void setInstCat(String instCat) {
		this.instCat = instCat;
	}
	public String getInstContact() {
		return instContact;
	}
	public void setInstContact(String instContact) {
		this.instContact = instContact;
	}
	public String getPrcMethod() {
		return prcMethod;
	}
	public void setPrcMethod(String prcMethod) {
		this.prcMethod = prcMethod;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getMethodDesc() {
		return methodDesc;
	}
	public void setMethodDesc(String methodDesc) {
		this.methodDesc = methodDesc;
	}
	public String getOriginalSrc() {
		return originalSrc;
	}
	public void setOriginalSrc(String originalSrc) {
		this.originalSrc = originalSrc;
	}
	public String getOriginalDesc() {
		return originalDesc;
	}
	public void setOriginalDesc(String originalDesc) {
		this.originalDesc = originalDesc;
	}
	public String getOriginalContact() {
		return originalContact;
	}
	public void setOriginalContact(String originalContact) {
		this.originalContact = originalContact;
	}
	public String getQaDesc() {
		return qaDesc;
	}
	public void setQaDesc(String qaDesc) {
		this.qaDesc = qaDesc;
	}
	public String getPrdContact() {
		return prdContact;
	}
	public void setPrdContact(String prdContact) {
		this.prdContact = prdContact;
	}
	public String getDissInfo() {
		return dissInfo;
	}
	public void setDissInfo(String dissInfo) {
		this.dissInfo = dissInfo;
	}
	public String getDataStorage() {
		return dataStorage;
	}
	public void setDataStorage(String dataStorage) {
		this.dataStorage = dataStorage;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataFormat() {
		return dataFormat;
	}
	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}
	public String getFormatVersion() {
		return formatVersion;
	}
	public void setFormatVersion(String formatVersion) {
		this.formatVersion = formatVersion;
	}
	public String getServiceInfo() {
		return serviceInfo;
	}
	public void setServiceInfo(String serviceInfo) {
		this.serviceInfo = serviceInfo;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServiceParas() {
		return serviceParas;
	}
	public void setServiceParas(String serviceParas) {
		this.serviceParas = serviceParas;
	}
	public String getParaName() {
		return paraName;
	}
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	public String getParaValue() {
		return paraValue;
	}
	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	public String getAccessClass() {
		return accessClass;
	}
	public void setAccessClass(String accessClass) {
		this.accessClass = accessClass;
	}
	public String getUseDesc() {
		return useDesc;
	}
	public void setUseDesc(String useDesc) {
		this.useDesc = useDesc;
	}
	public String getCopyRight() {
		return copyRight;
	}
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}
	public String getCopyRightDesc() {
		return copyRightDesc;
	}
	public void setCopyRightDesc(String copyRightDesc) {
		this.copyRightDesc = copyRightDesc;
	}
	public String getCitation() {
		return citation;
	}
	public void setCitation(String citation) {
		this.citation = citation;
	}
	public String getDissContact() {
		return dissContact;
	}
	public void setDissContact(String dissContact) {
		this.dissContact = dissContact;
	}
	public String getMdDesp() {
		return mdDesp;
	}
	public void setMdDesp(String mdDesp) {
		this.mdDesp = mdDesp;
	}
	public String getMdId() {
		return mdId;
	}
	public void setMdId(String mdId) {
		this.mdId = mdId;
	}
	public String getMdContact() {
		return mdContact;
	}
	public void setMdContact(String mdContact) {
		this.mdContact = mdContact;
	}
	public String getMdDateUpd() {
		return mdDateUpd;
	}
	public void setMdDateUpd(String mdDateUpd) {
		this.mdDateUpd = mdDateUpd;
	}
	public String getPointCoordType() {
		return pointCoordType;
	}
	public void setPointCoordType(String pointCoordType) {
		this.pointCoordType = pointCoordType;
	}
	public String getPointLong() {
		return pointLong;
	}
	public void setPointLong(String pointLong) {
		this.pointLong = pointLong;
	}
	public String getPointLat() {
		return pointLat;
	}
	public void setPointLat(String pointLat) {
		this.pointLat = pointLat;
	}
	public String getRespParty() {
		return respParty;
	}
	public void setRespParty(String respParty) {
		this.respParty = respParty;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String mail) {
		eMail = mail;
	}
	public void setTpCat(String tpCat) {
		this.tpCat = tpCat;
	}
	

}
