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

    @Value(("${signature.password}"))
    private String signaturePassword;

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
}
