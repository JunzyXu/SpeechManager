package com.example.tablayout;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.adapter.ListViewAdapter;
import com.example.model.Speech;

public class GradeActivity extends Activity {
	private ListView speechLV;
	private Context context;
	private List<Speech> speechList;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.grade_layout);
		init();

	}

	private void init() {
		context = this;
		speechList = new ArrayList<Speech>();

		for (int i = 0; i < 10; i++) {
			Speech speech = new Speech("speech_" + i, null, "hello world",
					80 + i);
			speechList.add(speech);
		}

		speechLV = (ListView) findViewById(R.id.gradeSpeechLV);

		ListViewAdapter lvAdapter = new ListViewAdapter(context, speechList);

		speechLV.setAdapter(lvAdapter);

		speechLV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GradeActivity.this,
						DetailedGradeActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("speech", speechList.get(position));
				intent.putExtras(bundle) ;
				startActivity(intent) ;
			}

		});

	}
}
