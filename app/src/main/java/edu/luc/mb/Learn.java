package edu.luc.mb;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import luc.mb.R;

public class Learn extends AppCompatActivity {
	
	private static String[] NOTES = new String[]{" Treble Clef", "Treble Clef Middle C", "Treble Clef D", "Treble Clef E", "Treble Clef F", "Treble Clef G", "Treble Clef A",
												 "Treble Clef B", "Treble Clef C", "Treble Clef D", "Treble Clef E", "Treble Clef F", "Treble Clef G",
												 "Treble Clef A", "Treble Clef B", "Treble Clef C", "Treble Clef Lines", "Treble Clef Spaces"};
	
	private static int[] IMAGES = new int[] {R.drawable.treble_clef, R.drawable.treb_middle_c, R.drawable.treb_d, R.drawable.treb_e, R.drawable.treb_f, R.drawable.treb_g, R.drawable.treb_a,
											 R.drawable.treb_b, R.drawable.treb_c1, R.drawable.treb_d1, R.drawable.treb_e1, R.drawable.treb_f1, R.drawable.treb_g1,
											 R.drawable.treb_a1, R.drawable.treb_b1, R.drawable.treb_c2, R.drawable.treb_lines, R.drawable.treb_spaces};
	
	private static String[] BASS_NOTES = new String[]{"Bass Clef", "Bass Clef Middle C", "Bass Clef B", "Bass Clef A", "Bass Clef G", "Bass Clef F", "Bass Clef E", "Bass Clef D",
													  "Bass Clef C", "Bass Clef B", "Bass Clef A", "Bass Clef G", "Bass Clef F", "Bass Clef E", "Bass Clef D", "Bass Clef C", "Bass Clef Lines", "Bass Clef Spaces"};
	
	private static int[] BASS_IMGS = new int[]{R.drawable.bass_clef, R.drawable.bass_middle_c, R.drawable.bass_b, R.drawable.bass_a, R.drawable.bass_g, R.drawable.bass_f, R.drawable.bass_e,
		 									   R.drawable.bass_d, R.drawable.bass_c1, R.drawable.bass_b1, R.drawable.bass_a1, R.drawable.bass_g1, R.drawable.bass_f1,
		 									   R.drawable.bass_e1, R.drawable.bass_d1, R.drawable.bass_c2, R.drawable.bass_lines, R.drawable.bass_spaces};
	
	private static String[] N_TYPES = new String[]{"Whole Note\n(4 beats)", "Whole Rest\n(4 beats)", "Half Note\n(2 beats)", "Half Rest\n(2 beats)", "Quarter Note\n(1 beat)", "Quarter Rest\n(1 beat)",
												   "Eighth Note\n(1/2 of the beat)", "Eighth Rest\n(1/2 of the beat)", "Sixteenth Note\n(1/4 of the beat)", "Sixteenth Rest\n(1/4 of the beat)"};
	
	private static int[] NTYP_IMGS = new int[]{R.drawable.whole_note, R.drawable.whole_rest, R.drawable.half_note, R.drawable.half_rest, R.drawable.quarter_note, R.drawable.quarter_rest,
											   R.drawable.eighth_note, R.drawable.eighth_rest, R.drawable.sixteenth_note, R.drawable.sixteenth_rest};
	
	private static String[] DYNAMICS = new String[]{"pianissimo\n(very quiet)", "piano\n(quiet)", "mezzo-piano\n(medium quiet)", "mezzo-forte\n(medium loud)", "forte\n(loud)", "fortissimo\n(very loud)", "crescendo\n(gradually get louder)", "diminuendo\n(gradually get softer)"};
	
	private static int[] DYN_IMGS = new int[]{R.drawable.pianissimo, R.drawable.piano, R.drawable.mezzo_piano, R.drawable.mezzo_forte, R.drawable.forte, R.drawable.fortissimo, R.drawable.crescendo, R.drawable.diminuendo};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.learn);

		// Added this to try to launch a list view
		
		
		Button b = (Button) findViewById(R.id.treb_button);
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				Intent b = new Intent(Learn.this, HorizontalListViewDemo.class); 
				b.putExtra("values", NOTES);
				b.putExtra("images", IMAGES);
				startActivity(b);
			}
		});
		
		Button c = (Button) findViewById(R.id.bass_button);
		c.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				Intent c = new Intent(Learn.this, HorizontalListViewDemo.class); 
				c.putExtra("values", BASS_NOTES);
				c.putExtra("images", BASS_IMGS);
				startActivity(c);
			}
		}); 
		
		Button d = (Button) findViewById(R.id.dynamics_button);
		d.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				Intent d = new Intent(Learn.this, HorizontalListViewDemo.class); 
				d.putExtra("values", DYNAMICS);
				d.putExtra("images", DYN_IMGS);
				startActivity(d);
			}
		});
		
		Button e = (Button) findViewById(R.id.note_types_button);
		e.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				Intent e = new Intent(Learn.this, HorizontalListViewDemo.class); 
				e.putExtra("values", N_TYPES);
				e.putExtra("images", NTYP_IMGS);
				startActivity(e);
			}
		});
	}
	
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