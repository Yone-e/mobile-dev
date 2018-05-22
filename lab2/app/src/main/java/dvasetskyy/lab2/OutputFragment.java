package dvasetskyy.lab2;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

public class OutputFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.output_fragment, container, false);
        return view;

    }

    public void setMsg(String msg) {
        TextView view = (TextView) getView().findViewById(R.id.output);
        view.setText(msg);
    }

}
