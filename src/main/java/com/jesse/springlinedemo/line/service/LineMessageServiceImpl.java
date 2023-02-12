package com.jesse.springlinedemo.line.service;

import com.jesse.springlinedemo.line.model.LineMessage;
import com.jesse.springlinedemo.line.repository.MessageRepository;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LineMessageServiceImpl implements LineMessageService {

    private final MessageRepository messageRepository;

    @Override
    public LineMessage save(LineMessage message){
        return messageRepository.save(message);
    }

    @Override
    public LineMessage saveFromTextMessageEvent(MessageEvent<TextMessageContent> event){
        LineMessage lineMessage = new LineMessage();
        lineMessage.setMessageId(event.getMessage().getId());
        lineMessage.setText(event.getMessage().getText());
        lineMessage.setReplyToken(event.getReplyToken());
        lineMessage.setType(lineMessage.getType());
        lineMessage.setUserId(event.getSource().getUserId());
        lineMessage.setTimestamp(LocalDateTime.ofInstant(event.getTimestamp(), ZoneOffset.UTC));

        return messageRepository.save(lineMessage);
    }

    @Override
    public TextMessage replyMessage(String originalMessageText) {
        return new TextMessage(originalMessageText);
    }

    public List<LineMessage> getListByUserId(String userId){
        return messageRepository.findAllByUserId(userId);
    }


}
