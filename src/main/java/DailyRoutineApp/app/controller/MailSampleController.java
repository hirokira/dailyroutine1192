package DailyRoutineApp.app.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import DailyRoutineApp.app.bean.ContactForm;


@RestController
public class MailSampleController {

    @Autowired
    private MailSender mailSender;

    @RequestMapping(value="/sendmail", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> sendmail(@RequestBody ContactForm contactform) {
        String body = "お名前: " + contactform.getName() + "\n" +
                "メールアドレス: " + contactform.getEmail() + "\n" +
                "メッセージ: \n" + contactform.getMessage();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(contactform.getEmail());
        msg.setTo("study.test011616@gmail.com"); // 適宜変更してください
        msg.setSubject("お問い合わせがありました");
        msg.setText("お問い合わせは下記の通りです。\n\n---------------------------\n" + body + "\n---------------------------");
        System.out.println(body);
        mailSender.send(msg);
        return Arrays.asList("OK");
    }



}
