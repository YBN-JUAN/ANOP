package edu.fzu.anop.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsonResult<T> extends ResponseEntity<Message> {

    public JsonResult(HttpStatus status) {
        super(status);
    }

    public JsonResult(HttpStatus code, String msg, T data) {
        super(Message.custom(code, msg, data), code);
    }

    public JsonResult(HttpStatus code, String msg) {
        super(Message.custom(code, msg), code);
    }

    public static <T> JsonResult<T> ok(String message, T data) {
        return new JsonResult(HttpStatus.OK, message, data);
    }

    public static <T> JsonResult<T> badRequest(String message, T data) {
        return new JsonResult(HttpStatus.BAD_REQUEST, message, data);
    }

    public static <T> JsonResult<T> notFound(String message, T data) {
        return new JsonResult(HttpStatus.NOT_FOUND, message, data);
    }

    public static <T> JsonResult<T> noContent(String message, T data) {
        return new JsonResult(HttpStatus.NO_CONTENT, message, data);
    }

    public static <T> JsonResult<T> created(String message, T data) {
        return new JsonResult(HttpStatus.CREATED, message, data);
    }

    public static <T> JsonResult<T> unprocessableEntity(String message, T data) {
        return new JsonResult<>(HttpStatus.UNPROCESSABLE_ENTITY, message, data);
    }

    public static <T> JsonResult<T> custom(HttpStatus code) {
        return new JsonResult(code);
    }

    public static <T> JsonResult<T> custom(HttpStatus code, String msg, T data) {
        return new JsonResult(code, msg, data);
    }
}

class Message<T> {

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
        this.status = status.value();
        this.message = message == null ? status.getReasonPhrase() : message;
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
