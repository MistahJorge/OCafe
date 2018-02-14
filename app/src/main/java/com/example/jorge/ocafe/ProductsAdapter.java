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
        final Product product = this.getItem(i);

        TextView textViewProductName = view.findViewById(R.id.textViewProductName);
        textViewProductName.setText(product.getName());

        ImageView imageViewProductPlaceHolder = view.findViewById(R.id.imageViewProductImage);
        Glide.with(context).load(product.getImage()).into(imageViewProductPlaceHolder);

        TextView textViewProductPrice = view.findViewById(R.id.textViewProductPrice);
        textViewProductPrice.setText(String.format("Price: %.2f€", product.getPrice()));

        TextView textViewProductStock = view.findViewById(R.id.textViewProductStock);
        textViewProductStock.setText("Stock: " + product.getStock());

        ImageView imageViewAdd = view.findViewById(R.id.imageViewAdd);
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (product.getStock() > 0) {
                    activity = (Activity) context;
                    if (activity.findViewById(R.id.fragment_container_portrait) != null) {
                        int flow = 1;
                        product.editOrderAdd();
                        changeFragmentToOrderList(
                                FragmentCache.getOrderListFragmentPortrait(), flow);
                    } else {
                        int flow = 2;
                        product.editOrderAdd();
                        if (activity.findViewById(R.id.textViewOrderListTitle) == null) {
                            changeFragmentToOrderList(
                                    FragmentCache.getOrderListFragmentLand(), flow);
                        } else {
                            OrderListFragment.callUpdateOrderList();
                        }
                    }
                    updateProduct();
                }
            }
        });

        ImageView imageViewRemove = view.findViewById(R.id.imageViewRemove);
        imageViewRemove.setRotation(180);
        imageViewRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (product.getStockOnList() > 0) {
                    activity = (Activity) context;
                    if (activity.findViewById(R.id.fragment_container_portrait) != null) {
                        int flow = 1;
                        product.editOrderRemove();
                        changeFragmentToOrderList(
                                FragmentCache.getOrderListFragmentPortrait(), flow);
                    } else {
                        int flow = 2;
                        product.editOrderRemove();
                        if (activity.findViewById(R.id.textViewOrderListTitle) == null) {
                        changeFragmentToOrderList(
                                FragmentCache.getOrderListFragmentLand(), flow);
                        } else {
                            OrderListFragment.callUpdateOrderList();
                        }
                    }
                    updateProduct();
                }
            }
        });

        return view;
    }

    private void changeFragmentToOrderList(OrderListFragment orderListFragment, int flow) {
        orderListFragment.setArguments(activity.getIntent().getExtras());

        android.support.v4.app.FragmentManager fragmentManager = getActivitySupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (flow){
            case 1:     transaction.replace(R.id.fragment_container_portrait, orderListFragment);
                        transaction.addToBackStack(null);
                        break;
            case 2:     transaction.replace(R.id.fragment_container_land2, orderListFragment);
                        break;
            default:    Toast.makeText(context, "You are not supposed to reach this part of " +
                    "the switch... check your code", Toast.LENGTH_SHORT).show();
        }
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

    public void updateProduct() {
        notifyDataSetChanged();
    }
}