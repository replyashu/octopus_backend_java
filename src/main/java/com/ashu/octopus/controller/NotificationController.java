package com.ashu.octopus.controller;

import com.ashu.octopus.data.Note;
import com.ashu.octopus.service.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {

    @Autowired
    FirebaseMessagingService firebaseService;

    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Note note,
                                   @RequestParam String token) throws FirebaseMessagingException {
        System.out.println("issue is here");
        return firebaseService.sendNotification(note, token);
    }
}
