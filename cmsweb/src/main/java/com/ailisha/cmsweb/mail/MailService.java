package com.ailisha.cmsweb.mail;

/**
 * Created by hspcadmin on 2017/12/4.
 */
public interface MailService {

    public void sendSimpleMail(String to, String subject, String content);

    public void sendTemplateMail(String to, String subject, String content);
}
