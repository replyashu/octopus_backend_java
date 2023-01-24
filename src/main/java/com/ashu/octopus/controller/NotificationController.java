package com.ashu.octopus.controller;

import com.ashu.octopus.data.Note;
import com.ashu.octopus.data.NoteWithToken;
import com.ashu.octopus.models.notification.PushNotificationRequest;
import com.ashu.octopus.models.notification.PushNotificationResponse;
import com.ashu.octopus.service.FirebaseMessagingService;
import com.ashu.octopus.service.PushNotificationService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {

    @Autowired
    private FirebaseMessagingService firebaseService;
    private PushNotificationService pushNotificationService;
    public NotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }
    @PostMapping("/notification/topic")
    public ResponseEntity sendNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationWithoutData(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }
    @PostMapping("/notification/token")
    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationToToken(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }
    @PostMapping("/notification/data")
    public ResponseEntity sendDataNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotification(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody NoteWithToken note) throws FirebaseMessagingException {
        System.out.println("notewa" + note.toString());
        return firebaseService.sendNotification(note.getNote(), note.getToken());
    }
}
