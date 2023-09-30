package com.springcrud.springApp.service.Impl;

import com.springcrud.springApp.DTO.FileDTO;
import com.springcrud.springApp.DTO.FileDTOSave;
import com.springcrud.springApp.DTO.FileDTOUpdate;
import com.springcrud.springApp.entity.FileEntity;
import com.springcrud.springApp.repo.FileRepo;
import com.springcrud.springApp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.MediaType;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepo fileRepo;

    @Override
    public String addFile(FileDTOSave fileDTOSave){
        FileEntity fileEntity=new FileEntity(fileDTOSave.getFileName(),fileDTOSave.getFileData(),fileDTOSave.getYear());
        fileRepo.save(fileEntity);
        return fileEntity.getFileName();
    }

    @Override
    public List<FileDTO> getAllFiles(){
        List<FileEntity> getfiles=fileRepo.findAll();
        List<FileDTO> fileList=new ArrayList<>();
        for(FileEntity fe:getfiles){
            FileDTO f=new FileDTO(fe.getFileId(),fe.getFileName(),fe.getFileData(),fe.getYear());
            fileList.add(f);
        }
        return fileList;
    }

    @Override
    public String updateFile(FileDTOUpdate fileDTOUpdate){
        if(fileRepo.existsById(fileDTOUpdate.getFileId())){
            FileEntity fe=fileRepo.getById(fileDTOUpdate.getFileId());
            fe.setFileName(fileDTOUpdate.getFileName());
            fe.setYear(fileDTOUpdate.getYear());
            fileRepo.save(fe);
            return fileDTOUpdate.getFileName();
        }
        else{
            return "Doesn't Exist";
        }
    }

    @Override
    public boolean deleteFile(int id){
        if(fileRepo.existsById(id)){
            FileEntity fe=fileRepo.getById(id);
            fileRepo.delete(fe);
            return true;
        }
        return false;
    }

    @Override
    public FileDTO getFile(int id){
        Optional<FileEntity> optionalFile = fileRepo.findById(id);

        if (optionalFile.isPresent()) {
            FileEntity encryptedFile = optionalFile.get();
            byte[] encryptedFileBytes = encryptedFile.getFileData();

            // Decrypt the file content
            try {
                // Generate an AES decryption key using the same key used for encryption
                byte[] aesKeyBytes = "YourSecretKey123".getBytes(); // Replace with your secret key
                SecretKeySpec aesKey = new SecretKeySpec(aesKeyBytes, "AES");

                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, aesKey);

                byte[] decryptedFileBytes = cipher.doFinal(encryptedFileBytes);

                FileDTO file=new FileDTO();
                file.setFileName(encryptedFile.getFileName());
                file.setFileId(encryptedFile.getFileId());
                file.setFileData(decryptedFileBytes);

                return file;
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                     IllegalBlockSizeException | BadPaddingException e) {
                e.printStackTrace();
                // Handle decryption error
            }
        }
        return null;
    }
}
