package com.study.bean;

public class ResultMap<T> {

    private int status;
    private String message;
    private int total;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultMap{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", total=" + total +
                ", data=" + data +
                '}';
    }

    public ResultMap(){

    }

    public ResultMap(int status, String message, int total, T data){
        this.status = status;
        this.message = message;
        this.total = total;
        this.data = data;
    }

}
