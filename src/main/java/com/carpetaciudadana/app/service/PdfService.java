package com.carpetaciudadana.app.service;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import freemarker.template.TemplateException;
import com.itextpdf.text.DocumentException;

import org.springframework.web.multipart.MultipartFile;

public interface PdfService {

	public File createHtmlWithData(String templateFile, String pathTemplates, String jsonData)
			throws IOException, TemplateException;

	public File generatePDFFromHTML(String filename, String fileExt, File html, Boolean alineacionHorizontal) throws DocumentException, IOException;

	public String savePNG(MultipartFile files, String template) throws IOException;
	
	public Set<String> getListFiles(String template) throws IOException;

	public String deleteFiles(String name,String template) throws IOException;
	
	public File generatePDFFromBase64(String filename, String fileExt,String file) throws IOException;
}
