package com.gis.dao.stats;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gis.dto.stats.CarDateDto;
import com.gis.dto.stats.SelectCarDto;

@Mapper
public interface IStatsDao {

	/**
	 * 통계 뷰 데이터 출력(모달창)
	 * 
	 * @author 고일봉
	 */
	public List<CarDateDto> selectCar(@Param("selectCarDto") SelectCarDto selectCartDto);
}
