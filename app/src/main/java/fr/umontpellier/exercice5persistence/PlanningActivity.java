package fr.umontpellier.exercice5persistence;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlanningActivity extends AppCompatActivity {
    private DataViewModel model;
    private Button afficherPlanning;
    FileOutputStream fos;
    private static final String FILE_NAME = "planning";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        textView = (TextView)findViewById(R.id.text_view);

        model = new  ViewModelProvider(this).get(DataViewModel.class);

        // Create the observer which updates the UI.
        final Observer<List<String>> nameObserver = new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable final List<String> newPlannings) {
               // Update the UI, in this case, a TextView.
                StringBuilder tx = new StringBuilder();
                for (int i = 0; i < newPlannings.size(); i++ ){
                    tx.append(newPlannings.get(i) + "/n");
                }
                textView.setText(tx.toString());
            }
        };
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the
          model.getPanning().observe(this, nameObserver);

        afficherPlanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> newListe = new ArrayList<>();
                model.getPanning().setValue(newListe);
            }
        });
    }

    public void sauvegarder(String data){
        try {
            fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
            try {
                fos.write(data.getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}