package com.example.adapter;

import java.util.List;
import java.util.zip.Inflater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.Speech;
import com.example.tablayout.R;

public class ListViewAdapter extends BaseAdapter {

	private Context context;
	private List<Speech> speechList;
	private LayoutInflater inflater;

	public ListViewAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	public ListViewAdapter(Context context, List<Speech> speechList) {
		this.context = context;
		this.speechList = speechList;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return speechList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return speechList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		ListItem listItem = null;
		if (view == null) {
			listItem = new ListItem();
			view = inflater.inflate(R.layout.list_item, null);
			listItem.gradeTV = (TextView) view.findViewById(R.id.grade);
			listItem.groupLeaderTV = (TextView) view
					.findViewById(R.id.grouperLeader);
			listItem.groupLogo = (ImageView) view.findViewById(R.id.groupLogo);
			listItem.titleTV = (TextView) view.findViewById(R.id.title);
			view.setTag(listItem);

		} else {
			listItem = (ListItem) view.getTag();
		}

		
		listItem.titleTV.setText(speechList.get(position).getTitle());
		
		if (speechList.get(position).getGroupMumber() != null
				&& speechList.get(position).getGroupMumber().size() != 0) {
			listItem.groupLeaderTV.setText(speechList.get(position)
					.getGroupMumber().get(0));
		} else {
			listItem.groupLeaderTV.setText("has not leader");
		}
		listItem.gradeTV.setText( String.valueOf(speechList.get(position).getGrade()));
		
		return view;
	}

	private class ListItem {
		public TextView titleTV;
		public TextView groupLeaderTV;
		public TextView gradeTV;
		public ImageView groupLogo;
	}

}
