package com.springcrud.springApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.Arrays;

@Entity
@Table(name="files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    @Id
    @Column(name="file_id",length=100)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fileId;

    @Column(name="file_name",length=50)
    private String fileName;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="file_data",length=99999999)
    private byte[] fileData;

    @Column(name="file_key",length=50)
    private String key;

    @Override
    public String toString() {
        return "FileEntity{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileData=" + Arrays.toString(fileData) +
                ", key='" + key + '\'' +
                '}';
    }

    public FileEntity(String fileName, byte[] fileData, String key){
        this.fileData=fileData;
        this.fileName=fileName;
        this.key=key;
    }
}
