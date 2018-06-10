package demo.remindersdemo;

import java.util.Map;

public class Reminder {

    private String id;
    private String Name;
    private String Description;
    private String UnivClass;
    private Integer Blue;
    private Long Date;
    private Integer Green;
    private Integer Red;
    private Map<String, Task> Tasks;
    private Map<String, Alert> Alerts;

    public Reminder(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBlue() {
        return Blue;
    }

    public void setBlue(Integer blue) {
        Blue = blue;
    }

    public Long getDate() {
        return Date;
    }

    public void setDate(Long date) {
        Date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getGreen() {
        return Green;
    }

    public void setGreen(Integer green) {
        Green = green;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getRed() {
        return Red;
    }

    public void setRed(Integer red) {
        Red = red;
    }

    public Map<String, Task> getTasks() {
        return Tasks;
    }

    public void setTasks(Map<String, Task> tasks) {
        Tasks = tasks;
    }

    public Map<String, Alert> getAlerts() {
        return Alerts;
    }

    public void setAlerts(Map<String, Alert> alerts) {
        Alerts = alerts;
    }

    public void setTaskAndAlertsIDs(){
        setAlertIDs();
        setTaskIDs();
    }

    public void setTaskIDs(){
        if(getTasks() != null)
            for(String keys: getTasks().keySet())
                getTasks().get(keys).setId(keys);
    }

    public void setAlertIDs(){
        if(getAlerts() != null)
            for(String keys: getAlerts().keySet())
                getAlerts().get(keys).setId(keys);
    }

    public String getUnivClass() {
        return UnivClass;
    }

    public void setUnivClass(String univClass) {
        UnivClass = univClass;
    }
}
