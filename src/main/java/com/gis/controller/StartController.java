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
    	return "index";
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
    @GetMapping("statistics")
    public String DateStatistics(){
    	return gisService.selectDateCleanTime("2023-08-29");
    }
    @ResponseBody
    @GetMapping("coord")
    public DateCoord DateCoord(){
    	log.info("contoller 시작");
    	return gisService.selectDateCoord("2023-08-29", "103하2414");
    }
}
