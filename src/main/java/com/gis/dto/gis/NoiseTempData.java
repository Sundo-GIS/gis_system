package com.gis.dto.gis;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NoiseTempData {
	private String carNum;
	private LocalDate date;
	private LocalTime time;
	@JsonProperty("noise")
    private BigDecimal noise;
}
