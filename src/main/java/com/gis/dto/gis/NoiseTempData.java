package com.gis.dto.gis;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class NoiseTempData {
	private String carNum;
	private LocalDate date;
	private LocalTime time;
	private int noise;
}
