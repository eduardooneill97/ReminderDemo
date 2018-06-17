package demo.remindersdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

public class ReminderFragment extends Fragment {

    //private static final String EXTRA_REMINDER_POS = "reminder_position";
    private TextView nameView;
    private TextView descriptionView;
    private TextView dateView;
    private TextView classView;
    private Reminder reminder;

    public static ReminderFragment newInstance(Reminder reminder){
        ReminderFragment frag = new ReminderFragment();
        frag.setReminder(reminder);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.reminder_detail_view, container, false);

        ConstraintLayout cl = v.findViewById(R.id.header_layout);
        int color = Color.rgb(reminder.getRed(), reminder.getGreen(), reminder.getBlue());
        cl.setBackgroundColor(color);

        nameView = v.findViewById(R.id.reminder_name);
        dateView = v.findViewById(R.id.reminder_date);
        descriptionView = v.findViewById(R.id.reminder_description);
        classView = v.findViewById(R.id.reminder_class);

        nameView.setText(reminder.getName());
        descriptionView.setText(reminder.getDescription());
        dateView.setText((new Date(reminder.getDate())).toString());
        classView.setText(reminder.getUnivClass());

        return v;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }
}
