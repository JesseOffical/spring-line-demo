package com.jesse.springlinedemo.line.controller;

import com.jesse.springlinedemo.line.service.LineMessageService;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
@Slf4j
public class LineMessageController {

    private final LineMessageService lineMessageService;
    private final LineMessagingClient lineMessagingClient;

    @PostMapping(value = "/messages/reply",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> replyMessageToCustomer(@RequestBody @Valid ReplyLineMessageVo body){
        log.info("ReplyLineMessageVo: {}",body);

        String replyToken = body.getReplyToken();
        List<Message> messages = Arrays.stream(body.getMessages()).map(TextMessage::new).collect(Collectors.toList());
        boolean notificationDisabled = body.getNotificationDisabled();

        BotApiResponse apiResponse = null;
        try {
            apiResponse = lineMessagingClient
                    .replyMessage(new ReplyMessage(replyToken, messages, notificationDisabled))
                    .get();
        } catch (InterruptedException e) {
            log.error("InterruptedException ",e);
            return ResponseEntity.internalServerError().body("");
        } catch (ExecutionException e) {
            log.error("ExecutionException ",e);
            return ResponseEntity.internalServerError().body("");
        }
        log.info("Sent messages: {}", apiResponse);
        return ResponseEntity.ok("");

    }

    @Data
    static public class ReplyLineMessageVo {
        @NotEmpty(message = "replyToken is empty.")
        private String replyToken;
        @NotEmpty(message = "messages is empty.")
        private String[] messages;
        @NotNull(message = "notificationDisabled is empty.")
        private Boolean notificationDisabled;

    }

//    @GetMapping("/users/{userId}/messages")
//    public List<LineMessage> getMessage(@PathVariable String userId){
//        return lineMessageService.getListByUserId(userId);
//    }

}
