package org.example;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailSender {

    public static void send(String to, String subject, String text) throws Exception {

        String host = EnvLoader.get("SMTP_HOST");
        String port = EnvLoader.get("SMTP_PORT");
        String user = EnvLoader.get("EMAIL_USER");
        String pass = EnvLoader.get("EMAIL_PASS");
        String fromName = EnvLoader.get("EMAIL_FROM_NAME");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pass);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user, fromName));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(text);

        Transport.send(message);
    }
}