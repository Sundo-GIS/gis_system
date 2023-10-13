package com.gis.service.mainView;

import java.util.List;

import com.gis.dto.mainView.CarDto;
import com.gis.dto.mainView.CarNumListDto;
import com.gis.dto.mainView.CleaningInfoDto;

public interface IMainViewService {

	/**
	 * 모달창 차량 추가
	 * @author 고일봉
	 */
	public void addCar(CarDto car);
	
	/**
	 * 차량 리스트 출력
	 * @author 고일봉
	 */
	public List<CarNumListDto> carNumList();
	
	/**
	 * 차량 별 청소 날짜 출력
	 * @author 고일봉
	 */
	public List<String> selectedDate(String carNum);
	
	/**
	 * 차량, 날짜 선택시 청소정보 및 달력에 대한 좌표 갖고오기
	 * @author 고일봉
	 */
	public CleaningInfoDto cleaningInfo(String carNum, String date);
}
