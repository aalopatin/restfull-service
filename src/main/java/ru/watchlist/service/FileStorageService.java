package ru.watchlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.watchlist.config.property.FileStorageProperties;
import ru.watchlist.rest.exception.FileNotFoundException;
import ru.watchlist.rest.exception.FileStorageException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path logoStorageLocation;

    private final String logoDir;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {

        this.logoDir = fileStorageProperties.getLogoDir();
        this.logoStorageLocation = Paths.get(this.logoDir)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.logoStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String uploadLogo(Long id, MultipartFile file) throws FileStorageException {

        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        String logoFileName = id.toString() + "." + fileExtension;

        try {
            if(logoFileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + logoFileName);
            }
            Path targetLocation = this.logoStorageLocation.resolve(logoFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return logoFileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + logoFileName + ". Please try again!", ex);
        }
    }

    public void deleteLogo(String logoFileName) {
        try {
            if(logoFileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + logoFileName);
            }
            Path targetLocation = this.logoStorageLocation.resolve(logoFileName);
            Files.delete(targetLocation);
        } catch (IOException ex) {
            throw new FileStorageException("Could not delete file " + logoFileName + ". Please try again!", ex);
        }
    }

    public String getLogoURI(String logoFileName){
        if (logoFileName == null) {
            return null;
        }
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(logoDir)
                .path(logoFileName)
                .toUriString();
    }



}
