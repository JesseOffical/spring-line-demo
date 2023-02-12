package com.jesse.springlinedemo.line.eventhandler;

import com.jesse.springlinedemo.line.model.LineMessage;
import com.jesse.springlinedemo.line.repository.MessageRepository;
import com.jesse.springlinedemo.line.service.LineMessageService;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@LineMessageHandler
public class LineEventHandler {

    private final LineMessageService lineMessageService;


    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("event: " + event);
        lineMessageService.saveFromTextMessageEvent(event);

        return null;

    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        log.info("event1: {}",event);
    }
}
