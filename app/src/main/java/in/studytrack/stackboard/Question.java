package in.studytrack.stackboard;

/**
 * Created by Neeraj on 19/03/17.
 */
public class Question {
    private String is_answered;
    private String question_id;
    private String score;
    private String accepted_answer_id;
    private String link;
    private String title;
    private String vote;
    private String owner;
    private String id;
    public Question() {
    }

    public Question(String is_answered, String question_id, String score, String accepted_answer_id,String link, String title,String vote,String owner,String id) {
        this.is_answered = is_answered;
        this.question_id= question_id;
        this.score = score;
        this.accepted_answer_id=accepted_answer_id;
        this.link=link;
        this.title = title;
        this.vote=vote;
        this.owner = owner;
        this.id = id;
    }

    public String getIs_answered() {
        return is_answered;
    }
    public void setIs_answered(String is_answered) {
        this.is_answered = is_answered;
    }
    public String getQuestion_id() {
        return question_id;
    }
    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score= score;
    }
    public String getAccepted_answer_id() {
        return accepted_answer_id;
    }
    public void setAccepted_answer_id(String accepted_answer_id) {
        this.accepted_answer_id = accepted_answer_id;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getVote() {
        return vote;
    }
    public void setVote(String vote) {
        this.vote = vote;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
