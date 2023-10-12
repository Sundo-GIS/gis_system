package com.gis.controller.mainView;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gis.dto.mainView.CarDto;
import com.gis.dto.mainView.CarNumListDto;
import com.gis.service.mainView.IMainViewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MainViewController {

//	private final IGisService gisService;
	private final IMainViewService mainViewService;

//	@GetMapping("/view")
//	public String mainView(
//			@RequestParam(name = "date", required = false) String date,
//			@RequestParam(name = "carNum", required = false) String carNum, Model model) {
//		log.info("------------1-------------" + date);
//		log.info("------------2-------------" + carNum);
//		DateCoord coord = gisService.selectDateCoord(date, carNum);
//		model.addAttribute("dateCoord", coord);
//		log.info("------------3-------------" + coord);
//		return "mainView/main";
//	}
	
	// 모달창 차량 추가
	@GetMapping("/view")
	public String mainView(Model model){
		
		List<CarNumListDto> cnldList = mainViewService.carNumList();
		model.addAttribute("carNumList",cnldList);
		return "mainView/main";
	}

	// 차량 리스트 출력
	@PostMapping("/view")
	public String modalCarInfo(CarDto car) {
		mainViewService.addCar(car);
		return "mainView/main";
	}
	
	// 차량 별 청소 날짜 출력
	@ResponseBody
	@GetMapping("/view/carNum")
	public List<String> cleanDateList (@RequestParam("carNum") String carNum) {
		List<String> cddList = mainViewService.selectedDate(carNum);
		
		return cddList;
	}
}
