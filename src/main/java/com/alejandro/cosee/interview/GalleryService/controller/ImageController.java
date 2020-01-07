package com.alejandro.cosee.interview.GalleryService.controller;

import com.alejandro.cosee.interview.GalleryService.api.ImageSummary;
import com.alejandro.cosee.interview.GalleryService.domain.Image;
import com.alejandro.cosee.interview.GalleryService.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImagesService imagesService;


    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file){
        Image savedImage = imagesService.addImage(file);
        return new ResponseEntity<>("Image saved successfully", HttpStatus.OK);
    }


    @GetMapping()
    public List<ImageSummary> getAllImages(){
        List<Image> imagesFromDB = imagesService.getAllImages();
        return imagesFromDB.stream()
                .map(imageFromDB -> new ImageSummary(imageFromDB.getUuid(),  imageFromDB.getFileName(),
                        imageFromDB.getFileType())).collect(Collectors.toList());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String uuid){
        Optional<Image> imageFromDB = imagesService.getImage(uuid);

        ByteArrayResource body = null;
        Image image = imageFromDB.get();
        try {
            body = new ByteArrayResource(Files.readAllBytes(image.getFile().toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"" )
                .body(body);
    }



}
