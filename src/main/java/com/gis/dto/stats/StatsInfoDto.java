package com.gis.dto.stats;

import java.time.LocalTime;

import lombok.Data;

@Data
public class StatsInfoDto {

	private LocalTime cleanTime;
	private double cleanRatio;
	private double totalDistance;
	private double cleanDistance;
}
