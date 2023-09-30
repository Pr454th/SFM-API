package com.springcrud.springApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    @Id
    @Column(name="file_id",length=50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fileId;

    @Column(name="file_name",length=50)
    private String fileName;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="file_data",length=16777219)
    private byte[] fileData;

    @Column(name="file_year",length=50)
    private int year;

    @Override
    public String toString() {
        return "FileEntity{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", year=" + year +
                '}';
    }

    public FileEntity(String fileName,byte[] fileData,int year){
        this.fileData=fileData;
        this.fileName=fileName;
        this.year=year;
    }
}
