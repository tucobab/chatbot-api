/**
 * Copyright 2023 bejson.com
 */
package cn.zeze.chatbot.api.domain.zsxq.res;
import cn.zeze.chatbot.api.domain.zsxq.vo.Topics;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2023-08-16 20:15:18
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Resp_data {

    private List<Topics> topics;
    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }
    public List<Topics> getTopics() {
        return topics;
    }

}