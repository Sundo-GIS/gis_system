package com.gis.controller.stats;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gis.dto.mainView.CarNumListDto;
import com.gis.dto.stats.SelectCarDto;
import com.gis.dto.stats.StatsInfoDto;
import com.gis.service.gis.IGisService;
import com.gis.service.mainView.IMainViewService;
import com.gis.service.stats.IStatsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class StatsController {

	private final IGisService gisService;
	private final IMainViewService mainViewService;
	private final IStatsService statsService;

	/**
	 * 통계차량 뷰 (모달창)
	 * 
	 * @author 고일봉
	 */
	@GetMapping("/stats")
	public String statistic(Model model) {
		List<CarNumListDto> cnldList = mainViewService.carNumList();
		model.addAttribute("carNumList", cnldList);
		return "mainView/stats";
	}

	/**
	 * 통계 뷰 데이터 출력(모달창)
	 * 
	 * @author 고일봉
	 */
	@ResponseBody
	@GetMapping("/stats/select")
	public StatsInfoDto statsInfo(SelectCarDto selectCarDto) {
		StatsInfoDto si = statsService.statsInfoDto(selectCarDto);
		return si;
	}
}
