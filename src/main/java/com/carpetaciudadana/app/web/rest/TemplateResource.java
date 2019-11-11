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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import freemarker.template.TemplateException;
import io.swagger.models.Model;



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
    @PutMapping(value = "/file/setmedia", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String setpngmedia(Model model, @RequestParam(value = "files", required = true) MultipartFile files) throws IOException{

        File file = new File("F:/Carpeta-cuidadana/carpeta-ciudadana-template/src/main/resources/templates/xml/",
            files.getOriginalFilename());
        try (FileOutputStream f = new FileOutputStream(file)){
            f.write(files.getBytes());
         } catch (Exception e) {
           e.printStackTrace();
        }
        //Path filepath = Paths.get("/", files.getOriginalFilename());
        //String filePath = request.getServletContext().getRealPath("/"); 
        //files[0].transferTo(new File(filepath));
        return files.getOriginalFilename();
    }
}
