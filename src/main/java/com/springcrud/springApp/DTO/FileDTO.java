package com.springcrud.springApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    private int fileId;
    private String fileName;
    private byte[] fileData;
    private int year;

    @Override
    public String toString() {
        return "FileDTO{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileData=" + Arrays.toString(fileData) +
                ", year=" + year +
                '}';
    }
}
