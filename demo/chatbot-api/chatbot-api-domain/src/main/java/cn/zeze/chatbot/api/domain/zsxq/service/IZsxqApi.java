package cn.zeze.chatbot.api.domain.zsxq.service;

import cn.zeze.chatbot.api.domain.zsxq.aggregates.queryUnanswerQuestionsAggregates;

import java.io.IOException;
public interface IZsxqApi {
    queryUnanswerQuestionsAggregates queryUnanswerQuestionTopicId(String groupId, String cookie)throws IOException;

    boolean answer(String groupId,String cookie,String topicId,String text,boolean silenced)throws IOException;
}
