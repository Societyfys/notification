package com.societyfy.notification.handler.pushNotificationProvider;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FcmPushNotificationProvider extends PushNotificationProvider {
    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;

    Logger logger = LoggerFactory.getLogger(FcmPushNotificationProvider.class);

    @PostConstruct
    public void firebaseInit() throws IOException {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath)
                            .getInputStream())).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("Firebase application initialized");
            }
        } catch (IOException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public void send(String clientToken, String title, String message) {
        try {
            Message pushMessage = Message.builder()
                    .setToken(clientToken)
                    .putData("body", message)
                    .build();

            FirebaseMessaging.getInstance().send(pushMessage);
        } catch (FirebaseMessagingException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void send(List<String> clientTokens, String title, String message) {
        MulticastMessage pushMessage = MulticastMessage.builder()
                .addAllTokens(clientTokens)
                .putData("body", message)
                .build();

        FirebaseMessaging.getInstance()
                .sendEachForMulticastAsync(pushMessage);
    }

    @Override
    public void sendByTopic(String topic, String title, String message) {
        try {
            Message pushMessage = Message.builder()
                    .setTopic(topic)
                    .putData("body", message)
                    .build();

            FirebaseMessaging.getInstance().send(pushMessage);
        } catch (FirebaseMessagingException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
