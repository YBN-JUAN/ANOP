package edu.fzu.anop.component;

import edu.fzu.anop.service.MailService;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;

/**
 * 定时任务组件
 * @author ZYF
 */
@Component
@Slf4j
public class ScheduleJob {

    @Resource(name = "mailServiceImpl")
    MailService mailService;
    /**
     * 每小时发送提醒邮件
     *
     * @throws TemplateException
     * @throws IOException
     * @throws MessagingException
     */
    @Async
    @Scheduled(cron = "0 0 * * * ?")
    public void remindTodo() throws TemplateException, IOException, MessagingException {
        log.info("-- Start sending remind mails --");
        mailService.sendRemindMails();
    }
}
