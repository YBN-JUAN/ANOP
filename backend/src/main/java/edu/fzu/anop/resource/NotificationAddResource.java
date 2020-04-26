package edu.fzu.anop.resource;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 通知添加资源
 *
 * @author Xue_Feng
 */
public class NotificationAddResource implements Serializable {
    @NotNull
    @Length(min = 0, max = 31)
    private String title;

    @NotNull
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
