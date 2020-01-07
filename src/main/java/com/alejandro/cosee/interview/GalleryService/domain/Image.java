package com.alejandro.cosee.interview.GalleryService.domain;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

@Entity
@Table(name= "image")
public class Image {

    @Id
    private String uuid;

    private String fileName;

    private String fileType;

    @CreatedDate
    private Date uploadedDate;

    @Transient
    private File file;

    public Image() {

    }

    public Image(String fileName, String fileType, Date uploadedDate, String uuid) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.uploadedDate = uploadedDate;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileTpe(String fileTpe) {
        this.fileType = fileTpe;
    }

    @Override
    public String toString() {
        return "DBFile{" +
                "uuid='" + uuid + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }

    public Date getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Date uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
