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
        // 使用JavaMail发送邮件的5个步骤
        // 1、创建Session
        Session session = Session.getInstance(prop);
        // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        // 2、通过session得到transport对象
        Transport ts = session.getTransport();
        // 3、使用邮箱的用户名和密码连上邮件服务器
        ts.connect("smtp.qq.com", Mail.sysSend, Mail.sysPassword);
        // 4、创建邮件对象
        Message message = createTextMail(session, mail);
        // 5、发送邮件
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
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress(mail.getSend()));
        // 指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getReceive()));
        // 邮件的表题
        message.setSubject(mail.getTheme());
        // 邮件的文本内容
        message.setContent(mail.getContent(), "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }
}
