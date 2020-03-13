package com.demo.schedule.controller;


import com.demo.schedule.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * 用于debug实验
 * */
@Controller
public class ScheduleTestController {

    @Resource(name = "crmNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate crmNamedParameterJdbcTemplate;

    @Autowired
    ScheduleService scheduleService;

    /**
     * 127.0.0.1:8080/scheduleTest/startTask?scheduleTaskId=2
     * */
    @RequestMapping(value = "/scheduleTest/startTask")
    @ResponseBody
    public Object startTask(@RequestParam(value = "scheduleTaskId") Long scheduleTaskId) throws Exception {
        String sqlString = "select * from crmScheduleTask where scheduleTaskId=:scheduleTaskId ";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("scheduleTaskId", scheduleTaskId);
        Map<String, Object> crmScheduleTask = crmNamedParameterJdbcTemplate.queryForMap(sqlString, paramMap);
        scheduleService.startTask(crmScheduleTask);
        String sqlString2 = "update crmScheduleTask set isEnable = 1 where scheduleTaskId=:scheduleTaskId ";
        crmNamedParameterJdbcTemplate.update(sqlString2, paramMap);


        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success/启动任务");
        return responseMap;
    }

    /**
     * 127.0.0.1:8080/scheduleTest/cancelTask?scheduleTaskId=2
     * */
    @RequestMapping(value = "/scheduleTest/cancelTask")
    @ResponseBody
    public Object cancelTask(@RequestParam(value = "scheduleTaskId") Long scheduleTaskId) throws Exception {
        String sqlString = "select * from crmScheduleTask where scheduleTaskId=:scheduleTaskId ";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("scheduleTaskId", scheduleTaskId);
        Map<String, Object> crmScheduleTask = crmNamedParameterJdbcTemplate.queryForMap(sqlString, paramMap);
        scheduleService.cancelTask(crmScheduleTask);
        String sqlString2 = "update crmScheduleTask set isEnable = 0 where scheduleTaskId=:scheduleTaskId ";
        crmNamedParameterJdbcTemplate.update(sqlString2, paramMap);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success/停止任务");
        return responseMap;
    }


}
