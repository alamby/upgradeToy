package com.carlos.luke.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;


public class Event implements Comparator<Event> , Serializable{

	private static final long serialVersionUID = -1379462471053114341L;
	
	private long id;//id
	private String sysid;//sysid
	private String imei;//imei
	private String trucknum;//trucknum
	private String driverno;//司机编号
	private String orgcode;//orgcode
	private String type;//type
	private String beginLng;
	private String beginLat;
	private Date beginTime;//开始时间
	private String endLng;
	private String endLat;
	private Date endTime;//结束时间
	private Date triggerTime;//触发时间
	private String triggerLng;
	private String triggerLat;
	private Date createTime;
	private Date updateTime;
	private int seconds;
	private String additional_info;
	private String additional_key;
	private String truckno;


    @Override
	public String toString() {
		return "Event [id=" + id + ", sysid=" + sysid + ", imei=" + imei
				+ ", trucknum=" + trucknum + ", driverno=" + driverno
				+ ", orgcode=" + orgcode + ", type=" + type + ", beginLng="
				+ beginLng + ", beginLat=" + beginLat + ", beginTime="
				+ beginTime + ", endLng=" + endLng + ", endLat=" + endLat
				+ ", endTime=" + endTime + ", triggerTime=" + triggerTime
				+ ", triggerLng=" + triggerLng + ", triggerLat=" + triggerLat
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", seconds=" + seconds + ", additional_info="
				+ additional_info + ", additional_key=" + additional_key + ", truckno=" + truckno + "]";
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getSysid() {
		return sysid;
	}


	public void setSysid(String sysid) {
		this.sysid = sysid;
	}


	public String getImei() {
		return imei;
	}


	public void setImei(String imei) {
		this.imei = imei;
	}


	public String getTrucknum() {
		return trucknum;
	}


	public void setTrucknum(String trucknum) {
		this.trucknum = trucknum;
	}


	public String getDriverno() {
		return driverno;
	}


	public void setDriverno(String driverno) {
		this.driverno = driverno;
	}


	public String getOrgcode() {
		return orgcode;
	}


	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getBeginLng() {
		return beginLng;
	}


	public void setBeginLng(String beginLng) {
		this.beginLng = beginLng;
	}


	public String getBeginLat() {
		return beginLat;
	}


	public void setBeginLat(String beginLat) {
		this.beginLat = beginLat;
	}


	public Date getBeginTime() {
		return beginTime;
	}


	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}


	public String getEndLng() {
		return endLng;
	}


	public void setEndLng(String endLng) {
		this.endLng = endLng;
	}


	public String getEndLat() {
		return endLat;
	}


	public void setEndLat(String endLat) {
		this.endLat = endLat;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public Date getTriggerTime() {
		return triggerTime;
	}


	public void setTriggerTime(Date triggerTime) {
		this.triggerTime = triggerTime;
	}


	public String getTriggerLng() {
		return triggerLng;
	}


	public void setTriggerLng(String triggerLng) {
		this.triggerLng = triggerLng;
	}


	public String getTriggerLat() {
		return triggerLat;
	}


	public void setTriggerLat(String triggerLat) {
		this.triggerLat = triggerLat;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public int getSeconds() {
		return seconds;
	}


	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}


	public String getAdditional_info() {
		return additional_info;
	}


	public void setAdditional_info(String additional_info) {
		this.additional_info = additional_info;
	}


	public String getAdditional_key() {
		return additional_key;
	}


	public void setAdditional_key(String additional_key) {
		this.additional_key = additional_key;
	}
	
    
    public String getTruckno() {
        return truckno;
    }


    public void setTruckno(String truckno) {
        this.truckno = truckno;
    }


	@Override
	public int compare(Event event1,Event event2) {
	    long xxx = event1.getBeginTime().getTime() - event2.getBeginTime().getTime();
	    return Integer.parseInt(String.valueOf(xxx));
	}
	
}
