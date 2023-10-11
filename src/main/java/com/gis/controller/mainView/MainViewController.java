package com.gis.controller.mainView;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gis.dto.gis.DateCoord;
import com.gis.service.gis.IGisService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainViewController {

	private final IGisService gisService;
	
	@GetMapping("/view")
	public String mainView (
			@RequestParam(name="date", required= false) String date, 
			@RequestParam(name="carNum", required= false) String carNum,
			Model model) {
		DateCoord coord = gisService.selectDateCoord(date, carNum);
		model.addAttribute("dateCoord", coord);
		return "mainView/main";
	}
	
}
