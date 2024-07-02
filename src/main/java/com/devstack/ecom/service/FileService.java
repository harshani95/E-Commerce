package com.devstack.ecom.service;

import com.devstack.ecom.util.CommonFileSavedBinaryDataDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public CommonFileSavedBinaryDataDTO createResource(MultipartFile file, String directory, String bucket);
    public void deleteResource(String bucket,String directory, String fileName);
    public byte[] downloadFile(String bucket, String fileName);
}
