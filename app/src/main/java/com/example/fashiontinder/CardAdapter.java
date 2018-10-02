package com.example.fashiontinder;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class CardAdapter extends ArrayAdapter<Product> {
    Listener listener;
    public CardAdapter(Context context, Listener listener) {
        super(context, 0);
        this.listener = listener;
    }

    public interface Listener{
        public void onClick(Product product);
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

        final Product product = getItem(position);

        holder.name.setText(product.getName());
        Picasso.get()
                .load(product.getImage())
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

        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(product);
            }
        });
        return contentView;
    }

    private static class ViewHolder {
        public TextView name;
        public TextView subtitle;
        public ImageView image;
        public ImageView info;

        public ViewHolder(View view) {
            this.name = (TextView) view.findViewById(R.id.item_card_title);
            this.image = (ImageView) view.findViewById(R.id.item_card_image);
            this.info = (ImageView) view.findViewById(R.id.produt_info_button);
        }
    }

}

