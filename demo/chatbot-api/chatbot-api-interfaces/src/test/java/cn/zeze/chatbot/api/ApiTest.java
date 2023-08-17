package cn.zeze.chatbot.api;

import cn.hutool.http.HttpStatus;
import cn.zeze.chatbot.api.domain.openai.IOpenAI;
import cn.zeze.chatbot.api.domain.zsxq.service.IZsxqApi;
import cn.zeze.chatbot.api.domain.zsxq.aggregates.queryUnanswerQuestionsAggregates;
import cn.zeze.chatbot.api.domain.zsxq.req.AnswerReq;
import cn.zeze.chatbot.api.domain.zsxq.req.Req_data;
import cn.zeze.chatbot.api.domain.zsxq.vo.Topics;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@SpringBootTest
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiApplication.class);
    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Autowired
    private IZsxqApi ZsxqApi;
    @Resource
    private IOpenAI iOpenAI;

    @Test
    public void test_zsxqApi() throws IOException {
        queryUnanswerQuestionsAggregates unAnsweredQuestionsAggregates = ZsxqApi.queryUnanswerQuestionTopicId(groupId, cookie);
/*
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
*/

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for(Topics topic : topics){
            long topicId=topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("topicId:{} text:{}",topicId,text);


            //回答问题
        }
    }
    @Test
    public void testchatgpt() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.aiproxy.io/v1/chat/completions");
        post.addHeader("Content-Type","application/json");
        post.addHeader("Authorization","Bearer sk-SBjsCWAmKOegVHtQBnZf5dp5mCBZDnYdfuvizHmWQt4eb71q");

        String paramJson = "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": [\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": \"你是谁\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"temperature\": 0.7,\n" +
                "  \"max_tokens\": 1024\n" +
                "}\n";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.HTTP_OK) {
            String res = EntityUtils.toString(response.getEntity(), "UTF-8");
            String decodedRes = StringEscapeUtils.unescapeJava(res);
            System.out.println(decodedRes);
        } else {
            throw new RuntimeException("errcode is:" + response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void opentest() throws IOException {
        String res = iOpenAI.doChatGPT("java冒泡排序");
        System.out.println(res);
    }

    @Test
    public void text() throws IOException {
        AnswerReq answerReq = new AnswerReq(new Req_data("111", false));
        String paramJson = JSONObject.toJSONString(answerReq);
        System.out.println(paramJson);
        System.out.println(groupId);
        System.out.println(cookie);
        System.out.println(ZsxqApi);
    }

}
