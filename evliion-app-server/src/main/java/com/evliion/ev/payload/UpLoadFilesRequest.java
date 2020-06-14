package com.evliion.ev.payload;

import org.springframework.web.multipart.MultipartFile;

public class UpLoadFilesRequest {
	private MultipartFile file;
	private String countryName;
	private String documentType;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

}
