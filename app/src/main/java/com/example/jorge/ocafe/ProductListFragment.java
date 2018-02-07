package com.example.jorge.ocafe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {

    private ProductsAdapter productsAdapter;

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
        productsAdapter = new ProductsAdapter(getActivity(), MainActivity.products);
        ListView listView = getActivity().findViewById(R.id.listViewProduct);
        listView.setAdapter(productsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = productsAdapter.getItem(i);
                if (getActivity().findViewById(R.id.fragment_container_portrait) != null){
                    Toast.makeText(getActivity().getBaseContext(), "Clicked Portrait." +
                                    product.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    ProductDetailsFragment.onArticleSelected(product);
                }
            }
        });
    }
}