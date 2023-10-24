package com.springcrud.springApp.controllers;

import com.springcrud.springApp.DTO.FileDTO;
import com.springcrud.springApp.DTO.FileDTOSave;
import com.springcrud.springApp.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

@RestController
@CrossOrigin(origins = "http://localhost:5173",exposedHeaders = "Content-Disposition")
@RequestMapping("api")
public class Controller {

    @Autowired
    private FileService fileService;

    @PostMapping(path="/save")
    public int handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam("key") String key) {
        if (!file.isEmpty()) {
            try {
//                // Generate an AES encryption key (16 bytes for AES-128)
//                byte[] aesKeyBytes = "YourSecretKey123".getBytes(); // Replace with your secret key
//                SecretKeySpec aesKey = new SecretKeySpec(aesKeyBytes, "AES");
//
//                // Initialize the AES cipher in encryption mode
//                Cipher cipher = Cipher.getInstance("AES");
//                cipher.init(Cipher.ENCRYPT_MODE, aesKey);
//
//                // Encrypt the uploaded file bytes
//                byte[] encryptedFileBytes = cipher.doFinal(file.getBytes());
//
//                // Save the encrypted file to the database
//                FileDTOSave encryptedFile = new FileDTOSave();
//                encryptedFile.setFileName(file.getOriginalFilename());
//                encryptedFile.setFileData(encryptedFileBytes);
                int id=fileService.addFile(file,key);

                // Redirect to a success page
                return id;
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the error
                return -1;
            }
        } else {
            return -1;
        }
    }

    @GetMapping("/download/{id}/{key}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable(value="id") int id,@PathVariable(value="key") String key) {

        boolean authentify=fileService.getAuthenticate(id,key);
        if(authentify) {
            FileDTO file = fileService.getFile(id,key);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.set("Content-Disposition", "attachment; filename=" + file.getFileName());
            //        headers.setContentDispositionFormData("attachment", file.getFileName());
            //        headers.add("Content-Disposition", "attachment; year="+String.valueOf(file.getYear()));
            ByteArrayResource resource = new ByteArrayResource(file.getFileData());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        }
        return null;
    }

//    @PostMapping(path="/save")
//    public String saveFile(@RequestBody FileDTOSave fileDTOSave){
//        String id=fileService.addFile(fileDTOSave);
//        return id;
//    }

    @GetMapping(path="/files")
    public List<FileDTO> getAllFiles(){
        List<FileDTO> allFiles=fileService.getAllFiles();
        return allFiles;
    }

    @DeleteMapping(path="/delete/{id}")
    public String deleteFile(@PathVariable(value="id")int id){
        boolean deleted= fileService.deleteFile(id);
        if(deleted) return "true";
        return "false";
    }
}
