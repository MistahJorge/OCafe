package com.example.jorge.ocafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by jorge on 13/01/2018.
 */

public class ProductsAdapter extends BaseAdapter {

    private Context context;
    private List<Product> products;

    public ProductsAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return products.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(R.layout.product_row, viewGroup, false);
        }
        Product product = this.getItem(i);

        TextView textViewProductName = view.findViewById(R.id.textViewProductNameDetails);
        textViewProductName.setText(product.getName());

        TextView textViewProductPrice = view.findViewById(R.id.textViewProductPrice);
        textViewProductPrice.setText("Price: " + product.getPrice() + "€");

        TextView textViewProductStock = view.findViewById(R.id.textViewProductStock);
        textViewProductStock.setText("Stock: " + product.getStock());

        ImageView imageView = view.findViewById(R.id.imageViewProductImageDetails);
        Glide.with(context).load(product.getImage()).into(imageView);

        return view;
    }
}