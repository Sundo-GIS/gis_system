package com.gis.service.mainView;

import java.util.List;

import com.gis.dto.mainView.CarDto;
import com.gis.dto.mainView.CarNumListDto;
import com.gis.dto.mainView.CleanDateDto;

public interface IMainViewService {

	// 모달창 차량 추가
	public void addCar(CarDto car);
	
	// 차량 리스트 출력
	public List<CarNumListDto> carNumList();
	
	// 차량 별 청소 날짜 출력
//	public List<CleanDateDto> selectedDate(String carNum);
	public List<String> selectedDate(String carNum);
}
