package cn.zeze.chatbot.api.domain.ai.service;

import cn.hutool.http.HttpStatus;
import cn.zeze.chatbot.api.domain.ai.IOpenAI;
import cn.zeze.chatbot.api.domain.ai.aggregates.AIAnswer;
import cn.zeze.chatbot.api.domain.ai.vo.Choices;
import cn.zeze.chatbot.api.domain.ai.vo.JsonRootBean;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class OpenAI implements IOpenAI {
    @Value("${chatbot-api.openAIKey}")
    private String openAIKey;

    @Override
    public String doChatGPT(String questions) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.aiproxy.io/v1/chat/completions");
        post.addHeader("Content-Type","application/json");
        post.addHeader("Authorization","Bearer "+openAIKey);

        String paramJson = "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": [\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": \""+questions+"\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"temperature\": 0.7,\n" +
                "  \"max_tokens\": 2048\n" +
                "}\n";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.HTTP_OK) {
            String res = EntityUtils.toString(response.getEntity(), "UTF-8");
            //String decodedRes = StringEscapeUtils.unescapeJava(res);
            JsonRootBean rootBean = JSON.parseObject(res, JsonRootBean.class);
            List<Choices> choices = rootBean.getChoices();
            StringBuilder answer = new StringBuilder();
            for (Choices choice : choices) {
                 answer.append(choice.getMessage().getContent());
            }
            return answer.toString();
        } else {
            throw new RuntimeException("errcode is:" + response.getStatusLine().getStatusCode());
        }
    }
}
