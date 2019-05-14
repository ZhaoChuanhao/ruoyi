package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.ruoyi.*.mapper")
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("=========召唤神兽！！！========\n" +
                " 　　　┏┓　　　┏┓\n" +
                " 　　┏┛┻━━━┛┻┓\n" +
                " 　　┃　　　　　　　 ┃\n" +
                " 　　┃　　　━　　　 ┃\n" +
                "   ████━████ ┃\n" +
                " 　　┃　　　　　　　 ┃\n" +
                " 　　┃　　　┻　　　 ┃\n" +
                " 　　┃　　　　　　　 ┃\n" +
                " 　　┗━┓　　　┏━┛\n" +
                " 　　　　┃　　　┃    \n" +
                " 　　　　┃　　　┃\n" +
                " 　　　　┃　　　┗━━━┓\n" +
                " 　　　　┃　　　　　    ┣┓\n" +
                " 　　　　┃　　　　      ┏┛\n" +
                " 　　　　┗┓┓┏━┳┓┏┛\n" +
                " 　　　　　┃┫┫　┃┫┫\n" +
                " 　　　　　┗┻┛　┗┻┛\n" +
                "===============神兽召唤成功！！！=============== \n" +
                "(♥◠‿◠)ﾉﾞ  高校社团管理系统启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}