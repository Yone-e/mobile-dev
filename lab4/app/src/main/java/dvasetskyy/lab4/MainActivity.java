package dvasetskyy.lab4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView trackListView;
    private ArrayList<String> tracks;

    final static String TRACK_SOURCE = "dvasetskyy.lab4.TRACK_SOURCE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //generate list
        ArrayList<TrackEntry> list = new ArrayList<TrackEntry>();
        list.add(new TrackEntry( "Fun. - We Are Young", R.raw.fun_audio, R.raw.fun_video));

        TrackListAdabpter adapter = new TrackListAdabpter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.trackList);
        lView.setAdapter(adapter);
    }

    private class TrackEntry {
        String name;
        int audioId;
        int videoId;

        TrackEntry(String name, int audioId, int videoId) {
            this.name = name;
            this.audioId = audioId;
            this.videoId = videoId;
        }
    }

    private class TrackListAdabpter extends BaseAdapter implements ListAdapter {
        private ArrayList<TrackEntry> list;
        private Context context;


        TrackListAdabpter(ArrayList<TrackEntry> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int pos) {
            return list.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.track_list_item, null);
            }

            final TrackEntry trackEntry = list.get(position);

            //Handle TextView and display string from your list
            TextView listItemText = (TextView)view.findViewById(R.id.track_name_string);
            listItemText.setText(trackEntry.name);

            //Handle buttons and add onClickListeners
            Button videoButton = (Button)view.findViewById(R.id.video_btn);
            Button audioButton = (Button)view.findViewById(R.id.audio_btn);

            videoButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, VideoActivity.class);
                    intent.putExtra(TRACK_SOURCE, trackEntry.videoId);
                    startActivity(intent);
                }
            });
            audioButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AudioActivity.class);
                    intent.putExtra(TRACK_SOURCE, trackEntry.audioId);
                    startActivity(intent);
                }
            });

            return view;
        }
    }
}
