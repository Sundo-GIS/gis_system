package com.gis.service.file;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gis.dto.gis.LocalData;

public interface IFileService {

	/**
	 * 파일 업로드  : coord 테이블에 데이터 추가
	 * @author 임연서
	 */
	LocalData uploadCsv(int rowCount, MultipartFile gpsFile, MultipartFile noiseFile, MultipartFile rpmFile);
	
	/**
	 * 파일 다운로드 : coord 테이블에서 데이터 조회 
	 * @author 임연서
	 */
	List<LocalData> selectLocalData(String date, String carNum);

	/**
	 * 파일 다운로드 : csv 생성 
	 * @author 임연서
	 */
	void createCsvFile(List<LocalData> dataList, PrintWriter writer);

}
