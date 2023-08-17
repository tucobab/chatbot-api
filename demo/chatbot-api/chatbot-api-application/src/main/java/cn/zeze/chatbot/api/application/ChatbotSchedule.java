package cn.zeze.chatbot.api.application;
//问题任务

import cn.zeze.chatbot.api.domain.openai.IOpenAI;
import cn.zeze.chatbot.api.domain.zsxq.aggregates.queryUnanswerQuestionsAggregates;
import cn.zeze.chatbot.api.domain.zsxq.service.IZsxqApi;
import cn.zeze.chatbot.api.domain.zsxq.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

@EnableScheduling
@Configuration
public class ChatbotSchedule {

    private Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Resource
    private IZsxqApi ZsxqApi;
    @Resource
    private IOpenAI iOpenAI;

    @Scheduled(cron = "0/10 * * * * ? ")
    public void run() {

        try {

            if (new Random().nextBoolean()) {
                logger.info("随机打烊了！");
                return;
            }
            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour < 7 || hour > 22) {
                logger.info("打烊了,明天再来！");
                return;
            }

            //1. 检索问题
            queryUnanswerQuestionsAggregates unAnsweredQuestionsAggregates = ZsxqApi.queryUnanswerQuestionTopicId(groupId, cookie);
            logger.info("检索结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (topics == null || topics.isEmpty()) {
                logger.info("本次检索未查询到待回答问题");
                return;
            }
            //2.AI回答
            Topics topic = topics.get(0);
            String answer = iOpenAI.doChatGPT(topic.getQuestion().getText().trim());
            //3.问题回复
            boolean status = ZsxqApi.answer(groupId, cookie, topic.getTopic_id() + "", answer, false);
            logger.info("编号：{} 问题：{} 回答：{} 状态：{}", topic.getTopic_id() + "",topic.getQuestion(), answer, status);
        } catch (Exception e) {
            logger.info("自动回答问题异常！", e);
        }
    }
}
