package com.carpetaciudadana.app.service;

import java.io.File;
import java.io.IOException;

import freemarker.template.TemplateException;
import com.itextpdf.text.DocumentException;

public interface PdfService {

	public File createHtmlWithData(String templateFile, String pathTemplates, String jsonData)
			throws IOException, TemplateException;

	public File generatePDFFromHTML(String filename, String fileExt, File html, Boolean alineacionHorizontal) throws DocumentException, IOException;

	
	public File generatePDFFromBase64(String filename, String fileExt,String file) throws IOException;
}
