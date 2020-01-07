package com.alejandro.cosee.interview.GalleryService.service;

import com.alejandro.cosee.interview.GalleryService.domain.Image;
import com.alejandro.cosee.interview.GalleryService.repository.ImageRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImagesService {

    @Autowired
    private ImageRepository repository;

    @Autowired
    private ImageFileStorageService imageFileStorageService;

    public List<Image> getAllImages(){
        return repository.findAll();
    }

    public Image addImage(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
          if (fileName.contains("..")){
              throw new RuntimeException("Filename contains invalid characters");
          }

          String uuid = UUID.randomUUID().toString();
          Image image = new Image(fileName, file.getContentType(), new Date(), uuid);
          String path = imageFileStorageService.storeImageFile(file.getBytes(), uuid);
          return repository.save(image);

        } catch (Exception exc){
          throw new RuntimeException("Unable to get extract the file content");
        }

    }

    public void deleteImage(String uuid){
        repository.deleteById(uuid);
    }

    public Optional<Image> getImage(String uuid){
        Optional<Image> image = repository.findById(uuid);
        image.ifPresent(imageFound -> {
            File imageFile = imageFileStorageService.getImageFile(uuid);
            imageFound.setFile(imageFile);
        });
        return image;
    }
}
