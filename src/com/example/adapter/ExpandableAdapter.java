package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.model.ApplySpeech;
import com.example.model.MyVariable;
import com.example.tablayout.R;

public class ExpandableAdapter extends BaseExpandableListAdapter {

	private List<List<ApplySpeech>> allList;
	private Context context;
	private LayoutInflater layoutInflater;
	private List<GroupItem> groupList;
	private int[] groupPicture = { R.drawable.dianzheng, R.drawable.shumei,
			R.drawable.tongruan, R.drawable.jiying, R.drawable.qianruan };
	private int[] childPicture = { R.drawable.group, R.drawable.group1,
			R.drawable.group2 };

	public ExpandableAdapter(Context context, List<List<ApplySpeech>> allList) {
		this.context = context;
		this.allList = allList;
		layoutInflater = LayoutInflater.from(context);
		this.groupList = new ArrayList<GroupItem>();
		groupList.add(new GroupItem("电政"));
		groupList.add(new GroupItem("树莓"));
		groupList.add(new GroupItem("通软"));
		groupList.add(new GroupItem("机应"));
		groupList.add(new GroupItem("嵌软"));
	}

	public ExpandableAdapter(Context context) {
		this.context = context;
	}

	public void setAdapter(List<List<ApplySpeech>> allList) {
		this.allList = allList;

	}

	@Override
	public Object getChild(int parentPosition, int childPosition) {
		return allList.get(parentPosition).get(childPosition);
	}

	@Override
	public long getChildId(int parentPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int parentPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = (View) layoutInflater.inflate(
					R.layout.expandable_item_child, null);
			viewHolder.titleTV = (TextView) convertView
					.findViewById(R.id.applyTitle);
			viewHolder.groupLeaderTV = (TextView) convertView
					.findViewById(R.id.applyGrouperLeader);
			viewHolder.groupLogo = (ImageView) convertView
					.findViewById(R.id.el_child_GroupLogo);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.titleTV.setText(allList.get(parentPosition)
				.get(childPosition).getTitle());
		viewHolder.groupLeaderTV.setText(allList.get(parentPosition)
				.get(childPosition).getGroupLeader());
		viewHolder.groupLogo.setBackgroundDrawable(context.getResources()
				.getDrawable(childPicture[childPosition % 3]));

		return convertView;
	}

	@Override
	public int getChildrenCount(int parentPosition) {
		if (allList != null && allList.size() != 0) {
			if (allList.get(parentPosition) != null)
				return allList.get(parentPosition).size();
		}
		return 0;
	}

	@Override
	public Object getGroup(int parentPosition) {

		return groupList.get(parentPosition);
	}

	@Override
	public int getGroupCount() {
		return groupList.size();
	}

	@Override
	public long getGroupId(int parentPosition) {

		return parentPosition;
	}

	@SuppressLint("NewApi")
	@Override
	public View getGroupView(int parentPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(
				R.layout.expandable_item_group, null);
		TextView textView = (TextView) linearLayout
				.findViewById(R.id.groupItem);
		textView.setText(groupList.get(parentPosition).direction);
		ImageView imageView = (ImageView) linearLayout
				.findViewById(R.id.gourpImageLogo);
		imageView.setBackground(context.getResources().getDrawable(
				groupPicture[parentPosition]));

		return linearLayout;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}

	private class ViewHolder {
		public TextView titleTV;
		public TextView groupLeaderTV;
		public ImageView groupLogo;

	}

	private class GroupItem {
		public String direction;

		public GroupItem(String direction) {
			this.direction = direction;
		}
	}

}
