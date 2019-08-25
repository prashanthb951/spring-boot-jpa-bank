package com.bank.utill;

import org.springframework.http.HttpHeaders;

public class HeaderUtill {

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert("Bank" + "." + entityName + ".created", param);
    }

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-bankApp-alert", message);
        headers.add("X-bankApp-params", param);
        return headers;
    }
}
