package demo.remindersdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReminderListFragment extends Fragment {
    //TODO create class to handle all of the data for the fragment to retrive.
    private RecyclerView remindersView;
    private ReminderAdapter adapter;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private List<Reminder> remindersList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceBundle){
        View v = inflater.inflate(R.layout.fragment_reminders_list, parent, false);

        remindersView = v.findViewById(R.id.list_view);
        remindersView.setLayoutManager(new LinearLayoutManager(getActivity()));
        remindersList = new ArrayList<>();
        adapter = new ReminderAdapter(remindersList);
        remindersView.setAdapter(adapter);

        DatabaseReference ref = database.getReference("Reminders");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snap: dataSnapshot.getChildren()) {
                    Log.d("DATA_B", dataSnapshot.getKey() + "-->" + dataSnapshot.getValue().toString());
                    Reminder r = snap.getValue(Reminder.class);
                    r.setId(dataSnapshot.getKey());
                    r.setTaskAndAlertsIDs();
                    remindersList.add(r);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }

    private class ReminderViewHolder extends RecyclerView.ViewHolder{
        private TextView nameView;
        private TextView descriptionView;
        private TextView timeView;

        public ReminderViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.reminder_list_item, parent, false));

            nameView = itemView.findViewById(R.id.r_name);
            descriptionView = itemView.findViewById(R.id.r_description);
            timeView = itemView.findViewById(R.id.r_time);

        }

        public void bind(Reminder reminder){
            nameView.setText(reminder.getName());
            descriptionView.setText(reminder.getDescription());
            timeView.setText((new Date(reminder.getDate())).toString());
        }
    }

    private class ReminderAdapter extends RecyclerView.Adapter<ReminderViewHolder>{

        private List<Reminder> reminders;

        public ReminderAdapter(List<Reminder> reminders){
            this.reminders = reminders;
        }

        @NonNull
        @Override
        public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ReminderViewHolder(LayoutInflater.from(getActivity()), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
            Reminder r = reminders.get(position);
            holder.bind(r);
        }

        @Override
        public int getItemCount() {
            return reminders.size();
        }
    }
}
