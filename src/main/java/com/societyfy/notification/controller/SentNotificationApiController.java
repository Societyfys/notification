package com.societyfy.notification.controller;

import com.societyfy.notification.exception.SocietyfyException;
import com.societyfy.notification.model.SentNotificationRequest;
import com.societyfy.notification.service.SentNotificationService;
import com.societyfy.notification.utils.HeaderMapper;
import com.societyfy.notification.utils.NotificationConstant;
import com.societyfy.notification.utils.NotificationExceptionConstants;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class SentNotificationApiController implements SentNotificationApi {
    private final SentNotificationService sentNotificationService;
    private final Environment env;
    private static final Logger log = LoggerFactory.getLogger(SentNotificationApiController.class);

    @Override
    public ResponseEntity<Void> sentNotification(String accept, String correlationId, SentNotificationRequest body) {
        if (accept == null || !accept.contains(NotificationConstant.APPLICATION_JSON))
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

        HttpHeaders responseHeaders = HeaderMapper.getHttpHeaders(correlationId);
        try {
            sentNotificationService.sentNotification(correlationId, body);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SocietyfyException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error while sent notifications : {}", ExceptionUtils.getStackTrace(ex));
            throw new SocietyfyException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    NotificationExceptionConstants.INTERNAL_SEVER_ERROR,
                    env.getRequiredProperty(NotificationExceptionConstants.INTERNAL_SEVER_ERROR),
                    correlationId);
        }
    }
}
