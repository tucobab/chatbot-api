package cn.zeze.chatbot.api.domain.openai.vo;

import lombok.Data;

@Data
public class Message {
    private String role;
    private String content;

    // Getters and setters...
}
