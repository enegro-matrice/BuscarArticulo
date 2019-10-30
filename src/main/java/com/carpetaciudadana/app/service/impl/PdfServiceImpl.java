package com.carpetaciudadana.app.service.impl;

import com.carpetaciudadana.app.service.PdfService;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;



/**
 * Servicios para guardar informacion en storage. Implementacion S3
 * 
 * @autor leonardopalazzo@buenosaires.gob.ar
 **/
@Service
public class PdfServiceImpl implements PdfService {

	/**
	 * A partir de un Template y datos en JSON genera un HTML
	 * 
	 * @return
	 */
	public File createHtmlWithData(String templateFile, String pathTemplates, String jsonData)
			throws IOException, TemplateException {

		Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		Map<String, Object> input = new HashMap<String, Object>();
		HashMap<String, String> result = new ObjectMapper().readValue(jsonData, HashMap.class);

		input.putAll(result);
		cfg.setDirectoryForTemplateLoading(new File(pathTemplates));
		Template template = cfg.getTemplate(templateFile);
		File tempFile = File.createTempFile("tempFile" + UUID.randomUUID(), "html");
		Writer fileWriter = new FileWriter(tempFile);
		try {
			template.process(input, fileWriter);
		} finally {
			fileWriter.close();
		}

		return tempFile;
	}

	/**
	 * A partir de un HTML genera un PDF
	 * 
	 * @return
	 */
	public File generatePDFFromHTML(String filename, String fileExt,File html, Boolean alineacionHorizontal) throws DocumentException, IOException {
		File pdfFile = File.createTempFile(filename, fileExt);

		DataOutputStream stream = new DataOutputStream(new FileOutputStream(pdfFile));
		Document document = new Document();
		if(alineacionHorizontal)
			document.setPageSize(PageSize.A4.rotate());
		PdfWriter writer = PdfWriter.getInstance(document, stream);
		if(alineacionHorizontal)
			writer.addPageDictEntry(PdfName.ROTATE, PdfPage.LANDSCAPE);
		document.open();		
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(html));
		document.close();

		return pdfFile;
	}	

	
	public File generatePDFFromBase64(String filename, String fileExt,String file) throws IOException {
		File pdfFile=File.createTempFile(filename, fileExt);
		Files.write(Base64.decode(file), pdfFile);
		return pdfFile;
	}
}
