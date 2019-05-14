package com.ruoyi.bus.domain;

/**
 * @Description TODO
 * @Author 赵传昊
 * @Date 2019/5/11 11:56
 * @Version 1.0
 */
public class Mail {

    private static final long serialVersionUID = 1L;

    /**
     * 系统发件邮箱
     */
    public static String sysSend = "724356121@qq.com";

    /**
     * 系统发件邮箱授权码
     */
    public static String sysPassword = "zoihlnmyfhmebfji";

    /**
     * 发送人
     */
    private String send;
    /**
     * 接收人
     */
    private String receive;
    /**
     * 邮件主题
     */
    private String theme;
    /**
     * 邮件内容
     */
    private String content;

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
