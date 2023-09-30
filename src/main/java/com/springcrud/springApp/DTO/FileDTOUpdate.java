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
    private int year;

    @Override
    public String toString() {
        return "FileDTO{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", year=" + year +
                '}';
    }
}