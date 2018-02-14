package com.example.jorge.ocafe;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderListFragment extends Fragment {

    public static double totalPrice;

    private static Context context;

    private TextView orderListContentTextViewForIf;
    public static TextView orderListContentTextView;
    public static TextView orderListPriceTextView;


    public OrderListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.context = getActivity();
        orderListContentTextViewForIf = getActivity().findViewById(R.id.textViewOrderListContent);
        orderListContentTextView = orderListContentTextViewForIf;
        orderListPriceTextView = getActivity().findViewById(R.id.textViewOrderListPrice);
        if (orderListContentTextViewForIf != null){
            updateOrderListContent();
        }
    }

    public static void callUpdateOrderList() {
        OrderListFragment.updateOrderListContent();
    }

    private static void updateOrderListContent() {
        totalPrice = 0;
        for (int i = 0; i < MainActivity.products.size(); i++) {
            Product product = MainActivity.products.get(i);
            if(i == 0){
                if (product.getStockOnList() > 0) {
                    orderListContentTextView.setText(product.getName() + ": " +
                            product.getStockOnList() + '\n');
                } else {
                    orderListContentTextView.setText("");
                }
            } else {
                if (product.getStockOnList() > 0) {
                    orderListContentTextView.setText(orderListContentTextView.getText().toString() +
                            product.getName() + ": " + product.getStockOnList() + '\n');
                }
            }
            totalPrice = totalPrice + product.getPrice() * ((double) product.getStockOnList());
            orderListPriceTextView.setText(String.format("Total price: %.2f€", totalPrice));
        }
    }
}
