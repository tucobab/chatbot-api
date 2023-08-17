package cn.zeze.chatbot.api.domain.openai.aggregates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIAnswer {
    private String content;
}
