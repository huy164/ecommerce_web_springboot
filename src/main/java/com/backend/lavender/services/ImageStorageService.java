package com.backend.lavender.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageStorageService implements IStorageService {
    private final Path storageFolder = Paths.get("upload");

    public ImageStorageService() {
        try {
            Files.createDirectories(storageFolder);
        } catch (Exception exception) {
            throw new RuntimeException("Cannot initialize storage", exception);
        }
    }

    private boolean isImageFile(MultipartFile file) {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[] { "png", "jpg", "jpeg" }).contains(fileExtension.trim().toLowerCase());
    }

    @Override
    public String storeFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }
            if (!isImageFile(file)) {
                throw new RuntimeException("please upload image format ");
            }
            float fileSizeMegabytes = file.getSize() / 1000000.0f;
            if (fileSizeMegabytes > 5.0f) {
                throw new RuntimeException("File must be <= 5MB");
            }
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("-", "");
            generatedFileName = generatedFileName + "." + fileExtension;
            Path destinationFilePath = this.storageFolder.resolve(
                    Paths.get(generatedFileName))
                    .normalize()
                    .toAbsolutePath();
            if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
                throw new RuntimeException("cannot store file ouside current directory");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFileName;
        } catch (IOException exception) {
            throw new RuntimeException("Failed to store file ", exception);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    };

    @Override
    public byte[] readFileContent(String fileName) {
        return null;
    };

    @Override
    public void deleteAllFiles() {
    };

}
