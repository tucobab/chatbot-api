package cn.zeze.chatbot.api.domain.zsxq.service;

import cn.hutool.http.HttpStatus;
import cn.zeze.chatbot.api.domain.zsxq.aggregates.queryUnanswerQuestionsAggregates;
import cn.zeze.chatbot.api.domain.zsxq.req.AnswerReq;
import cn.zeze.chatbot.api.domain.zsxq.req.Req_data;
import cn.zeze.chatbot.api.domain.zsxq.res.AnswerRes;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class ZsxqApi implements IZsxqApi {

    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    @Override
    public queryUnanswerQuestionsAggregates queryUnanswerQuestionTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/" + groupId + "/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie", cookie);

        get.addHeader("Content-Type", "application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);

        if (response.getStatusLine().getStatusCode() == HttpStatus.HTTP_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            String decodedRes = StringEscapeUtils.unescapeJava(jsonStr);
            return JSON.parseObject(decodedRes, queryUnanswerQuestionsAggregates.class);
        } else {
            throw new RuntimeException("errcode is:" + response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/" + topicId + "/answer");

        post.addHeader("cookie", cookie);

        post.addHeader("Content-Type", "application/json; charset=UTF-8");

        post.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0");

        /*String  JsonParam ="{\n" +
                "\t\"req_data\": {\n" +
                "\t\t\"image_ids\": [],\n" +
                "\t\t\"silenced\": false,\n" +
                "\t\t\"text\": \"让大家都看见\\n\"\n" +
                "\t}\n" +
                "}";*/
        AnswerReq answerReq = new AnswerReq(new Req_data(text, silenced));
        String paramJson = JSONObject.toJSONString(answerReq);

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.HTTP_OK) {
            String res = EntityUtils.toString(response.getEntity(), "UTF-8");
            String decodedRes = StringEscapeUtils.unescapeJava(res);
            logger.info("回答星球问题结果：groupID：{}topicID：{}", groupId, topicId, decodedRes);
            AnswerRes answerRes = JSON.parseObject(decodedRes, AnswerRes.class);
            return answerRes.isSucceeded();
        } else {
            throw new RuntimeException("errcode is:" + response.getStatusLine().getStatusCode());
        }
    }
}
