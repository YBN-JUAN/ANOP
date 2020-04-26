package edu.fzu.anop.service.impl;

import edu.fzu.anop.mapper.RemindMailMapper;
import edu.fzu.anop.pojo.RemindEmailDetail;
import edu.fzu.anop.service.MailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 邮件服务
 *
 * @author ZYF
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Resource
    RemindMailMapper remindMailMapper;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Resource
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public void sendRemindMails() throws IOException, TemplateException, MessagingException {
        List<RemindEmailDetail> remindEmails = remindMailMapper.selectRemindMailDetails();
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("EmailTemplate.html");

        for (RemindEmailDetail remindEmail : remindEmails) {
            Map<String, Object> model = new HashMap<>(1);
            model.put("info", remindEmail);
            String templateHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            this.sendHtmlMail(remindEmail.getEmail(), "您有未完成的待办事项", templateHtml);
        }
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("ANOP 通之" + " <" + fromEmail + ">");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
        log.info("Success to send Email to：" + to);
    }


}
