package com.hospital.system.service;

import java.io.IOException;

public interface EmailService {
    public boolean sendEmail(String toEmail, String subject, String body) throws IOException;
    public boolean sendVerificationCode(String toEmail, String subject, String body) throws IOException;
}
