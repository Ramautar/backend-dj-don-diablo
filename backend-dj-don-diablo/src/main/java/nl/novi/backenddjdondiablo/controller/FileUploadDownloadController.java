package nl.novi.backenddjdondiablo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.novi.backenddjdondiablo.domain.Demo;
import nl.novi.backenddjdondiablo.payload.request.DemoUploadRequest;
import nl.novi.backenddjdondiablo.payload.response.FileUploadResponse;
import nl.novi.backenddjdondiablo.service.DemoServiceImpl;
import nl.novi.backenddjdondiablo.service.FileDemoStorageService;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.core.io.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class FileUploadDownloadController {

    @Autowired
    private FileDemoStorageService fileDemoStorageService;

    @Autowired
    private DemoServiceImpl demoService;

    public FileUploadDownloadController(FileDemoStorageService fileDemoStorageService) {
        this.fileDemoStorageService = fileDemoStorageService;
    }

    @PostMapping("/upload")
    FileUploadResponse demoMP3Upload(@RequestParam("file") MultipartFile file, @RequestParam("model") String model){
        String fileName = fileDemoStorageService.storeFile(file);

        // http://localhost:8080/download/"fileName"
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileName)
                .toUriString();

        String contentType = file.getContentType();

        ObjectMapper mapper = new ObjectMapper();
        try {
            Demo modelDemo = mapper.readValue(model, Demo.class);
            modelDemo.setDemoURL(url);
            long demoId = demoService.createDemo(modelDemo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("issue with uploading data",e);
        }

        FileUploadResponse response = new FileUploadResponse(fileName, contentType, url);
        return response;
    }

    @GetMapping("/download/{fileName}")
    ResponseEntity<Resource> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileDemoStorageService.downloadFile(fileName);
        String mimeType;
        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        mimeType = mimeType == null ? MediaType.APPLICATION_OCTET_STREAM_VALUE : mimeType;

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName="+resource.getFilename())
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }
}
