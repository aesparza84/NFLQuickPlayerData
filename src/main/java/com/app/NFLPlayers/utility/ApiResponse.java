package com.app.NFLPlayers.utility;

public class ApiResponse<T> {
    private String message;
    private String status;
    private T data;

    public ApiResponse(String status,String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    //Getters and Setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
