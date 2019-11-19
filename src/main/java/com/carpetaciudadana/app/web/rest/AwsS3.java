package com.carpetaciudadana.app.web.rest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.carpetaciudadana.app.service.StorageService;
import com.carpetaciudadana.app.web.rest.errors.CustomParameterizedException;
import com.carpetaciudadana.app.web.rest.util.HeaderUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.core.JsonParseException;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/template")
public class AwsS3 {


    private final Logger log = LoggerFactory.getLogger(AwsS3.class);

    private final StorageService storageService;

    public AwsS3(StorageService storageService){
        this.storageService=storageService;
    }
    /**
     * 
     * @param name
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/file/AWSResource" , produces = {
        MediaType.IMAGE_PNG_VALUE,
        MediaType.TEXT_PLAIN_VALUE,
        MediaType.TEXT_HTML_VALUE
})
    public @ResponseBody ResponseEntity<byte[]> getFiles(@RequestParam(value = "name",required = true)String name) throws IOException{
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+name);
        return ResponseEntity.ok().headers(header).contentType(MediaType.parseMediaType("application/octet-stream")).body(storageService.getObject(name));
    }
    /**
     * 
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/file/AWSgetListResource")
    public ResponseEntity<List<S3ObjectSummary>> getListFiles() throws IOException{
        return ResponseEntity.accepted().body(storageService.getFrom());
    }
    /**
     * 
     * @param files
     * @param bool
     * @return
     * @throws IOException
     */
    @PutMapping(value = "/file/AWSResource", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> setpngmedia(@RequestParam(value = "files", required = true) MultipartFile files,@RequestParam(value = "bool", required = true) Boolean bool) throws IOException{
        return ResponseEntity.accepted().body(HeaderUtil.successMsj(storageService.put(files, bool)));
    }
    /**
     * 
     * @param name
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/file/AWSBucket")
    public ResponseEntity<Bucket> createBucket(@RequestParam(value = "name",required = true)String name) throws IOException{
        return ResponseEntity.accepted().body(storageService.createBucket(name));
    }
    /**
     * 
     * @param name
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/file/AWSDeletMedia")
    public ResponseEntity<String> DelMedia(@RequestParam(value = "name",required = true) String name) throws IOException{
        if (storageService.delecFile(name)){
            throw new CustomParameterizedException(name + "No se pudo borrar");
        }else {
            return ResponseEntity.accepted().body(name + "Ha sido borrado");
        }
    
    }
}