package nl.novi.backenddjdondiablo.service;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileDemoStorageService {

    private Path fileStoragePath;
    private String fileStorageLocation;

    public FileDemoStorageService(@Value("${File.upload-dir}") String fileStorageLocation){
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        this.fileStorageLocation =fileStorageLocation;

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue with creating file directory");
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);
        try {
            Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue with creating file directory",e);
        }
        return fileName;
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Resource downloadFile(String fileName) {

    Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);

    Resource resource;
    try {
        resource = new UrlResource(path.toUri());

    } catch (MalformedURLException e) {
        throw new RuntimeException("Issue in reading the file", e);
    }

    if(resource.exists() && resource.isReadable()){
        return resource;
    }else{
        throw new RuntimeException("the file doesn't exist or not readable");
    }
}
}
