package cn.zeze.chatbot.api;

import cn.zeze.chatbot.api.domain.zsxq.service.IZsxqApi;
import cn.zeze.chatbot.api.domain.zsxq.aggregates.queryUnanswerQuestionsAggregates;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi ZsxqApi;

    @Test
    public void test_zsxqApi() throws IOException {
        queryUnanswerQuestionsAggregates unAnsweredQuestionsAggregates = ZsxqApi.queryUnanswerQuestionTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
        }
}
