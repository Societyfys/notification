package com.societyfy.notification.handler.smsProvider;

import com.societyfy.notification.repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwilioSmsProvider extends SmsProvider{
    public TwilioSmsProvider(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public void send(String toPhoneNumber,String fromNumber,String message) {
        Twilio.init(provider.getUsername(),provider.getPassword());
        Message.creator(new PhoneNumber(toPhoneNumber),new PhoneNumber(fromNumber), message)
                .create();
    }

    @Override
    public void send(List<String> toPhoneNumbers, String fromNumber, String message) {
        throw new RuntimeException("Not integrate Bulk SMS");
    }
}
