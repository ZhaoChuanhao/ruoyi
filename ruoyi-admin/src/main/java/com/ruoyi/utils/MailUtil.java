package com.ruoyi.utils;

import com.ruoyi.bus.domain.Mail;
import com.ruoyi.system.domain.SysUser;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Description TODO
 * @Author 赵传昊
 * @Date 2019/5/11 11:44
 * @Version 1.0
 */
public class MailUtil {

    /**
     * 发送邮件
     * @param mail
     * @throws Exception
     */
    public static void sendMail(Mail mail) throws Exception{
        // 设置邮件服务器属性
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(prop);
        session.setDebug(true);
        // 通过session得到transport对象
        Transport ts = session.getTransport();
        ts.connect("smtp.qq.com", Mail.sysSend, Mail.sysPassword);
        Message message = createTextMail(session, mail);
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    /**
     * 创建只含文本信息的邮件对象
     * @param session
     * @param mail
     * @return
     * @throws Exception
     */
    public static MimeMessage createTextMail(Session session, Mail mail) throws Exception{
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(mail.getSend()));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getReceive()));
        message.setSubject(mail.getTheme());
        message.setContent(mail.getContent(), "text/html;charset=UTF-8");
        return message;
    }
}
