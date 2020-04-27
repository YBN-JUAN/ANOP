package edu.fzu.anop.resource;

import lombok.Data;

import java.util.Date;

/**
 * 用于显示在提醒邮件正文的待办事项资源
 *
 * @author ZYF
 */
@Data
public class MailTodoResource {
    public String title;

    public Date endDate;
}
