package entidade;

public class Feedback {
    private int id;
    private String feedback, data, post;
    private Consumidor consumidor;
    private MeiodoFeedback meiodoFeedback;
    public Feedback(int id, String feedback, String data, String post, Consumidor consumidor,
            MeiodoFeedback meiodoFeedback) {
        this.id = id;
        this.feedback = feedback;
        this.data = data;
        this.post = post;
        this.consumidor = consumidor;
        this.meiodoFeedback = meiodoFeedback;
    }
    public Feedback(int id, String feedback, String data, Consumidor consumidor,
            MeiodoFeedback meiodoFeedback) {
        this.id = id;
        this.feedback = feedback;
        this.data = data;
        this.consumidor = consumidor;
        this.meiodoFeedback = meiodoFeedback;
    }
    
    public Feedback(String feedback) {
		super();
		this.feedback = feedback;
	}
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getPost() {
        return post;
    }
    public void setPost(String post) {
        this.post = post;
    }
    public Consumidor getConsumidor() {
        return consumidor;
    }
    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }
    public MeiodoFeedback getMeiodoFeedback() {
        return meiodoFeedback;
    }
    public void setMeiodoFeedback(MeiodoFeedback meiodoFeedback) {
        this.meiodoFeedback = meiodoFeedback;
    }
    @Override
    public String toString() {
        return "Feedback: " + feedback + "\nData: " + data + "\nPost: " + post
                + consumidor + "\nPlataforma: " + meiodoFeedback;
    }

}
