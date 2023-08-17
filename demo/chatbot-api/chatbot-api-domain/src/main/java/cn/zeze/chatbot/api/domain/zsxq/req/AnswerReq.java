package cn.zeze.chatbot.api.domain.zsxq.req;

public class AnswerReq {

    private Req_data req_data;

    public AnswerReq(Req_data req_data) {
        this.req_data = req_data;
    }

    public Req_data getReq_data() {
        return req_data;
    }

    public void setReq_data(Req_data req_data) {
        this.req_data = req_data;
    }

}
