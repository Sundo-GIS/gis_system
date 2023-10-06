package com.gis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gis.dto.TempDto;
import com.gis.service.IGisService;
import com.gis.util.TimeScheduler;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/scheduled")
@RequiredArgsConstructor
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
    @GetMapping("/insert/temp")
    public void insertTempData(@RequestBody TempDto tempDto) {
    	gisService.insertGpsTempData(tempDto.getGtd());
    	gisService.insertNoiseTempData(tempDto.getNtd());
    	gisService.insertRpmTempData(tempDto.getRtd());
    }
    @ResponseBody
    @GetMapping("/stop")
    public String stopScheduler() {
        timnScheduler.stopScheduler();
        return "";
    }
}
