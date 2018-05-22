package dvasetskyy.lab3;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputFragment extends Fragment  {

    private OnFragmentInteractionListener mListener;

    public static final String EXTRA_MESSAGE = "dvasetskyy.lab3.MESSAGE";
    final static String FILE_NAME = "content.txt";

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
        Button ofButton = view.findViewById(R.id.button_open);
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
        ofButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOpenFileAction();
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
            saveToFile(spinner.getSelectedItem().toString());
        } else {
            Toast.makeText(getActivity(), "NOTHING SELECTED", Toast.LENGTH_SHORT).show();
        }
    }

    public void onCancelAction() {
        Spinner spinner = getView().findViewById(R.id.spinner);
        spinner.setSelection(spinner.getAdapter().getCount());

        mListener.onFragmentInteraction("");
    }

    public void onOpenFileAction() {
        FileInputStream fin = null;
        String fileContent = null;

        try {
            fin = getActivity().openFileInput(FILE_NAME);
            if (fin.available() > 0) {
                byte[] bytes = new byte[fin.available()];
                fin.read(bytes);
                fileContent = new String(bytes);
            } else {
                Toast.makeText(getActivity(), "FILE IS EMPTY", Toast.LENGTH_SHORT).show();
            }
        }
        catch(IOException ex) {
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){
                Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        if (fileContent != null) {
            Intent intent = new Intent(getActivity(), FileContentActivity.class);
            intent.putExtra(EXTRA_MESSAGE, fileContent);
            startActivity(intent);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " has to implement OnFragmentInteractionListener interface");
        }
    }

    private void saveToFile(String text) {
        FileOutputStream fos = null;
        try {
            fos = getActivity().openFileOutput(FILE_NAME, Context.MODE_APPEND);
            fos.write(text.getBytes());
            fos.write("\n".getBytes());
            Toast.makeText(getActivity(), "WRITTEN DATA TO FILE", Toast.LENGTH_SHORT).show();
        } catch(IOException ex) {
            Toast.makeText(getActivity(), ex.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                if(fos != null)
                    fos.close();
            }
            catch(IOException ex){
                Toast.makeText(getActivity(), ex.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    interface OnFragmentInteractionListener {
        void onFragmentInteraction(String msg);
    }
}
