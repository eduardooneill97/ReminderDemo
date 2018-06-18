package demo.remindersdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReminderListFragment extends Fragment {

    private RecyclerView remindersView;
    private ReminderAdapter adapter;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Callbacks callbacks;

    public interface Callbacks{
        void onReminderSelected(Reminder reminder);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        callbacks = (Callbacks) context;
    }

    @Override
    public void onDetach(){
        super.onDetach();
        callbacks = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        DatabaseReference ref = database.getReference("Reminders");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snap: dataSnapshot.getChildren()) {
                    Log.d("DATA_B", dataSnapshot.getKey() + "-->" + dataSnapshot.getValue().toString());
                    Reminder r = snap.getValue(Reminder.class);
                    r.setId(snap.getKey());
                    r.setTaskAndAlertsIDs();
                    int pos = RemindersHandler.get().findReminderByID(r.getId());
                    if(pos == -1)
                        RemindersHandler.get().getReminders().add(r);
                }
                updateUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceBundle){
        View v = inflater.inflate(R.layout.fragment_reminders_list, parent, false);

        remindersView = v.findViewById(R.id.list_view);
        remindersView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Reminder> remindersList = RemindersHandler.get().getReminders();
        adapter = new ReminderAdapter(remindersList);
        remindersView.setAdapter(adapter);

        return v;
    }

    public void updateUI(){
        adapter.notifyDataSetChanged();
    }

    private class ReminderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nameView;
        private TextView descriptionView;
        private TextView timeView;
        //private LinearLayout background;
        private Reminder reminder;

        public ReminderViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.reminder_list_item, parent, false));

            nameView = itemView.findViewById(R.id.r_name);
            descriptionView = itemView.findViewById(R.id.r_description);
            timeView = itemView.findViewById(R.id.r_time);
            //background = itemView.findViewById(R.id.background_layout);
            itemView.setOnClickListener(this);
        }

        public void bind(Reminder reminder){
            this.reminder = reminder;
            nameView.setText(reminder.getName());
            descriptionView.setText(reminder.getDescription());
            timeView.setText((new Date(reminder.getDate())).toString());
            //background.setBackgroundColor(Color.rgb(reminder.getRed(),reminder.getGreen(),reminder.getBlue()));
        }

        @Override
        public void onClick(View view) {
            callbacks.onReminderSelected(reminder);
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
