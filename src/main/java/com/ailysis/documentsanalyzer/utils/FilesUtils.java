package com.ailysis.documentsanalyzer.utils;

import com.ailysis.documentsanalyzer.domain.dto.DocumentRequestDto;
import com.ailysis.documentsanalyzer.exception.DocumentNameExistsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.ailysis.documentsanalyzer.constant.Contants.DIRECTORY_CREATED;
import static com.ailysis.documentsanalyzer.constant.Contants.USER_FOLDER;

@Slf4j
public class FilesUtils {

    public static DocumentRequestDto saveFile(String text, String title) throws IOException, DocumentNameExistsException {
        Path userFolder = Path.of(USER_FOLDER /* TODO user.getUsername()*/);
        Path filePath = Path.of(USER_FOLDER, title);
        if (Files.exists(filePath)) {
            throw new DocumentNameExistsException("A document with the name: '" + title + "', already exists.");
        }
        if(!Files.exists(userFolder)) {
            Files.createDirectories(userFolder);
            log.info(DIRECTORY_CREATED + userFolder);
        }
        Path newFile = Files.createFile(filePath);
        Files.writeString(newFile, text, StandardCharsets.UTF_8);
        FileChannel fileChannel = FileChannel.open(newFile);
        long fileSize = fileChannel.size();

        DocumentRequestDto fileDto = new DocumentRequestDto(title, text, newFile.toAbsolutePath().toString(), FileUtils.byteCountToDisplaySize(fileSize));
        return fileDto;
    }

    public static DocumentRequestDto processAndSaveFile(MultipartFile documentDto) throws IOException, DocumentNameExistsException {
        Path userFolder = Path.of(USER_FOLDER /* TODO user.getUsername()*/);
        String fileName = documentDto.getOriginalFilename();
        long size = documentDto.getSize();
        Path filePath = Paths.get(USER_FOLDER + documentDto.getOriginalFilename());
        if (Files.exists(filePath)) {
            throw new DocumentNameExistsException("A document with the name: '" + fileName + "', already exists.");
        }
        if(!Files.exists(userFolder)) {
            Files.createDirectories(userFolder);
            log.info(DIRECTORY_CREATED + userFolder);
        }
        Path newFile = Files.createFile(filePath);
        documentDto.transferTo(newFile);
        String content = FilesUtils.reduceFile(Files.readString(newFile));

        DocumentRequestDto fileDto = new DocumentRequestDto(fileName, content, filePath.toString(), FileUtils.byteCountToDisplaySize(size));
        return fileDto;
    }

    public static String reduceFile(String content) {
        if (content.length() > 10000) {
            return content.substring(0, 9999);
        }
        return content;
    }

    public static void deleteFile(String path) throws IOException {
        Files.delete(Paths.get(path));
    }
}
