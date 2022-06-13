package com.group.hassocial.service;

import com.group.hassocial.security.config.EmailConfig;
import com.group.hassocial.service.interfaces.IEmailSender;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderService implements IEmailSender {

    @Value("${spring.mail.username}")
    private String from;

    //private final EmailConfig emailConfig;
    private final static Logger logger = LoggerFactory.getLogger(IEmailSender.class);
    private final JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {
        //this.emailConfig = emailConfig;
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Async
    public void sendEmail(String to, String msg) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(msg, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom(from);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            logger.error("Failed to send email for: " + to + "\n" + ex);
            throw new IllegalArgumentException("Failed to send email for: " + to);
        }
    }

    public String buildEmail(String name, String link) {
        return "<div style=\"font-family:Cereal,Helvetica,sans-serif;;font-size:15px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td style=\"border-radius: 10px;\"width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"border-radius: 10px;\" bgcolor=\"#82D8A2\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style = \"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;border: 1px solid #F56969 ;border-radius: 10px;background-color: #F56969;width: 122px;height: 38px;\"class=\"boxed\"><div style=\"align-items: center;font-size: 16px;font-weight: bold;\"><a style=\"margin-top:6px;font-size:16px;font-weight:bold;color:aliceblue;text-decoration: none;\n\" href=\"" + link + "\">Activate Now</a></div></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "<div>\n" +
                "   <table width=\"100%\" height=\"100%\" align=\"center\" valign=\"center\">\n" +
                "   <tr><td>\n" +
                "      <img style=\"width:100px;height:21px\" src=\"https://s3-alpha-sig.figma.com/img/332d/ca9f/a8a27b401dbde82d81402e0025d08084?Expires=1655683200&Signature=DMAlgRLaoK0LT0vn-HXxFeLQziHViEkfH5hz0cV3kktFIRt5O4ub~PPeAYexWmrTdlv-jn~L1mqriY1-uTOzi7~fR3NYMhFu6SuKdJjLzk~UkTJ6UBY9DPseJKSXe9b5qoNBQr22-h1~h5HrxqCH1x5hwJq4BQ6M3hsnpZ902Uf6q8cnwZ-lE6J-s-9mZ8S6DhbEDOO4LEFHBPCkYZg4c--1ONsASOJidTjVS8GPnDn5PPlf88-ub1sDnxFKJkf8t7uporbMAKbYjd1iDq5ewOotF6LSsKsNHklLw0qNPSQKDRegDGhoDIfMs3Z8tMLqyBlJfHMrSh8YYl58g1OcWw__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA\" alt=\"foo\" />\n" +
                "   </td></tr>\n" +
                "   </table>\n" +
                "</div>" +
                "</div></div>";
    }

}
