package com.gis.controller.mainView;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gis.dto.mainView.CarDto;
import com.gis.dto.mainView.CarNumListDto;
import com.gis.dto.mainView.CleaningInfoDto;
import com.gis.dto.member.MemberDto;
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
	
	/**
	 * 차량, 날짜 선택시 청소정보 및 달력에 대한 좌표 조회
	 * 
	 * @author 고일봉
	 */
	@ResponseBody
	@GetMapping("/view/select")
	public CleaningInfoDto cleaningInfo(@RequestParam("carNum") String carNum, @RequestParam("date") String date) {
		CleaningInfoDto cid = mainViewService.cleaningInfo(carNum, date);

		return cid;
	}

	@GetMapping("/view")
	public String memberPage(HttpServletRequest request, Model model) {

		/**
		 * 세션에서 사용자 정보 가져오기
		 * 
		 * @author 임연서
		 */
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");

		// 사용자가 로그인되어 있지 않으면 로그인 페이지로 리다이렉트
		if (member == null) {
			log.info("회원이 아님");
			return "redirect:/";
		}

		/**
		 * 첫 화면 차량 리스트 출력
		 * 
		 * @author 고일봉
		 */
		List<CarNumListDto> cnldList = mainViewService.carNumList();
		model.addAttribute("carNumList", cnldList);
		return "mainView/main";
	}

	/**
	 * 차량추가 (모달창)
	 * 
	 * @author 고일봉
	 */
	@ResponseBody
	@PostMapping("/view")
	public List<CarNumListDto> modalCarInfo(CarDto car) {
		mainViewService.addCar(car);
		List<CarNumListDto> cnldList = mainViewService.carNumList();
		return cnldList;
	}

	/**
	 * 차량삭제 (모달창)
	 * 
	 * @author 고일봉
	 */
	@ResponseBody
	@PostMapping("/view/deleteCar")
	public List<CarNumListDto> deleteCar(String carNum) {
		mainViewService.deleteCarData(carNum);
		mainViewService.deleteCar(carNum);
		
		List<CarNumListDto> cnldList = mainViewService.carNumList();
		return cnldList;
	}

	/**
	 * 차량에따른 청소날짜 출력
	 * 
	 * @author 고일봉
	 */
	@ResponseBody
	@GetMapping("/view/carNum")
	public List<String> cleanDateList(@RequestParam("carNum") String carNum) {
		List<String> cddList = mainViewService.selectedDate(carNum);

		return cddList;
	}

}
