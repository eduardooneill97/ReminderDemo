package demo.remindersdemo;

public class Task{

    private String id;
    private Boolean IsDone;
    private String Name;

    public Task(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsDone() {
        return IsDone;
    }

    public void setIsDone(Boolean done) {
        IsDone = done;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
