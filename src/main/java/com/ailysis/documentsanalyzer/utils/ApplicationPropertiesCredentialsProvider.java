package com.ailysis.documentsanalyzer.utils;

import ai.expert.nlapi.security.Credential;
import ai.expert.nlapi.security.DefaultCredentialsProvider;
import ai.expert.nlapi.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationPropertiesCredentialsProvider extends DefaultCredentialsProvider {

    @Value("${nlpai.username}")
    String username;

    @Value("${nlpai.password}")
    String password;


    @Override
    public Credential getCredentials() {
        log.debug("Getting Credentials from Application Properties...");
        String token = StringUtils.trim(System.getenv("EAI_TOKEN"));
        if (token != null && !token.isEmpty()) {
            log.info("Found Token from Environment Variables.");
        } else {
            if (username == null || username.isEmpty()) {
                return null;
            }

            if (password == null || password.isEmpty()) {
                return null;
            }

            log.info("Found Credentials from Application Properties.");
        }

        return new Credential(username, password, token);
    }
}
