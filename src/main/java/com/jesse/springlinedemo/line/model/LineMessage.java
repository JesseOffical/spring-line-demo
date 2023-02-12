package com.jesse.springlinedemo.line.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("LineMessages")
public class LineMessage {
    @Id
    private String id ;
    private String messageId ;
    private String userId;
    private String replyToken;
    private String type;
    private String text;
    private LocalDateTime timestamp;

}
