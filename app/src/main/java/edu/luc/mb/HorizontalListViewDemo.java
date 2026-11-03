package edu.luc.mb;


import edu.luc.mb.HorizontalListView;
import luc.mb.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import android.view.View;


public class HorizontalListViewDemo extends Activity {

	private static String[] values;
	private static int[] images;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		values = extras.getStringArray("values");
		images = extras.getIntArray("images");

		setContentView(R.layout.horizontal_list_view);
		
		HorizontalListView listview = (HorizontalListView) findViewById(R.id.listview);
		listview.setAdapter(mAdapter);
	
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.finish();
	}
	

	private BaseAdapter mAdapter = new BaseAdapter() {

		private OnClickListener mOnButtonClicked = new OnClickListener() {

				@Override
				public void onClick(View v) {
					
					AlertDialog.Builder builder = new AlertDialog.Builder(HorizontalListViewDemo.this);
					builder.setMessage("Some text about this thing" + v);
					builder.setPositiveButton("Cool", null);
					
				//	AlertDialog dialog = builder.create();
					
					builder.show();
			//		dialog.show();
	/*				
					//custom dialog window
					final Dialog dialog = new Dialog(mContext);
					dialog.setContentView(R.layout.info_dialog);
					dialog.setTitle("More Info");
					
					// set the custom dialog components
					TextView text = (TextView) dialog.findViewById(R.id.info_text);
					text.setText("Some Text about this thing");
					Button dialogButton = (Button) dialog.findViewById(R.id.dialog_button_ok);
					// if button is clicked, close the custom dialog window
					dialogButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();
						}
					});
					
					dialog.show(); */
					
				}
		};

		@Override
		public int getCount() {
			return values.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
		

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, null);
			TextView title = (TextView) rowView.findViewById(R.id.title);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
	
			imageView.setImageResource(images[position]);
	
			title.setText(values[position]); 
			
			return rowView;
		} 

	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
        case R.id.menu_exit:
        	System.exit(1);
            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}