package com.gis.dto.gis;

import lombok.Data;

@Data
public class NoiseTempData {
	private String car;
	private String date;
	private String time;
	private int noiseLevel;
}
