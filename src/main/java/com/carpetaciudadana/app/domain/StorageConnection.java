package com.carpetaciudadana.app.domain;


import com.amazonaws.auth.BasicAWSCredentials;
import com.emc.vipr.services.s3.ViPRS3Client;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
public class StorageConnection {
	
	private BasicAWSCredentials creds;
	private ViPRS3Client client;
	
	private static StorageConnection storageConnection;
	
	public StorageConnection() {}

	@SuppressWarnings("deprecation")
	private StorageConnection(String accessKey, String secretKey,String endpoint) {
		this.creds = new BasicAWSCredentials(accessKey, secretKey);
		this.client = new ViPRS3Client(endpoint, creds);
		this.client.setEndpoint(endpoint);
	}
	
	@Scope("singleton")
	public static StorageConnection getInstance(String accessKey,String secretKey,String endpoint,String port) {
		if(storageConnection==null) {
			storageConnection=new StorageConnection(accessKey, secretKey, endpoint + ":" + port);
		}
		return storageConnection;
	}

	public BasicAWSCredentials getCreds() {
		return creds;
	}

	public ViPRS3Client getClient() {
		return client;
	}



}
