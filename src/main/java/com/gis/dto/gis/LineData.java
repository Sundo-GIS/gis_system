package com.gis.dto.gis;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LineData {
	private String carNum;
	private LocalDate date;
	private boolean is_done;
}
