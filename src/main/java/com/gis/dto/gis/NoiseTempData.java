package com.gis.dto.gis;

import lombok.Data;

@Data
public class NoiseTempData {
	private String carNum;
	private String date;
	private String time;
	private int noise;
}
