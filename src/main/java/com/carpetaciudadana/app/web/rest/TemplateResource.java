package com.carpetaciudadana.app.web.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

import com.carpetaciudadana.app.domain.EstudianteCedel;
import com.carpetaciudadana.app.service.PdfService;
import com.carpetaciudadana.app.service.dto.TituloCedelDTOpdf;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hazelcast.com.eclipsesource.json.ParseException;
import com.itextpdf.text.DocumentException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping(value = "/file/setmedia", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String setpngmedia(@RequestParam(value = "files", required = true) MultipartFile files) throws IOException, JsonMappingException, JsonParseException, NotFoundException, ParseException{
        return pdfService.savePNG(files, template) + " fue guardado con exito";
    }
   /* @PostMapping(value = "/file/Resource", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String setCertificado(@RequestParam(value = "files", required = true) MultipartFile files) throws IOException{
        return pdfService.setCertificado(files,template) + " fue guardado con exito";
    }*/
    @GetMapping(value = "/file/Resource")
    public ResponseEntity<Set<String>> getCertificado() throws IOException, JsonMappingException, JsonParseException, NotFoundException, ParseException{  
        return ResponseEntity.accepted().body(pdfService.getCertificado(template));
        //return new ResponseEntity<>(pdfService.getCertificado(template), HttpStatus.OK);
    }
    @GetMapping(value = "/file/getListResource")
    public ResponseEntity<Set<String>> getListFiles() throws IOException, JsonMappingException, JsonParseException, NotFoundException, ParseException{
        return ResponseEntity.accepted().body(pdfService.getListFiles(template));
    }
    @PostMapping(value = "/file/DeletMedia")
    public ResponseEntity<String> DelMedia(@RequestParam(value = "name",required = true) String name) throws IOException, JsonMappingException, JsonParseException, NotFoundException, ParseException{
        return ResponseEntity.accepted().body(pdfService.deleteFiles(name, template));
    }
  
}
