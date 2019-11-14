package com.carpetaciudadana.app.service;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import freemarker.core.ParseException;
import freemarker.template.TemplateException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.itextpdf.text.DocumentException;

import org.springframework.boot.json.JsonParseException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface PdfService {

	public File createHtmlWithData(String templateFile, String pathTemplates, String jsonData)
			throws IOException, TemplateException;

	public File generatePDFFromHTML(String filename, String fileExt, File html, Boolean alineacionHorizontal) throws DocumentException, IOException;

	public String savePNG(MultipartFile files, String template) throws IOException, JsonMappingException, JsonParseException, NotFoundException, ParseException;

//	public String setCertificado(MultipartFile files, String template) throws IOException;
	
	public Set<String> getListFiles(String template) throws IOException, JsonMappingException, JsonParseException, NotFoundException, ParseException;
	
	public Set<String> getCertificado(String template) throws IOException, JsonMappingException, JsonParseException, NotFoundException, ParseException;

	public String deleteFiles(String name,String template) throws IOException, JsonMappingException, JsonParseException, NotFoundException, ParseException;
	
	public File generatePDFFromBase64(String filename, String fileExt,String file) throws IOException, JsonMappingException, JsonParseException, NotFoundException, ParseException;
}
