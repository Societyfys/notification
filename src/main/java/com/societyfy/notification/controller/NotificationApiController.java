package com.societyfy.notification.controller;

import com.societyfy.notification.exception.SocietyfyException;
import com.societyfy.notification.model.CreateNotification;
import com.societyfy.notification.model.GetNotificationsResponse;
import com.societyfy.notification.model.NotificationModel;
import com.societyfy.notification.service.NotificationService;
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
public class NotificationApiController implements NotificationApi {
    private final NotificationService notificationService;
    private final Environment env;
    private static final Logger log = LoggerFactory.getLogger(NotificationApiController.class);

    @Override
    public ResponseEntity<GetNotificationsResponse> getAllNotifications(String accept, String correlationId) {
        if (accept == null || !accept.contains(NotificationConstant.APPLICATION_JSON))
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

        HttpHeaders responseHeaders = HeaderMapper.getHttpHeaders(correlationId);
        try {
            GetNotificationsResponse response = notificationService.getAllNotifications(correlationId);
            return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);
        } catch (SocietyfyException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error while get notifications : {}", ExceptionUtils.getStackTrace(ex));
            throw new SocietyfyException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    NotificationExceptionConstants.INTERNAL_SEVER_ERROR,
                    env.getRequiredProperty(NotificationExceptionConstants.INTERNAL_SEVER_ERROR),
                    correlationId);
        }
    }

    @Override
    public ResponseEntity<NotificationModel> getNotification(String accept, String correlationId, String notificationId) {
        if (accept == null || !accept.contains(NotificationConstant.APPLICATION_JSON))
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

        HttpHeaders responseHeaders = HeaderMapper.getHttpHeaders(correlationId);
        try {
            NotificationModel notification = notificationService.getNotification(notificationId, correlationId);
            return new ResponseEntity<>(notification, responseHeaders, HttpStatus.OK);
        } catch (SocietyfyException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error while get notification : {}", ExceptionUtils.getStackTrace(ex));
            throw new SocietyfyException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    NotificationExceptionConstants.INTERNAL_SEVER_ERROR,
                    env.getRequiredProperty(NotificationExceptionConstants.INTERNAL_SEVER_ERROR),
                    correlationId);
        }
    }

    @Override
    public ResponseEntity<NotificationModel> addNotification(String accept, String correlationId, CreateNotification body) {
        if (accept == null || !accept.contains(NotificationConstant.APPLICATION_JSON))
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

        HttpHeaders responseHeaders = HeaderMapper.getHttpHeaders(correlationId);
        try {
            NotificationModel notification = notificationService.addNotification(correlationId,body);
            return new ResponseEntity<>(notification, responseHeaders, HttpStatus.CREATED);
        } catch (SocietyfyException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error while create notification : {}", ExceptionUtils.getStackTrace(ex));
            throw new SocietyfyException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    NotificationExceptionConstants.INTERNAL_SEVER_ERROR,
                    env.getRequiredProperty(NotificationExceptionConstants.INTERNAL_SEVER_ERROR),
                    correlationId);
        }
    }

    @Override
    public ResponseEntity<NotificationModel> updateNotification(String accept, String correlationId, CreateNotification body) {
        if (accept == null || !accept.contains(NotificationConstant.APPLICATION_JSON))
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

        HttpHeaders responseHeaders = HeaderMapper.getHttpHeaders(correlationId);
        try {
            NotificationModel notification = notificationService.updateNotification(correlationId,body);
            return new ResponseEntity<>(notification, responseHeaders, HttpStatus.CREATED);
        } catch (SocietyfyException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error while update notification : {}", ExceptionUtils.getStackTrace(ex));
            throw new SocietyfyException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    NotificationExceptionConstants.INTERNAL_SEVER_ERROR,
                    env.getRequiredProperty(NotificationExceptionConstants.INTERNAL_SEVER_ERROR),
                    correlationId);
        }
    }
}
