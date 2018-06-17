package demo.remindersdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ReminderActivity extends AppCompatActivity{

    private static final String EXTRA_REMINDER_DETAIL_POS = "reminder_detail_pos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        FragmentManager fm = getSupportFragmentManager();
        Fragment reminderDetailFragment = fm.findFragmentById(R.id.reminder_fragment_container);

        if(reminderDetailFragment == null){
//            reminderDetailFragment = ReminderFragment.newInstance(getIntent().getIntExtra(EXTRA_REMINDER_DETAIL_POS,0));
//            fm.beginTransaction().add(R.id.reminder_fragment_container, reminderDetailFragment).commit();
        }
    }

    public static Intent newIntent(Context context, int position){
        Intent intent = new Intent(context, ReminderActivity.class);
        intent.putExtra(EXTRA_REMINDER_DETAIL_POS, position);
        return intent;
    }
}
