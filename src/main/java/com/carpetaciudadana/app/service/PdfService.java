package com.carpetaciudadana.app.service;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.io.Resources;
import com.itextpdf.text.DocumentException;

import org.springframework.boot.json.JsonParseException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import freemarker.core.ParseException;
import freemarker.template.TemplateException;

public interface PdfService {
	/**
	 * 
	 * @param templateFile
	 * @param pathTemplates
	 * @param jsonData
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public File createHtmlWithData(String templateFile, String pathTemplates, String jsonData)
			throws IOException, TemplateException;
	/**
	 * 
	 * @param filename
	 * @param fileExt
	 * @param html
	 * @param alineacionHorizontal
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public File generatePDFFromHTML(String filename, String fileExt, File html, Boolean alineacionHorizontal) throws DocumentException, IOException;
	/**
	 * 
	 * @param files
	 * @param template
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws NotFoundException
	 * @throws ParseException
	 */
	public String savePNG(MultipartFile files, String template) throws IOException, JsonMappingException, JsonParseException, NotFoundException, ParseException;

	/**
	 * 
	 * @param name
	 * @param template
	 * @return
	 * @throws IOException
	 */
	public ByteArrayResource GetData(String name, String template) throws IOException;

	/**
	 * 
	 * @param template
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws NotFoundException
	 * @throws ParseException
	 */
	public Set<String> getListFiles(String template) throws IOException, JsonMappingException, JsonParseException, NotFoundException, ParseException;
	/**
	 * 
	 * @param name
	 * @param template
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws NotFoundException
	 * @throws ParseException
	 */
	public String deleteFiles(String name,String template) throws IOException, JsonMappingException, JsonParseException, NotFoundException, ParseException;
	
}
