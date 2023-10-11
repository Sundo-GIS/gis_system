package com.gis.dto.gis;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LocalData {
	private String carNum;
	private LocalDate date;
	private String time;
	private double lon;
	private double lat;
	private int rpm;
	private int noise;
	private boolean is_done;
}
