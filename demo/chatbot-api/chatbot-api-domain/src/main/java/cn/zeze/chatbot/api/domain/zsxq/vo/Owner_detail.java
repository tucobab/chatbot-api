/**
 * Copyright 2023 bejson.com
 */
package cn.zeze.chatbot.api.domain.zsxq.vo;
import lombok.Data;

import java.util.Date;

/**
 * Auto-generated: 2023-08-16 20:15:18
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Owner_detail {

    private int questions_count;
    private Date join_time;
    public void setQuestions_count(int questions_count) {
        this.questions_count = questions_count;
    }
    public int getQuestions_count() {
        return questions_count;
    }

    public void setJoin_time(Date join_time) {
        this.join_time = join_time;
    }
    public Date getJoin_time() {
        return join_time;
    }

}