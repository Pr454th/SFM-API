package com.springcrud.springApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTOSave {
    private String fileName;
    private byte[] fileData;
    private String key;

    @Override
    public String toString() {
        return "FileDTOSave{" +
                "fileName='" + fileName + '\'' +
                ", fileData=" + Arrays.toString(fileData) +
                ", key='" + key + '\'' +
                '}';
    }
}