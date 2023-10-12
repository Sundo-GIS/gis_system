package com.gis.controller.mainView;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gis.dto.mainView.CarDto;
import com.gis.dto.mainView.CarNumListDto;
import com.gis.dto.mainView.CleaningInfoDto;
import com.gis.service.gis.IGisService;
import com.gis.service.mainView.IMainViewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MainViewController {

	private final IGisService gisService;
	private final IMainViewService mainViewService;

	// 차량, 날짜 선택시 청소정보 및 달력에 대한 좌표 갖고오기
	@ResponseBody
	@GetMapping("/view/select")
	public CleaningInfoDto cleaningInfo(@RequestParam("carNum") String carNum, 
										@RequestParam("date") String date ) {
		CleaningInfoDto cid = mainViewService.cleaningInfo(carNum, date);
		return cid;
	}
	
	// 차량 리스트 출력
	@GetMapping("/view")
	public String modalAddCar(Model model) {
		List<CarNumListDto> cnldList = mainViewService.carNumList();
		model.addAttribute("carNumList", cnldList);
		return "mainView/main";
	}

	// 모달창 차량 추가
	@PostMapping("/view")
	public String modalCarInfo(CarDto car) {
		mainViewService.addCar(car);
		return "redirect:/view";
	}

	// 차량 별 청소 날짜 출력
	@ResponseBody
	@GetMapping("/view/carNum")
	public List<String> cleanDateList(@RequestParam("carNum") String carNum) {
		List<String> cddList = mainViewService.selectedDate(carNum);

		return cddList;
	}
}
