package com.carpetaciudadana.app.service.impl;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
//import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.carpetaciudadana.app.service.PdfService;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.common.io.Files;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	private final Logger log = LoggerFactory.getLogger(PdfServiceImpl.class);
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

	public File generatePDFFromHTML(String filename, String fileExt, File html, Boolean alineacionHorizontal)
			throws DocumentException, IOException {
		File pdfFile = File.createTempFile(filename, fileExt);

		DataOutputStream stream = new DataOutputStream(new FileOutputStream(pdfFile));
		Document document = new Document();
		if (alineacionHorizontal)
			document.setPageSize(PageSize.A4.rotate());
		PdfWriter writer = PdfWriter.getInstance(document, stream);
		if (alineacionHorizontal)
			writer.addPageDictEntry(PdfName.ROTATE, PdfPage.LANDSCAPE);
		document.open();
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(html));
		document.close();

		return pdfFile;
	}

	public String savePNG(MultipartFile files, String template) throws IOException {
		File file = new File(template, files.getOriginalFilename());
		try (FileOutputStream f = new FileOutputStream(file)) {
			f.write(files.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return files.getOriginalFilename();
	}

	public Set<String> getListFiles(String template) throws IOException {
		Set<String> fileList = new HashSet<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(template))) {
			for (Path path : stream) {
				if (!Files.isDirectory(path)) {
					fileList.add(path.getFileName().toString());
				}
			}
		}
		return fileList;
	}

	public String deleteFiles(String name, String template) throws IOException {
		File fileToDelete = FileUtils.getFile(template + name);
		try {
			boolean success = FileUtils.deleteQuietly(fileToDelete);
			if (success) {
				return name + " Eliminado";
			} else {
				return " No se encontro el archivo";
			}

		} catch (Exception e) {
			return "No se pudo borrar el archivo";
		}

	}
	public ByteArrayResource GetData(String name, String template) throws IOException {
			File file = new File(template+name);
			Path path = Paths.get(file.getAbsolutePath());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
			return resource;
	}
}
