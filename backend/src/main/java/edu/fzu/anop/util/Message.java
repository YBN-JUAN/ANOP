package edu.fzu.anop.util;

import org.springframework.http.HttpStatus;

/**
 * 消息载体
 *
 * @param <T> 数据负载类型
 * @author Xue_Feng
 */
public class Message<T> {

    int status;

    String message;

    T data;

    public Message() {
    }

    public Message(HttpStatus status) {
        this.status = status.value();
        this.message = status.getReasonPhrase();
    }

    public Message(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message == null ? status.getReasonPhrase() : message;
    }

    public Message(HttpStatus status, String message, T data) {
        this(status, message);
        this.data = data;
    }

    public static <T> Message<T> custom(HttpStatus status, String message, T data) {
        return new Message(status, message, data);
    }

    public static <T> Message<T> custom(HttpStatus status, String message) {
        return new Message(status, message);
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
