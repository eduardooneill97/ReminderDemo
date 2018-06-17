package demo.remindersdemo;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ReminderListFragment.Callbacks{

    //Para obtener la data del databse
    //FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment listViewFragment = fm.findFragmentById(R.id.main_view);

        if(listViewFragment == null){
            listViewFragment = new ReminderListFragment();
            fm.beginTransaction().add(R.id.main_view, listViewFragment).commit();
        }

    }

    @Override
    public void onReminderSelected(Reminder reminder) {
        FragmentManager fm = getSupportFragmentManager();
        ReminderFragment rf = ReminderFragment.newInstance(reminder);

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_view, rf);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
