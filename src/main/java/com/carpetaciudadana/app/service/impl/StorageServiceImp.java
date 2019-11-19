package com.carpetaciudadana.app.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import com.carpetaciudadana.app.domain.StorageConnection;
import com.carpetaciudadana.app.service.StorageService;
import com.carpetaciudadana.app.web.rest.errors.CustomParameterizedException;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.core.JsonParseException;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.JsonMappingException;
//import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicios para guardar informacion en storage. Implementacion S3
 * 
 * @autor leonardopalazzo@buenosaires.gob.ar
 **/

@Service
public class StorageServiceImp implements StorageService {
	private final Logger log = LoggerFactory.getLogger(StorageServiceImp.class);
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
	public String put(MultipartFile file, Boolean isPublic) throws IOException {
		log.debug("Add File : "+file.getOriginalFilename());
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		File convFile =  File.createTempFile(file.getName(),extension);
		log.debug(file.getName()+ "   415465    " +extension);
		FileOutputStream fos = new FileOutputStream(convFile); 
		fos.write(file.getBytes());
		PutObjectRequest objectRequest = new PutObjectRequest(bucketName, file.getOriginalFilename(), convFile);
		if (isPublic)
			objectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
		StorageConnection.getInstance(accessKey, secretKey, endpoint, port).getClient().putObject(objectRequest);
		return file.getOriginalFilename();
	}

	/** devuelve archivos del bucket */
	public List<S3ObjectSummary> getFrom() throws IOException{
		log.debug("Get list file : ");
		return StorageConnection.getInstance(accessKey, secretKey, endpoint, port).getClient().listObjects(bucketName).getObjectSummaries();
	}

	/***devuelve el archivo del bucket*/
	public byte[] getObject(String fileName) throws IOException {
		log.debug("Get file : "+fileName);
		if(StorageConnection.getInstance(accessKey, secretKey, endpoint, port).getClient().doesObjectExist(bucketName, fileName)){
			S3Object fetchFile = StorageConnection.getInstance(accessKey, secretKey, endpoint, port).getClient().getObject(new GetObjectRequest(bucketName, fileName));
			S3ObjectInputStream stream = fetchFile.getObjectContent();
			try {
				byte[] content = IOUtils.toByteArray(stream);
				fetchFile.close();
				return content;
			} catch (IOException e) {
				throw new CustomParameterizedException(fileName + " Error a cargar");
			}
		}else{
			throw new CustomParameterizedException(fileName + " No existe");
		}
	}


	public Bucket createBucket(String fileName) throws IOException {
		log.debug("Create bucket : "+fileName);
		if (StorageConnection.getInstance(accessKey, secretKey, endpoint, port).getClient().doesBucketExist(bucketName)){
			throw new CustomParameterizedException(fileName + " Ya existe el Bucket");
		}else{
			return StorageConnection.getInstance(accessKey, secretKey, endpoint, port).getClient().createBucket(fileName);
		}

	}


	public Boolean delecFile(String fileName) throws IOException {
		log.debug("Delect file : "+fileName);
		if (StorageConnection.getInstance(accessKey, secretKey, endpoint, port).getClient().doesObjectExist(bucketName, fileName)){
			StorageConnection.getInstance(accessKey, secretKey, endpoint, port).getClient().deleteObject(bucketName, fileName);
			return true;
		}else{
			throw new CustomParameterizedException(fileName + " El objeto no existe");
		}

		
	}
	

}
