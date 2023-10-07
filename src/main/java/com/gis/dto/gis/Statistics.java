package com.gis.dto.gis;

import java.time.LocalTime;

import lombok.Data;

@Data
public class Statistics {
	private LocalTime cleanTime;
	private int cleanRatio;
	private int totalDistance;
	private int cleanDistance;
}
