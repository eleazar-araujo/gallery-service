package com.alejandro.cosee.interview.GalleryService.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ImageFileStorageService implements CommandLineRunner {

    private Path imageFolder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Creating folder");
        imageFolder = Files.createTempDirectory("images");
        System.out.println("folder is at " + imageFolder.toString());
    }

    public String storeImageFile(byte[] bytes, String fileName){
        String totalPath = getFilePath(fileName);

        try (FileOutputStream fos = new FileOutputStream(totalPath)) {
            fos.write(bytes);
            return totalPath;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalPath;

    }

    private String getFilePath(String fileName) {
        String folderPath = imageFolder.toString();
        return folderPath + "/" + fileName;
    }

    public File getImageFile(String uuid){
        File file = new File(getFilePath(uuid));
        if (file.exists()){
            return file;
        }
        throw new RuntimeException("File not found");
    }


}
