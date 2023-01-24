package com.ashu.octopus.service;

import com.ashu.octopus.models.notification.PushNotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PushNotificationService {

    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);
    private FCMService fcmService;
    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }
    public void sendPushNotification(PushNotificationRequest request) {
        try {
            fcmService.sendMessage(getSamplePayloadData(), request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    public void sendPushNotificationWithoutData(PushNotificationRequest request) {
        try {
            fcmService.sendMessageWithoutData(request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    public void sendPushNotificationToToken(PushNotificationRequest request) {
        try {
            fcmService.sendMessageToToken(request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    private Map<String, String> getSamplePayloadData() {
        Map<String, String> pushData = new HashMap<>();
        pushData.put("messageId", "msgid");
        pushData.put("text", "txt");
        pushData.put("user", "pankaj singh");
        return pushData;
    }
}
