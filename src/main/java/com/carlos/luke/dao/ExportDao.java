package com.carlos.luke.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.carlos.luke.commons.datasource.DynamicDS;
import com.carlos.luke.model.Event;
import com.carlos.luke.model.TerminalAlarmInfo;


@Repository
@DynamicDS("DB11")
public interface ExportDao {

    public List<Event> queryEvent(@Param("beginTime")Date beginTime,@Param("endTime")Date endTime,
    		@Param("imei")String imei ,@Param("type")int type, @Param("start")int pageNo,@Param("offset")int pageSize);

    public List<Event> queryInEvent(@Param("ids")List<Long>  ids);
    
    public List<TerminalAlarmInfo> queryAlarm0(@Param("imei")String imei,@Param("start")Date start,@Param("end")Date end);
    
    public List<TerminalAlarmInfo> queryAlarm1(@Param("imei")String imei,@Param("start")Date start,@Param("end")Date end);
    
    public List<TerminalAlarmInfo> queryAlarms(@Param("gpsnos")List<Long> gpsnos);
}

