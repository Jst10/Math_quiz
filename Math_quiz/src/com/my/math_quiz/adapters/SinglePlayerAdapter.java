package com.my.math_quiz.adapters;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.my.math_quiz.R;
import com.my.math_quiz.utilist.LevelData;

public class SinglePlayerAdapter extends ArrayAdapter<LevelData>{
	LayoutInflater inflater;
	public SinglePlayerAdapter(Context context, int resource, LevelData[] objects) {
		super(context, resource, objects);
		inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);	
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemHolder holder=null;
		LevelData info=getItem(position);
		
		if(convertView==null){
			convertView=inflater.inflate(R.layout.element_single_player, null);
			holder=new ItemHolder(
					(TextView)convertView.findViewById(R.id.ESPtitle),
					(TextView)convertView.findViewById(R.id.ESPdescription),
					(TextView)convertView.findViewById(R.id.ESPtimeL),
					(TextView)convertView.findViewById(R.id.ESPtimeM),
					(TextView)convertView.findViewById(R.id.ESPtimeH),
					(TextView)convertView.findViewById(R.id.ESPscoreL),
					(TextView)convertView.findViewById(R.id.ESPscoreM),
					(TextView)convertView.findViewById(R.id.ESPscoreH));
			convertView.setTag(holder);
		}else{
			holder=(ItemHolder)convertView.getTag();
		}
		holder.title.setText(info.levelName);
		holder.description.setText(info.levelDescription);
		
		holder.timeL.setText(info.levelTimes[0]+"");
		holder.timeM.setText(info.levelTimes[1]+"");
		holder.timeH.setText(info.levelTimes[2]+"");
		
		
		holder.scoreL.setText(info.levelScores[0]+"");
		holder.scoreM.setText(info.levelScores[1]+"");
		holder.scoreH.setText(info.levelScores[2]+"");
		
		
		
		return convertView;
	}
	
	
	@Override
	public LevelData getItem(int position) {
		return super.getItem(position);
	}


	class ItemHolder{
		public TextView title;
		public TextView description;
		
		public TextView timeL;
		public TextView timeM;
		public TextView timeH;
		
		public TextView scoreL;
		public TextView scoreM;
		public TextView scoreH;
		
		public ItemHolder(TextView title,TextView description,TextView timeL,TextView timeM,TextView timeH,TextView scoreL,TextView scoreM,TextView scoreH){
			this.title=title;
			this.description=description;
			
			this.timeL=timeL;
			this.timeM=timeM;
			this.timeH=timeH;
			
			this.scoreL=scoreL;
			this.scoreM=scoreM;
			this.scoreH=scoreH;
		}
		
		
	}
}
