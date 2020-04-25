package edu.fzu.anop.pojo;

import edu.fzu.anop.resource.MailTodoResource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * 提醒邮件信息
 *
 * @author ZYF
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemindEmailDetail implements Serializable {

    private Integer id;

    private String email;

    private String username;

    private List<MailTodoResource> todosToRemind;

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder(String.format("email: %s\tusername: %s\n", email, username));
        todosToRemind.forEach(resource ->
                ret.append(String.format("---title: %s\tendDate: %s\n", resource.getTitle(), resource.getEndDate())));
        return ret.toString();
    }
}
