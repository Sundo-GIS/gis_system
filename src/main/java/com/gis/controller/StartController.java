package com.gis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	private final TimeScheduler timeScheduler;
	private final IGisService gisService;
	
    @GetMapping
    public String main() {
    	return "prac";
    }
    @ResponseBody
    @GetMapping("/start")
    public ResponseEntity<String> startScheduler() {
    	timeScheduler.startScheduler();
        return new ResponseEntity<>("Success message", HttpStatus.OK);
    }
    /**
	 * Car테이블에서 차량 번호 조회
	 * @author 여수한
	 */
    @ResponseBody
    @GetMapping("/car")
    public String selectCar() {
    	Map<String, Object> data = new HashMap<>();
    	List<String> car = gisService.selectCar();
    	data.put("carNum", car);

        // ObjectMapper를 생성
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Map을 JSON 문자열로 변환
            String jsonString = objectMapper.writeValueAsString(data);

            // JSON 문자열 출력
            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    /**
	 * App에서 Temp Data 받아오기
	 * @author 여수한
	 */
    @PostMapping("/temp/gps")
    public ResponseEntity<String> insertGpsTempData(@RequestBody GpsTempData locationDto) {
    	log.info("GPS : " + locationDto);
    	gisService.insertGpsTempData(locationDto);
    	return  new ResponseEntity<>("Success message", HttpStatus.OK);
    }
    @PostMapping("/temp/noise")
    public ResponseEntity<String>  insertNoiseTempData(@RequestBody NoiseTempData noiseDto) {
    	log.info("noise : " + noiseDto);
    	gisService.insertNoiseTempData(noiseDto);
    	return  new ResponseEntity<>("Success message", HttpStatus.OK);
    }
    @PostMapping("/temp/rpm")
    public ResponseEntity<String>  insertRpmTempData(@RequestBody RpmTempData vibrationDto) {
    	log.info("rpm : " + vibrationDto);
    	gisService.insertRpmTempData(vibrationDto);
    	return  new ResponseEntity<>("Success message", HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/stop")
    public ResponseEntity<String> stopScheduler() {
    	timeScheduler.stopScheduler();
        return new ResponseEntity<>("Success message", HttpStatus.OK);
    }
    /**
	 * 선택한 차량의 선택한 날짜의 센터 좌표 가져오기 
	 * @author 여수한
	 */
    @ResponseBody
    @GetMapping("/coord")
    public DateCoord DateCoord(@RequestParam("date") String date, @RequestParam("carNum") String carNum){
    	return gisService.selectDateCoord(date, carNum);
    }
    /**
	 * 라이브 좌표 조회
	 * @author 여수한
	 */
    @ResponseBody
    @PostMapping("/live")
    public DateCoord getLiveCoord(){
    	return gisService.selectLiveCoord();
    }
}
