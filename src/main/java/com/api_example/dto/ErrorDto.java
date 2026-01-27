package com.api_example.dto;

import java.util.Date;

public class ErrorDto {
    private Date date;
    private String  message;
    private String url;
    public ErrorDto(Date date,String  message , String url){
        this.date = date;
        this.message = message;
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }
}
