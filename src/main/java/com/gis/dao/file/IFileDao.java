package com.gis.dao.file;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gis.dto.gis.LocalData;

@Mapper
public interface IFileDao {

	/**
	 * 파일 업로드 : coord 테이블에 데이터 삽입
	 * @author 임연서
	 */
	void insertCsvData(@Param("localData")LocalData localData);
	
	/**
	 * 파일 다운로드 : coord 테이블에서 데이터 조회 
	 * @author 임연서
	 */	
	List<LocalData> selectLocalData(@Param("date")String date, @Param("carNum") String carNum);
	
}
