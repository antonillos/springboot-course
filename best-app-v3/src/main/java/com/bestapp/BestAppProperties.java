package com.bestapp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
@ConfigurationProperties(prefix = "betsconf")
public class BestAppProperties {
    public Resource getFileToProcessAsResource() {
        return new FileSystemResource(getFileToProcess());
    }

    private String fileToProcess;

    public String getFileToProcess() {
        return fileToProcess;
    }

    public void setFileToProcess(String fileToProcess) {
        this.fileToProcess = fileToProcess;
    }
}
