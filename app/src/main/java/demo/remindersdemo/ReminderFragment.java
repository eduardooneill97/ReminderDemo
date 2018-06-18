package demo.remindersdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReminderFragment extends Fragment {

    private TextView nameView;
    private TextView descriptionView;
    private TextView dateView;
    private TextView classView;
    private RecyclerView alertsView;
    private RecyclerView tasksView;
    private Reminder reminder;

    public static ReminderFragment newInstance(Reminder reminder){
        ReminderFragment frag = new ReminderFragment();
        frag.setReminder(reminder);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.frgament_reminder_detail_view, container, false);

        ConstraintLayout cl = v.findViewById(R.id.header_layout);
        int color = Color.rgb(reminder.getRed(), reminder.getGreen(), reminder.getBlue());
        cl.setBackgroundColor(color);

        nameView = v.findViewById(R.id.reminder_name);
        dateView = v.findViewById(R.id.reminder_date);
        descriptionView = v.findViewById(R.id.reminder_description);
        classView = v.findViewById(R.id.reminder_class);
        alertsView = v.findViewById(R.id.alerts_recycler_view);
        tasksView = v.findViewById(R.id.tasks_recycler_view);

        alertsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(reminder.getAlerts() != null)
            alertsView.setAdapter(new AlertsAdapter(toAlertList(reminder.getAlerts())));

        tasksView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(reminder.getTasks() != null)
            tasksView.setAdapter(new TasksAdapter(toTaskList(reminder.getTasks())));

        nameView.setText(reminder.getName());
        descriptionView.setText(reminder.getDescription());
        dateView.setText((new Date(reminder.getDate())).toString());
        classView.setText(reminder.getUnivClass());

        return v;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    private class AlertHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private Alert alert;

        public AlertHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.alerts_list_item, parent, false));

            name = itemView.findViewById(R.id.alert_item_name);
        }

        public void bind(Alert alert){
            this.alert = alert;
            name.setText(alert.getName());
        }
    }

    private class AlertsAdapter extends RecyclerView.Adapter<AlertHolder>{

        private List<Alert> alerts;

        public AlertsAdapter(List<Alert> alertList){
            alerts = alertList;
        }

        @Override
        public AlertHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new AlertHolder(LayoutInflater.from(getActivity()), parent);
        }

        @Override
        public void onBindViewHolder(AlertHolder holder, int position) {
            Alert a = alerts.get(position);
            holder.bind(a);
        }

        @Override
        public int getItemCount() {
            return alerts.size();
        }
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CheckBox isDoneCheckBox;
        private Task task;

        public TaskHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.tasks_list_item, parent, false));

            isDoneCheckBox = itemView.findViewById(R.id.task_checkbox);
            isDoneCheckBox.setClickable(false);
            itemView.setOnClickListener(this);
        }

        public void bind(Task task){
            this.task = task;
            isDoneCheckBox.setText(task.getName());
            isDoneCheckBox.setChecked(task.getIsDone());
        }

        @Override
        public void onClick(View view) {
            task.setIsDone(!task.getIsDone());
            isDoneCheckBox.setChecked(task.getIsDone());
        }
    }

    private class TasksAdapter extends RecyclerView.Adapter<TaskHolder>{

        private List<Task> tasks;

        public TasksAdapter(List<Task> tasks){
            this.tasks = tasks;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TaskHolder(LayoutInflater.from(getActivity()), parent);
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, int position) {
            Task t = tasks.get(position);
            holder.bind(t);
        }

        @Override
        public int getItemCount() {
            return tasks.size();
        }
    }

    private static ArrayList<Alert> toAlertList(Map<String, Alert> alerts){
        ArrayList<Alert> list = new ArrayList<>();
        for(Alert a: alerts.values())
            list.add(a);
        return list;
    }

    private static ArrayList<Task> toTaskList(Map<String, Task> tasks){
        ArrayList<Task> list = new ArrayList<>();
        for(Task t: tasks.values())
            list.add(t);
        return list;
    }
}
