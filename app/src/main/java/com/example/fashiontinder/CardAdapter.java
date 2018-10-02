package com.example.fashiontinder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class CardAdapter extends ArrayAdapter<Card> {

    public CardAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        ViewHolder holder;

        if (contentView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            contentView = inflater.inflate(R.layout.item_card, parent, false);
            holder = new ViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }

        Card card = getItem(position);

        holder.name.setText("TITLE");
        holder.subtitle.setText("SUBTITLE");
        Picasso.get()
                .load(card.getUrl())
                .into(holder.image, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("CardAdapter", "onSuccess() called");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("CardAdapter", "onError: ", e );
                    }
                });

        return contentView;
    }

    private static class ViewHolder {
        public TextView name;
        public TextView subtitle;
        public ImageView image;

        public ViewHolder(View view) {
            this.name = (TextView) view.findViewById(R.id.item_card_title);
            this.subtitle = (TextView) view.findViewById(R.id.item_card_subtitle);
            this.image = (ImageView) view.findViewById(R.id.item_card_image);
        }
    }

}

