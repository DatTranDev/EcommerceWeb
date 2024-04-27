package com.EcommerceWeb.utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.security.MessageDigest;
import java.util.Properties;

public class Helper {

    private static Helper helper = null;
    public static Helper getInstance() {
        if (helper == null) {
            helper = new Helper();
        }
        return helper;
    }
    public static String toMd5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String randomCode() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    public static String sendEmail(String email) {
        String code = randomCode();
        String subject = "Your verification code";
        String mess = "Your verification code is: " + code;

        String senderMail = "appservice.uit.se@gmail.com";
        String senderPassword = "izhicmdclgomsyiu";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderMail, senderPassword);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderMail));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(mess);

            Transport.send(message);
            System.out.println("Email sent successfully to " + email);
        } catch (MessagingException e) {
            System.out.println(e.toString());
            e.printStackTrace();
            // Handle exception
        }

        return code;
    }
    public static boolean checkEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regex);
    }
    public static boolean checkPhone(String phone) {
        String regex = "^0\\d{9}$";
        return phone.matches(regex);
    }
}
