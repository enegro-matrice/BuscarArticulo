package com.carpetaciudadana.app.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.core.JsonParseException;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.web.multipart.MultipartFile;



public interface StorageService {

	public URL getURL(String nombreArchivo);

	public String put(MultipartFile file, Boolean isPublic) throws IOException;

	public List<S3ObjectSummary> getFrom()throws IOException;

	public Bucket createBucket(String fileName) throws IOException;
	
	public Boolean delecFile(String fileName) throws IOException;

	public byte[] getObject(String fileName) throws IOException;

}
