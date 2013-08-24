package com.example.dbtest;


import java.util.List;
import java.util.Random;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Tab1 extends Fragment {
	
	private FestivalDataSource datasource;
	ListView list;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_dummy,
				container, false);
		list = (ListView)rootView.findViewById(android.R.id.list);
		
		datasource = new FestivalDataSource(getActivity());
		datasource.open();
		
		List <Festival> values = datasource.getAllFestivals();
		ArrayAdapter <Festival> adapter = new ArrayAdapter<Festival>(getActivity(),android.R.layout.simple_list_item_1, values);
		
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			public void onItemClick(AdapterView<?> adapter,View view, int position, long arg){
				
				Festival festival = (Festival)list.getItemAtPosition(position);
				Intent intent = new Intent(getActivity(), Festival_Item.class);
				intent.putExtra("selected_item", festival);
				
				startActivity(intent);
				
				/*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setMessage(festival.getName());
				builder.create();
				builder.show(); */
				
			}
		});
	
		
		
			final Button btnAdd = (Button)rootView.findViewById(R.id.add);
		    btnAdd.setOnClickListener(new OnClickListener()
	    {
		    	public void onClick(View v)
		    	{
	    			@SuppressWarnings("unchecked")
					ArrayAdapter <Festival> adapter = (ArrayAdapter<Festival>) list.getAdapter();
	    			Festival festival = null;
	    			
	    			String [] festivals = new String[] {"Fest1", "Fest2", "Fest3"};
	    			int nextInt = new Random().nextInt(3);
	    			festival = datasource.createFestival(festivals[nextInt]);
	    			adapter.add(festival);
	    			adapter.notifyDataSetChanged();
	    		}
	    });

		return rootView;
	}
	
}
