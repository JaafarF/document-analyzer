package com.ailysis.documentsanalyzer.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.ailysis.documentsanalyzer.constant.Contants.DIRECTORY_CREATED;
import static com.ailysis.documentsanalyzer.constant.Contants.USER_FOLDER;

@Slf4j
@Component
public class FilesUtils {

    public String saveFile(String text, String title) {
        Path userFolder = Path.of(USER_FOLDER /* TODO user.getUsername()*/);
        Path file = Path.of(USER_FOLDER, title);
        try {

            if(!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
                log.info(DIRECTORY_CREATED + userFolder);
            }
            Path newFile = Files.createFile(file);
            Files.writeString(newFile, text, StandardCharsets.UTF_8);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return file.toString();
    }
}
