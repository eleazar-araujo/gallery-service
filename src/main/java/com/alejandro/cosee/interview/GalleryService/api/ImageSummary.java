package com.alejandro.cosee.interview.GalleryService.api;

public class ImageSummary {

    private String uuid;

    private String fileName;

    private String fileType;

    public ImageSummary(String uuid, String fileName, String fileType) {
        this.uuid = uuid;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public String getId() {
        return uuid;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }



}
