package dvasetskyy.lab2;

import android.support.v7.app.AppCompatActivity ;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity  implements InputFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(String message) {
        OutputFragment fragment = (OutputFragment) getSupportFragmentManager().findFragmentById(R.id.output_fragment);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setMsg(message);
        }
    }
}
