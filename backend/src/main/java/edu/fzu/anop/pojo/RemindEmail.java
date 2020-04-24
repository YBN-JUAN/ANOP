package edu.fzu.anop.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 提醒邮件信息
 * @author ZYF
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemindEmail implements Serializable {

    private String email;

    private String username;

    private String title;

    private Date remindDate;

    @Override
    public String toString() {
        return String.format("email: %s\tusername: %s\ttitle: %s\tremindDate:%s", email, username, title, remindDate);
    }
}
