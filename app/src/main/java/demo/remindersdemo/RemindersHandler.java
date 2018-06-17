package demo.remindersdemo;

import java.util.ArrayList;
import java.util.List;

//Singleton class to store and handle the reminder list.
public class RemindersHandler {

    private List<Reminder> reminders;
    private static RemindersHandler handler;

    public static RemindersHandler get(){
        if(handler == null){
            handler = new RemindersHandler();
            return handler;
        }
        else
            return handler;
    }

    private RemindersHandler(){
        reminders = new ArrayList<>();
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public int findReminderByID(String id){
        for(int i = 0; i<reminders.size(); i++)
            if(reminders.get(i).getId().equals(id))
                return i;
        return -1;
    }
}
