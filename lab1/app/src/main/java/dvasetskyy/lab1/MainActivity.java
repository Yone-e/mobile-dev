package dvasetskyy.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        final String[] days = getResources().getStringArray(
                R.array.days);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, days) {
            @Override
            public int getCount() {
                return super.getCount() - 1;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());
    }

    public void onOKAction(View view) {
        TextView text = findViewById(R.id.output);
        Spinner spinner = findViewById(R.id.spinner);
        if (spinner.getSelectedItemId() != spinner.getCount()) {
            text.setText(spinner.getSelectedItem().toString());
        }
    }

    public void onCancelAction(View view) {
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setSelection(spinner.getAdapter().getCount());

        TextView text = findViewById(R.id.output);
        text.setText("");
    }
}
