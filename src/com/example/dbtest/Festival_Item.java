package com.example.dbtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class Festival_Item extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clicked_item);
		
		EditText textbox = (EditText)findViewById(R.id.editText1);
		EditText textbox2 = (EditText)findViewById(R.id.editText2);
		Bundle bundle = getIntent().getExtras();
		Festival festival = (Festival)bundle.getParcelable("selected_item");
		
		textbox.setText(festival.getName());
		textbox2.setText(Long.toString(festival.getID()));
		
	}

}
