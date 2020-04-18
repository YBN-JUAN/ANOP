package edu.fzu.anop.component;

import edu.fzu.anop.service.MailService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时任务组件
 */
@Component
public class ScheduleJob {

    @Resource(name = "mailServiceImpl")
    MailService mailService;

//    /**
//     * 每小时发送提醒邮件
//     *
//     * @throws TemplateException
//     * @throws IOException
//     * @throws MessagingException
//     */
//    @Scheduled(cron = "0 0 * * * ? *")
//    public void remindTodo() throws TemplateException, IOException, MessagingException {
//        mailService.SendRemindMails();
//    }
}
