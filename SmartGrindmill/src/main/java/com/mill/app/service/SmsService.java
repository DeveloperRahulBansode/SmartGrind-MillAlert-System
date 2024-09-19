package com.mill.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;

@Service
public class SmsService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    // Method to initialize Twilio SDK
    @PostConstruct
    private void init() {
        if (accountSid == null || authToken == null || twilioPhoneNumber == null) {
            throw new IllegalArgumentException("Twilio credentials must not be null");
        }
        Twilio.init(accountSid, authToken);
    }

    // Method to send an SMS
    public void sendSms(String toPhoneNumber, String message) {
        try {
            // Validate the phone number format
            if (toPhoneNumber == null || toPhoneNumber.isEmpty()) {
                throw new IllegalArgumentException("The recipient phone number must be provided.");
            }
            if (message == null || message.isEmpty()) {
                throw new IllegalArgumentException("The message body must be provided.");
            }

            // Send the SMS using Twilio's API
            Message smsMessage = Message.creator(
                    new PhoneNumber(toPhoneNumber),   // Client's phone number ('To')
                    new PhoneNumber(twilioPhoneNumber),  // Your Twilio phone number ('From')
                    message)                         // The message content
                .create();

            // Log the successful SMS sending with the message SID
            System.out.println("SMS sent successfully. Message SID: " + smsMessage.getSid());
        } catch (Exception e) {
            // Catch and log any exception during SMS sending
            System.err.println("Failed to send SMS. Error: " + e.getMessage());
        }
    }
}
