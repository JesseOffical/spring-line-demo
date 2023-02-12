package com.jesse.springlinedemo.line.service;

import com.jesse.springlinedemo.line.model.LineMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;

public interface LineMessageService {
    LineMessage save(LineMessage message);

    LineMessage saveFromTextMessageEvent(MessageEvent<TextMessageContent> event);

    TextMessage replyMessage(String originalMessageText);
}
