package com.springcrud.springApp.service;

import com.springcrud.springApp.DTO.FileDTO;
import com.springcrud.springApp.DTO.FileDTOSave;
import com.springcrud.springApp.DTO.FileDTOUpdate;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface FileService {
    int addFile(MultipartFile file,String key);

    boolean getAuthenticate(int id,String key);

    List<FileDTO> getAllFiles();

    String updateFile(FileDTOUpdate fileDTOUpdate);

    boolean deleteFile(int id);

    FileDTO getFile(int id,String key);
}
