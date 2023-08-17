/**
 * Copyright 2023 bejson.com
 */
package cn.zeze.chatbot.api.domain.zsxq.vo;

import lombok.Data;

/**
 * Auto-generated: 2023-08-16 20:15:18
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Group {

    private long group_id;
    private String name;
    private String type;
    private String background_url;
    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }
    public long getGroup_id() {
        return group_id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setBackground_url(String background_url) {
        this.background_url = background_url;
    }
    public String getBackground_url() {
        return background_url;
    }

}
