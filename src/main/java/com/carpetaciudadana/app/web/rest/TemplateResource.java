package com.carpetaciudadana.app.web.rest;

import com.carpetaciudadana.app.domain.EstudianteCedel;
import com.carpetaciudadana.app.service.PdfService;
import com.carpetaciudadana.app.service.dto.TituloCedelDTOpdf;
import com.itextpdf.text.DocumentException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import freemarker.template.TemplateException;



@RestController
@RequestMapping("/template")
public class TemplateResource {

    @Value("${app.template}")
	private String template;


    private final Logger log = LoggerFactory.getLogger(TemplateResource.class);

    private final PdfService pdfService;

    public TemplateResource(PdfService pdfService){
        this.pdfService=pdfService;
    }


    @GetMapping(value = "/file/media/", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPdfMedia(@RequestParam(value = "json", required = false) String json) throws IOException,TemplateException,DocumentException{
        log.debug("MediaType Generator"+ json);		
        log.debug(new TituloCedelDTOpdf(new EstudianteCedel().apellido("Pizarro").nombre("Maximiliano").dni("36771843").curso("Apache Freemarker").fechaFin("201812").duracion("1").toJson(),template).toString());
        return Files.readAllBytes(pdfService.generatePDFFromHTML
        ("Certificado Cedel",".pdf",
        pdfService.createHtmlWithData
        ("CERTIFICADO_CEDEL.ftl", template, new TituloCedelDTOpdf(new EstudianteCedel().apellido("Pizarro").nombre("Maximiliano").dni("36771843").curso("Apache Freemarker").fechaFin("201812").duracion("1").toJson(),template).toString())
        ,true).toPath());
    }
}
