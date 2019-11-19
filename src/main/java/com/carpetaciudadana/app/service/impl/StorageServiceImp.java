package com.carpetaciudadana.app.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.carpetaciudadana.app.domain.StorageConnection;
import com.carpetaciudadana.app.service.StorageService;

/**
 * Servicios para guardar informacion en storage. Implementacion S3
 * 
 * @autor leonardopalazzo@buenosaires.gob.ar
 **/

@Service
public class StorageServiceImp implements StorageService {
	// properties
	@Value("${app.s3.accessKey}")
	private String accessKey;
	@Value("${app.s3.secretKey}")
	private String secretKey;
	@Value("${app.s3.endpoint}")
	private String endpoint;
	@Value("${app.s3.port}")
	private String port;
	@Value("${app.s3.bucketName}")
	private String bucketName;

	public URL getURL(String nombreArchivo) {
		return StorageConnection.getInstance(accessKey, secretKey, endpoint, port).getClient().getUrl(bucketName, nombreArchivo);
	}

	/**
	 * Agrega un archivo en el bucket
	 */
	public PutObjectResult put(File file, Boolean isPublic) {
		PutObjectRequest objectRequest = new PutObjectRequest(bucketName, file.getName(), file);
		if (isPublic)
			objectRequest.withCannedAcl(CannedAccessControlList.PublicRead);

		return StorageConnection.getInstance(accessKey, secretKey, endpoint, port).getClient().putObject(objectRequest);
	}

	/** devuelve archivos del bucket */
	public List<S3ObjectSummary> getFrom() {
		return StorageConnection.getInstance(accessKey, secretKey, endpoint, port).getClient().listObjects(bucketName).getObjectSummaries();
	}

	/***devuelve el archivo del bucket*/
	public InputStream getObject(String fileName) throws IOException {
		S3Object fetchFile = StorageConnection.getInstance(accessKey, secretKey, endpoint, port).getClient()
				.getObject(new GetObjectRequest(bucketName, fileName));
		final BufferedInputStream i = new BufferedInputStream(fetchFile.getObjectContent());
		InputStream objectData = fetchFile.getObjectContent();
		MultipartFile file= new MockMultipartFile(fileName.concat(".pdf"),fileName.concat(".pdf"), "application/pdf", objectData);
		objectData.close();
		
		return new BufferedInputStream(file.getInputStream());
	}

}
