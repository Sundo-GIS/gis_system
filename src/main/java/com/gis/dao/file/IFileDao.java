package com.gis.dao.file;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gis.dto.gis.LocalData;

@Mapper
public interface IFileDao {

	void insertCsvData(@Param("localData")LocalData localData);
	
}
