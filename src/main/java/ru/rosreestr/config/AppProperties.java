package ru.rosreestr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AppProperties {

    @Value("${ws.client.isur}")
    private String isur;

    @Value("${signature.provider}")
    private String signatureProvider;

    @Value("${signature.alias}")
    private  String signatureAlias;

    @Value("${signature.password}")
    private String signaturePassword;

    @Value("${datasource.name}")
    private String datasourceName;

    @Value("${datasource.driver-class-name}")
    private String datasourceDriverClassName;

    @Value("${datasource.url}")
    private String datasourceURL;

    @Value("${datasource.username}")
    private String datasourceUsername;

    @Value("${datasource.password}")
    private String datasourcePassword;

    public String getIsur() {
        return isur;
    }

    public void setIsur(String isur) {
        this.isur = isur;
    }

    public String getSignatureAlias() {
        return signatureAlias;
    }

    public void setSignatureAlias(String signatureAlias) {
        this.signatureAlias = signatureAlias;
    }

    public String getSignaturePassword() {
        return signaturePassword;
    }

    public void setSignaturePassword(String signaturePassword) {
        this.signaturePassword = signaturePassword;
    }

    public String getSignatureProvider() {
        return signatureProvider;
    }

    public void setSignatureProvider(String signatureProvider) {
        this.signatureProvider = signatureProvider;
    }

    public String getDatasourceDriverClassName() {
        return datasourceDriverClassName;
    }

    public void setDatasourceDriverClassName(String datasourceDriverClassName) {
        this.datasourceDriverClassName = datasourceDriverClassName;
    }

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }

    public String getDatasourcePassword() {
        return datasourcePassword;
    }

    public void setDatasourcePassword(String datasourcePassword) {
        this.datasourcePassword = datasourcePassword;
    }

    public String getDatasourceURL() {
        return datasourceURL;
    }

    public void setDatasourceURL(String datasourceURL) {
        this.datasourceURL = datasourceURL;
    }

    public String getDatasourceUsername() {
        return datasourceUsername;
    }

    public void setDatasourceUsername(String datasourceUsername) {
        this.datasourceUsername = datasourceUsername;
    }
}
