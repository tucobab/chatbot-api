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
public class User_specific {

    private boolean liked;
    private boolean subscribed;
    public void setLiked(boolean liked) {
        this.liked = liked;
    }
    public boolean getLiked() {
        return liked;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
    public boolean getSubscribed() {
        return subscribed;
    }

}