package cn.zeze.chatbot.api.domain.ai;

import java.io.IOException;

public interface IOpenAI {
    String doChatGPT(String questions)throws IOException;
}
