package com.gis.dto.gis;

import lombok.Data;

@Data
public class LocalData {
	private String carNum;
	private String date;
	private String time;
	private double lon;
	private double lat;
	private int noiseLevel;
	private int rpmLevel;
	private boolean is_done;
}
