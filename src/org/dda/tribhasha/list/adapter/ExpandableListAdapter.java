package org.dda.tribhasha.list.adapter;

import java.util.ArrayList;

import org.dda.tribhasha.R;
import org.dda.tribhasha.list.data.StringValueController;
import org.dda.tribhasha.list.data.Word;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private StringValueController strValueController = new StringValueController();

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	private Context context;

	private ArrayList<String> groups;

	private ArrayList<ArrayList<Word>> children;

	public ExpandableListAdapter(Context context, ArrayList<String> groups,
			ArrayList<ArrayList<Word>> children) {
		this.context = context;
		this.groups = groups;
		this.children = children;
	}

	/**
	 * A general add method, that allows you to add a Vehicle to this list
	 * 
	 * Depending on if the category opf the vehicle is present or not, the
	 * corresponding item will either be added to an existing group if it
	 * exists, else the group will be created and then the item will be added
	 * 
	 * @param vehicle
	 */
	public void addItem(Word word) {
		if (!groups.contains(word.getGroup())) {
			groups.add(word.getGroup());
		}
		int index = groups.indexOf(word.getGroup());
		if (children.size() < index + 1) {
			children.add(new ArrayList<Word>());
		}
		children.get(index).add(word);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return children.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	// Return a child view. You can load your custom layout here.
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		Word word = (Word) getChild(groupPosition, childPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.child_layout, null);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.tvChild);
		tv.setText("   " + word.getKey_en());

		// Depending upon the child type, set the imageTextView01
		tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return children.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	// Return a group view. You can load your custom layout here.
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String group = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.group_layout, null);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.tvGroup);
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.imgGroup);

		try {
			imageView.setImageDrawable(context.getResources().getDrawable(
					strValueController.getImageValue(context.getResources(),
							group)));
			tv.setText(strValueController.getStringValue(
					context.getResources(), (group)));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}

}
