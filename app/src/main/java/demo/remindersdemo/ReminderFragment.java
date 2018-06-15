package demo.remindersdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ReminderFragment extends Fragment {

    private static final String EXTRA_REMINDER_POS = "reminder_position";
    private Reminder reminder;

    public static ReminderFragment newInstance(int position){
        Bundle args = new Bundle();
        args.putInt(EXTRA_REMINDER_POS, position);

        ReminderFragment frag = new ReminderFragment();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        int position = getArguments().getInt(EXTRA_REMINDER_POS);
        reminder = RemindersHandler.get().getReminders().get(position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.reminder_detail_view, container, false);
        return v;
    }
}
