package com.example.jorge.ocafe;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by jorge on 13/01/2018.
 */

public class ProductsAdapter extends BaseAdapter {
    private Context context;
    private List<Product> products;
    private Activity activity;

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
            view = LayoutInflater.from(this.context).inflate(R.layout.product_row, viewGroup,
                    false);
        }
        Product product = this.getItem(i);

        TextView textViewProductName = view.findViewById(R.id.textViewProductNameDetails);
        textViewProductName.setText(product.getName());

        ImageView imageViewProductPlaceHolder = view.findViewById(R.id.imageViewProductImage);
        Glide.with(context).load(product.getImage()).into(imageViewProductPlaceHolder);

        TextView textViewProductPrice = view.findViewById(R.id.textViewProductPrice);
        textViewProductPrice.setText("Price: " + product.getPrice() + "€");

        TextView textViewProductStock = view.findViewById(R.id.textViewProductStock);
        textViewProductStock.setText("Stock: " + product.getStock());

        ImageView imageViewAdd = view.findViewById(R.id.imageViewAdd);
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = (Activity) context;
                if (activity.findViewById(R.id.textViewOrderListTitle) == null) {
                    if (activity.findViewById(R.id.fragment_container_portrait) != null){
                        int flow = 1;
                        Toast.makeText(context, "It should add a product in the future.",
                                Toast.LENGTH_SHORT).show();
                        changePortraitFragmentToOrderList(
                                FragmentCache.getOrderListFragmentPortrait(), flow);
                    } else {
                        int flow = 2;
                        Toast.makeText(context, "It should add a product in the future.",
                                Toast.LENGTH_SHORT).show();
                        changePortraitFragmentToOrderList(
                                FragmentCache.getOrderListFragmentPortrait(), flow);
                    }
                }
            }
        });

        ImageView imageViewRemove = view.findViewById(R.id.imageViewRemove);
        imageViewRemove.setRotation(180);
        imageViewRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = (Activity) context;
                if(activity.findViewById(R.id.textViewOrderListTitle) == null) {
                    if (activity.findViewById(R.id.fragment_container_portrait) != null) {
                        int flow = 1;
                        Toast.makeText(context, "It should remove a product in the future.",
                                Toast.LENGTH_SHORT).show();
                        changePortraitFragmentToOrderList(
                                FragmentCache.getOrderListFragmentPortrait(), flow);
                    } else {
                        int flow = 2;
                        Toast.makeText(context, "It should remove a product in the future.",
                                Toast.LENGTH_SHORT).show();
                        changePortraitFragmentToOrderList(
                                FragmentCache.getOrderListFragmentPortrait(), flow);
                    }
                }
            }
        });

        return view;
    }

    private void changePortraitFragmentToOrderList(OrderListFragment orderListFragment, int flow) {
        orderListFragment.setArguments(activity.getIntent().getExtras());

        android.support.v4.app.FragmentManager fragmentManager = getActivitySupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (flow){
            case 1: transaction.replace(R.id.fragment_container_portrait, orderListFragment);
                    break;
            case 2: transaction.replace(R.id.fragment_container_land2, orderListFragment);
                break;
            default:
                Toast.makeText(context, "You are not supposed to reach this part of the " +
                        "switch... check your code", Toast.LENGTH_SHORT).show();
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private android.support.v4.app.FragmentManager getActivitySupportFragmentManager(){
        try {
            return ((FragmentActivity) activity).getSupportFragmentManager();
        } catch (ClassCastException e) {
            Toast.makeText(context, "Some error has ocurred, it seems that the view passed " +
                            "was not an activity",
                    Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}