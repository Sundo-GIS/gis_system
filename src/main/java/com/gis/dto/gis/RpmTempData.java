package com.gis.dto.gis;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class RpmTempData {
	private String carNum;
	private LocalDate date;
	private LocalTime time;
	private int rpm;
}
