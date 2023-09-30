package com.springcrud.springApp.service;

import com.springcrud.springApp.DTO.FileDTO;
import com.springcrud.springApp.DTO.FileDTOSave;
import com.springcrud.springApp.DTO.FileDTOUpdate;

import java.util.List;

public interface FileService {
    String addFile(FileDTOSave fileDTOSave);

    List<FileDTO> getAllFiles();

    String updateFile(FileDTOUpdate fileDTOUpdate);

    boolean deleteFile(int id);

    FileDTO getFile(int id);
}
