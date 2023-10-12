package com.gis.dao.mainView;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gis.dto.mainView.CarDto;
import com.gis.dto.mainView.CleanDateDto;

@Mapper
public interface IMainViewDao {
	
	// 모달창 차량 추가
	public void addCar(@Param("car") CarDto car); 

	// 차량 리스트 출력
	public List<CarDto> carNumList();
	
	// 차량 별 청소 날짜 출력
//	public List<CleanDateDto> selectedDate(@Param("carNum") String carNum);
	public List<String> selectedDate(@Param("carNum") String carNum);
}
