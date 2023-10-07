package com.gis.dto.gis;

import lombok.Data;

@Data
public class LocalData {
	private String car;
	private String date;
	private String time;
	private double x;
	private double y;
	private int noiseLevel;
	private int rpmLevel;
	private boolean is_done;
}
