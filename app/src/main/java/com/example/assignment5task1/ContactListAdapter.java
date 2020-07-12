package com.example.assignment5task1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactListAdapter extends BaseAdapter
{
    private ArrayList<ContactDetails> cd;
    private Context context;

    public ContactListAdapter(ArrayList<ContactDetails> cd, Context context) {
        this.cd = cd;
        this.context = context;
    }


    @Override
    public int getCount() {
        return cd.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ContactDetails cD = cd.get(i);
        if(view == null)
        {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.contact_layout, null);
            MyViewHolder viewHolder = new MyViewHolder(view);
            view.setTag(viewHolder);
        }
        MyViewHolder viewHolder = (MyViewHolder)view.getTag();
        viewHolder.name.setText(cD.getContactName());
        viewHolder.number.setText(cD.getContactNumber());
        if(cD.getContactCategory().equals("Co-worker")){
            viewHolder.image.setImageResource(R.drawable.co_worker_icon1);
        }
        else if(cD.getContactCategory().equals("Family")){
            viewHolder.image.setImageResource(R.drawable.family_icon1);
        }
        else if(cD.getContactCategory().equals("Friends")){
            viewHolder.image.setImageResource(R.drawable.friends_icon1);
        }
        return view;
    }

    public class MyViewHolder{
        public TextView name, number;
        public ImageView image;

        public MyViewHolder(View view) {
            name = (TextView)view.findViewById(R.id.name);
            number = (TextView)view.findViewById(R.id.number);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }
}
