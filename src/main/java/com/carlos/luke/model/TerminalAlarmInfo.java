package com.carlos.luke.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <P>
 * 终端报警领域模型
 * </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 */
public class TerminalAlarmInfo implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = -1007322197503587700L;

    public static final int ALARMBEGIN = 1;

	public static final int ALARMEND = 0;
	/** 数据库主键 */
	private long id;
	/** 设备串号 */
	private String imei;
	/** 设备号 */
	private int gpsno;
	/** 系统ID **/
	private String sysid;
	/** 纬度 */
	private double lat;
	/** 经度 */
	private double lng;
	/** 结束纬度 */
	private double latend;
	/** 结束经度 */
	private double lngend;
	/** 速度 */
	private int speed;
	/** 报警开始时间 */
	private Date start_time;
	/** 报警结束时间 */
	private Date end_time;
	/** 报警类型  事件id,用于区分不同的报警*/
	private int alarm_type;
	/** 附加消息体数据 */
	private String additional_info;
	/** 处理标记 */
	private int handle;
	/** 地址信息 */
	private String address;
	/** 备注信息 */
	private String notes;

	/** 报警标记 */
	private boolean alarm;

	/** 状态1：单次 2：状态 3:报警 */
	private int status;

	/**
	 * 标示终端报警事件是开始记录 还是结束记录 1-事件开始 0-事件结束
	 */
	private int alarmStatus;

	/**
	 * 是否删除 0：未删除 1：删除
	 */
	private int deleted;

	/**
	 * 司机卡号
	 */
	private String icno;

	/**
	 * 创建时间
	 */
	private Date createtime;
	
	/**
	 * 更新时间
	 */
	private Date updatetime;

	/**
	 * 机构编码
	 */
	private String orgcode;

	/**
	 * 开始事件ID
	 * 
	 * 成对事件（进出区，点熄火等），nextid为结束事件（出区、熄火）所对应的开始事件（进区、点火）的ID
	 */
	private int nextid;
	
	/**
	 * 
	 */
	private String trucknum;
	
	private String driverNo;

	public String getDriverNo() {
		return driverNo;
	}

	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getIcno() {
		return icno;
	}

	public void setIcno(String icno) {
		this.icno = icno;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAlarmStatus() {
		return alarmStatus;
	}

	public void setAlarmStatus(int alarmStatus) {
		this.alarmStatus = alarmStatus;
	}

	/**
	 * @param alarm
	 *            the alarm to set
	 */
	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public int getAlarm_type() {
		return alarm_type;
	}

	public void setAlarm_type(int alarm_type) {
		this.alarm_type = alarm_type;
	}

	public String getAdditional_info() {
		return additional_info;
	}

	public void setAdditional_info(String additional_info) {
		this.additional_info = additional_info;
	}

	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * @return the lng
	 */
	public double getLng() {
		return lng;
	}

	/**
	 * @param lng
	 *            the lng to set
	 */
	public void setLng(double lng) {
		this.lng = lng;
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 *            the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei
	 *            the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	public boolean isAlarm() {
		return alarm;
	}

	public double getLatend() {
		return latend;
	}

	public void setLatend(double latend) {
		this.latend = latend;
	}

	public double getLngend() {
		return lngend;
	}

	public void setLngend(double lngend) {
		this.lngend = lngend;
	}

	/**
	 * @return the handle
	 */
	public int getHandle() {
		return handle;
	}

	/**
	 * @param handle
	 *            the handle to set
	 */
	public void setHandle(int handle) {
		this.handle = handle;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGpsno() {
		return gpsno;
	}

	public void setGpsno(int gpsno) {
		this.gpsno = gpsno;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	public int getNextid() {
		return nextid;
	}

	public void setNextid(int nextid) {
		this.nextid = nextid;
	}

	public String getTrucknum() {
		return trucknum;
	}

	public void setTrucknum(String trucknum) {
		this.trucknum = trucknum;
	}

	@Override
	public String toString() {
		return "TerminalAlarmInfo [id=" + id + ", imei=" + imei + ", gpsno=" + gpsno + ", lat=" + lat + ", lng=" + lng
				+ ", latend=" + latend + ", lngend=" + lngend + ", speed=" + speed + ", start_time=" + start_time
				+ ", end_time=" + end_time + ", alarm_type=" + alarm_type + ", additional_info=" + additional_info
				+ ", handle=" + handle + ", address=" + address + ", notes=" + notes + ", alarm=" + alarm + ", status="
				+ status + ", alarmStatus=" + alarmStatus + ", deleted=" + deleted + ", icno=" + icno + ", updatetime="
				+ updatetime + "]";
	}

}
