package cn.zeze.chatbot.api.domain.zsxq.aggregates;

import cn.zeze.chatbot.api.domain.zsxq.res.Resp_data;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//未回答的信息
public class queryUnanswerQuestionsAggregates {
    private boolean succeeded;
    private Resp_data resp_data;

}
