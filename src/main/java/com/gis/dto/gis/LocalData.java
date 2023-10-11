package com.gis.dto.gis;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class LocalData {
	private String carNum;
	private LocalDate date;
	private LocalTime time;
	private double lon;
	private double lat;
	private int noise;
	private int rpm;
	private boolean is_done;
}
