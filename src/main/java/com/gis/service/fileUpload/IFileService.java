package com.gis.service.fileUpload;

public interface IFileService {

	void uploadCsv(String[] gpsFile, String[] noiseFile, String[] rpmFile);

}
