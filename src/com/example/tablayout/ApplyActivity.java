package com.example.tablayout;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.example.adapter.ExpandableAdapter;
import com.example.model.ApplySpeech;

public class ApplyActivity extends Activity {
	/** Called when the activity is first created. */
	private ExpandableListView expandableListView;

	private Context context;

	private Button applyBT;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.apply_layout);
		context = this;
		init();
	}

	private void init() {

		List<List<ApplySpeech>> allList = new ArrayList<List<ApplySpeech>>();
		for (int i = 0; i < 5; i++) {
			List<ApplySpeech> list = new ArrayList<ApplySpeech>();

			for (int j = 0; j < 5; j++) {
				list.add(new ApplySpeech(i + j, "speech" + j, null,
						"hello world ", "2014-06-" + i + j, "" + i));
			}
			allList.add(list);
		}
		applyBT = (Button) findViewById(R.id.applyButton);
		applyBT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(ApplyActivity.this,
						ApplyNewSpeech.class);
				Bundle bundle = new Bundle();
				startActivity(intent);
			}

		});

		expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
		ExpandableAdapter expandableAdapter = new ExpandableAdapter(context,
				allList);
		expandableListView.setAdapter(expandableAdapter);
		expandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView expandableListView,
					View view, int parentPosition, int childPosition, long arg4) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ApplyActivity.this,
						DetailedApplyActivity.class);
				Bundle bundle = new Bundle();
				startActivity(intent);
				return false;
			}

		});
	}
}