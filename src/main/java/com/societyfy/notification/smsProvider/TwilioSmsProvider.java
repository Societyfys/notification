package com.societyfy.notification.smsProvider;

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
    public void sent(String toPhoneNumber,String fromNumber,String message) {
        Twilio.init(provider.getUsername(),provider.getPassword());
        Message.creator(new PhoneNumber(toPhoneNumber),new PhoneNumber(fromNumber), message)
                .create();
    }

    @Override
    public void sent(List<String> toPhoneNumbers,String fromNumber,String message) {

    }
}
