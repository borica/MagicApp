package com.example.brcomboricagrid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.brcomboricagrid.R;
import com.example.brcomboricagrid.models.Card;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CardAdapter extends ArrayAdapter<Card> {

    private Context mContext;
    private int mResource;

    public CardAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Card> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView txtCardName = convertView.findViewById(R.id.cardNameList);
        TextView txtCardType = convertView.findViewById(R.id.cardType);

        txtCardName.setText(getItem(position).getName());
        txtCardType.setText(getItem(position).getType());

        return convertView;
    }
}
