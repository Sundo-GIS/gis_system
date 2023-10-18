package com.gis.service.stats;

import com.gis.dto.stats.SelectCarDto;
import com.gis.dto.stats.StatsInfoDto;

public interface IStatsService {

	/**
	 * 통계 뷰 데이터 출력(모달창)
	 * 
	 * @author 고일봉
	 */
	public StatsInfoDto statsInfoDto (SelectCarDto selecCarDto);
}
