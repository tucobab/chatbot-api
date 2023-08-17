
/**
 * Copyright 2023 bejson.com
 */
package cn.zeze.chatbot.api.domain.openai.vo;
import java.util.List;

/**
 * Auto-generated: 2023-08-17 12:16:7
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private String model;
    private String object;
    private Usage usage;
    private String id;
    private long created;
    private List<Choices> choices;
    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }

    public void setObject(String object) {
        this.object = object;
    }
    public String getObject() {
        return object;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }
    public Usage getUsage() {
        return usage;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setCreated(long created) {
        this.created = created;
    }
    public long getCreated() {
        return created;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }
    public List<Choices> getChoices() {
        return choices;
    }

}
