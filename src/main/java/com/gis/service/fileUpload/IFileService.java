package com.gis.service.fileUpload;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

	void uploadCsv(int rowCount, MultipartFile gpsFile, MultipartFile noiseFile, MultipartFile rpmFile);

}
