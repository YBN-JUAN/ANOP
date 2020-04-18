package edu.fzu.anop.resource;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

public class NotificationUpdateResource implements Serializable {
    @Length(min = 0, max = 31)
    private String title;

    @Length(min = 0, max = 127)
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
