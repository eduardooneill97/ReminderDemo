package demo.remindersdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Singleton class to store and handle the reminder list.
public class RemindersHandler {

    private List<Reminder> reminders;
    private Map<String, String> reminderIDs;
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
        reminderIDs = new HashMap<>();
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void addReminder(Reminder reminder){
        if(!reminderIDs.containsKey(reminder.getId())){
            reminders.add(reminder);
            reminderIDs.put(reminder.getId(), reminder.getId());
        }
    }

    public Reminder removeReminder(Reminder reminder){
        if(reminderIDs.containsKey(reminder.getId())){
            reminderIDs.remove(reminder.getId());

            int rtr = findReminderByID(reminder.getId());
            return reminders.remove(rtr);
        }
        return null;
    }

    private int findReminderByID(String id){
        for(int i = 0; i<reminders.size(); i++)
            if(reminders.get(i).getId().equals(id))
                return i;
        return -1;
    }
}
