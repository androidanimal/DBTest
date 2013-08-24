package com.example.dbtest;

import android.os.Parcel;
import android.os.Parcelable;

public class Festival implements Parcelable {
	
	private long id;
	private String name;
	
	 public long getID(){
		 return id;
	 }
	 public void setId(long id){
		 this.id = id;
	 }
	 
	 public String getName(){
		 return name;
	 }
	 
	 public void setName(String name){
		 this.name = name;
	 }
	 
	 @Override
	 public String toString(){
		 return name;
	 }
	 
	 private Festival(Parcel in){
		 this.id = in.readLong();
		 this.name = in.readString();
	 
	 }
	public Festival() {
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(name);
	}
		
		public static final Parcelable.Creator<Festival> CREATOR = new Parcelable.Creator<Festival>() {
			
			public Festival createFromParcel(Parcel in){
				return new Festival(in);
			}
			
			public Festival [] newArray(int size){
			return new Festival [size];
			//TESTER
			}

		};
		
}
