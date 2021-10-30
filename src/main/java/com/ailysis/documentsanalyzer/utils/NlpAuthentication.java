package com.ailysis.documentsanalyzer.utils;

import ai.expert.nlapi.security.Authentication;
import ai.expert.nlapi.security.Authenticator;
import ai.expert.nlapi.security.BasicAuthenticator;
import ai.expert.nlapi.v2.API;
import ai.expert.nlapi.v2.cloud.Analyzer;
import ai.expert.nlapi.v2.cloud.AnalyzerConfig;
import ai.expert.nlapi.v2.cloud.Categorizer;
import ai.expert.nlapi.v2.cloud.CategorizerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NlpAuthentication {

    @Autowired
    ApplicationPropertiesCredentialsProvider credentialsProvider;


    public Authentication createAuthentication() throws Exception {
        Authenticator authenticator = new BasicAuthenticator(credentialsProvider);
        return new Authentication(authenticator);
    }

    public Analyzer createAnalyzer() throws Exception {
        return new Analyzer(AnalyzerConfig.builder()
                .withVersion(API.Versions.V2)
                .withContext("standard")
                .withLanguage(API.Languages.en)
                .withAuthentication(createAuthentication())
                .build());
    }

    public Categorizer createCategorizer() throws Exception {
        return new Categorizer(CategorizerConfig.builder()
                .withVersion(API.Versions.V2)
                .withTaxonomy("iptc")
                .withLanguage(API.Languages.en)
                .withAuthentication(createAuthentication())
                .build());
    }
}
