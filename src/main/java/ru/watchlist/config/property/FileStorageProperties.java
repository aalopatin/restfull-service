package ru.watchlist.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

    private String logoDir;

    public String getLogoDir() {
        return logoDir;
    }

    public void setLogoDir(String logoDir) {
        this.logoDir = logoDir;
    }
}
