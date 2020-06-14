package com.evliion.ev.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.evliion.ev.util.AppConstants;


public class FileStorageProperties {
	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
}
