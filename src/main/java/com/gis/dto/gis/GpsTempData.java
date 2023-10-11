package com.gis.dto.gis;

import lombok.Data;

@Data
public class GpsTempData {
	private String carNum;
	private String date;
	private String time;
	private double lon;
	private double lat;
}
