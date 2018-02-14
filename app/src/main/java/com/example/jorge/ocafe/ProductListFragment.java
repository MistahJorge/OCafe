package com.example.jorge.ocafe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {

    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ListView listView = getActivity().findViewById(R.id.listViewProduct);
        if (listView != null){
            MainActivity.productsAdapter = new ProductsAdapter(getActivity(), MainActivity.products);
            listView.setAdapter(MainActivity.productsAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    MainActivity.product = MainActivity.productsAdapter.getItem(i);
                    if (getActivity().findViewById(R.id.fragment_container_portrait) != null) {
                        changePortraitFragmentToDetails(FragmentCache.getProductDetailsFragmentPortrait());
                    } else {
                        if(getActivity().findViewById(R.id.textViewProductNameDetails) == null) {
                            changeLandscapeFragmentToDetails(FragmentCache.getProductDetailsFragmentLand());
                        } else {
                            ProductDetailsFragment.onProductSelected();
                        }
                    }
                }
            });
        }
    }

    public void changePortraitFragmentToDetails(ProductDetailsFragment productDetailsFragment) {
        productDetailsFragment.setArguments(getActivity().getIntent().getExtras());

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container_portrait, productDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void changeLandscapeFragmentToDetails(ProductDetailsFragment productDetailsFragment) {
        productDetailsFragment.setArguments(getActivity().getIntent().getExtras());

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container_land2, productDetailsFragment);
        transaction.commit();
    }
}