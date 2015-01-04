package com.example.al.turinrally;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MainMenuCustomAdapter extends BaseAdapter   implements OnClickListener{

	private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    public Resources res;
    MainMenuListModel tempValues=null;
    int i=0;
    
    public MainMenuCustomAdapter(Activity activity, ArrayList arrayList,Resources resLocal) {
        
        /********** Take passed values **********/
         this.activity = activity;
         data = arrayList;
         res = resLocal;
      
         /***********  Layout inflator to call external xml layout () ***********/
          inflater = ( LayoutInflater )activity.
                                      getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      
 }
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(data.size()<=0)
            return 1;
        return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
