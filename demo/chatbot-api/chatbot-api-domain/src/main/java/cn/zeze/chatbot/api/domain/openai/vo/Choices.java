package cn.zeze.chatbot.api.domain.openai.vo;

import lombok.Data;

@Data
public class Choices {
    private int index;
    private String finish_reason;
    private Message message;

    // Getters and setters...
}
