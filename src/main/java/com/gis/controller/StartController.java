package com.gis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gis.dto.gis.DateCoord;
import com.gis.dto.gis.GpsTempData;
import com.gis.dto.gis.NoiseTempData;
import com.gis.dto.gis.RpmTempData;
import com.gis.dto.gis.Statistics;
import com.gis.service.gis.IGisService;
import com.gis.util.TimeScheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/gis")
@RequiredArgsConstructor
@Log4j2
public class StartController {
	
	private final TimeScheduler timnScheduler;
	private final IGisService gisService;
	
    @GetMapping
    public String main() {
    	return "prac";
    }
    @ResponseBody
    @GetMapping("/start")
    public String startScheduler(@RequestParam("time") int time) {
    	timnScheduler.startScheduler(time);
        return "";
    }
    @GetMapping("/temp/gps")
    public void insertGpsTempData(@RequestBody GpsTempData ptd) {
    	gisService.insertGpsTempData(ptd);
    }
    @GetMapping("/temp/noise")
    public void insertNoiseTempData(@RequestBody NoiseTempData ntd) {
    	gisService.insertNoiseTempData(ntd);
    }
    @GetMapping("/temp/rpm")
    public void insertRpmTempData(@RequestBody RpmTempData rtd) {
    	gisService.insertRpmTempData(rtd);
    }
    @ResponseBody
    @GetMapping("/stop")
    public String stopScheduler() {
        timnScheduler.stopScheduler();
        return "";
    }
    @ResponseBody
    @GetMapping("/statistics")
    public Statistics DateStatistics(){
    	// 청소 시간
    	String DateCleanTime = gisService.selectDateCleanTime("2023-08-29", "103하2414");
    	// 청소 비율
    	double DateCleanRatio = gisService.selectDateCleanRatio("2023-08-29", "103하2414");
    	// 총 운행거리
    	double DateTotalDistance = gisService.selectDateTotalDistance("2023-08-29", "103하2414");
    	// 청소한 운행거리
    	double DateCleanDistance = gisService.selectDateCleanDistance("2023-08-30", "103하2414");
    	Statistics statistics = new Statistics();
    	statistics.setCleanTime(DateCleanTime);
    	statistics.setCleanDistance(DateCleanDistance);
    	statistics.setCleanRatio(DateCleanRatio);
    	statistics.setTotalDistance(DateTotalDistance);
    	return statistics;
    }
    @ResponseBody
    @GetMapping("/coord")
    public DateCoord DateCoord(@RequestParam("date") String date, @RequestParam("carNum") String carNum){
    	log.info(date+" "+carNum);
    	log.info("asdsd");
    	return gisService.selectDateCoord(date, carNum);
    }
}
