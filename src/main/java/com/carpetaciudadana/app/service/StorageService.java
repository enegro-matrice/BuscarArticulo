package com.carpetaciudadana.app.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public interface StorageService {

	public URL getURL(String nombreArchivo);

	public PutObjectResult put(File file, Boolean isPublic);

	public List<S3ObjectSummary> getFrom();
	
	InputStream getObject(String fileName) throws IOException;

}
