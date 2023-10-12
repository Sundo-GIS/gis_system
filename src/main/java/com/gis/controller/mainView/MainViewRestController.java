package com.gis.controller.mainView;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gis.dto.mainView.CarDto;
import com.gis.dto.mainView.CarNumListDto;
import com.gis.service.mainView.IMainViewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MainViewRestController {

//	private final IGisService gisService;
	private final IMainViewService mainViewService;
	

	
	// 차량 별 청소 날짜 출력
//	@GetMapping("/view/{carNum}")
//	public List<String> cleanDateList (@PathVariable("carNum") String carNum) {
//		log.info("--------1--------" + carNum);
//		List<CleanDateDto> cddList = mainViewService.selectedDate(carNum);
//		List<String> cddList = mainViewService.selectedDate(carNum);
//		log.info("--------2--------" + cddList);
//		return cddList;
//	}
}
