package com.gis.controller.mainView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class MainViewController {

	@GetMapping("/view")
	public String mainView () {
		return "mainView/main";
	}
	
}
