package com.example.mais;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.mais.model.Mais;

import java.util.ArrayList;

public class AdapterMais extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<Mais> maises;
    int p;
    Mais m;

    public AdapterMais(Activity activity, ArrayList<Mais> maises) {
        this.activity = activity;
        this.maises = maises;
    }
    public void setMaises(int p,Mais c){maises.set(p,c);}

    @Override
    public int getCount() {
        return maises.size();
    }

    @Override
    public Mais getItem(int position) {
        return maises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
