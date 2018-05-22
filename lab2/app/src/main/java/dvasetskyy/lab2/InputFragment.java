package dvasetskyy.lab2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class InputFragment extends Fragment  {

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_fragment, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        final String[] days = getResources().getStringArray(
                R.array.days);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, days) {
            @Override
            public int getCount() {
                return super.getCount() - 1;
            }
        };

        Button okButton = view.findViewById(R.id.button_ok);
        Button koButton = view.findViewById(R.id.button_ko);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOKAction();
            }
        });
        koButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelAction();
            }
        });


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());

        return view;
    }

    public void onOKAction() {
        Spinner spinner = getView().findViewById(R.id.spinner);
        if (spinner.getSelectedItemId() != spinner.getCount()) {
            mListener.onFragmentInteraction(spinner.getSelectedItem().toString());
        }
    }

    public void onCancelAction() {
        Spinner spinner = getView().findViewById(R.id.spinner);
        spinner.setSelection(spinner.getAdapter().getCount());

        mListener.onFragmentInteraction("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

    interface OnFragmentInteractionListener {
        void onFragmentInteraction(String msg);
    }
}
