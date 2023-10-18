package com.gis.dao.mainView;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gis.dto.mainView.CarDto;
import com.gis.dto.mainView.CleanDateDto;

@Mapper
public interface IMainViewDao {
	/**
	 * 모달창 차량 추가
	 * @author 고일봉
	 */
	public void addCar(@Param("car") CarDto car); 

	/**
	 * 모달창 차량 삭제
	 * @author 고일봉
	 */
	public void deleteCar(String carNum);
	
	/**
	 * 차량 데이터 삭제
	 * @author 고일봉
	 */
	public void deleteCarData(String carNum);


	/**
	 * 차량 리스트 출력
	 * @author 고일봉
	 */
	public List<CarDto> carNumList();
	
	/**
	 * 차량별 청소 날짜 출력
	 * @author 고일봉
	 */
	public List<String> selectedDate(@Param("carNum") String carNum);
}
