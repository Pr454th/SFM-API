package com.springcrud.springApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTOUpdate {
    private int fileId;
    private String fileName;
    private String key;

    @Override
    public String toString() {
        return "FileDTOUpdate{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}