package com.gis.service.mainView;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gis.dao.mainView.IMainViewDao;
import com.gis.dto.mainView.CarDto;
import com.gis.dto.mainView.CarNumListDto;
import com.gis.dto.mainView.CleanDateDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class MainViewService implements IMainViewService {

	private final IMainViewDao mainViewDao;

	// 모달창 차량 추가
	public void addCar(CarDto car) {
		mainViewDao.addCar(car);
	}

	// 차량 리스트 출력
	public List<CarNumListDto> carNumList() {
		List<CarDto> cdList = mainViewDao.carNumList();
		List<CarNumListDto> cnldList = new ArrayList<>();

		for (CarDto cd : cdList) {
			CarNumListDto cnld = new CarNumListDto();

			cnld.setCarNum(cd.getCarNum());
			cnldList.add(cnld);
		}
		return cnldList;
	}

	// 차량 별 청소 날짜 출력
	public List<String> selectedDate(String carNum) {
//		List<CleanDateDto> cdd = mainViewDao.selectedDate(carNum);
		List<String> cdd = mainViewDao.selectedDate(carNum);
		return cdd;
	}

}
