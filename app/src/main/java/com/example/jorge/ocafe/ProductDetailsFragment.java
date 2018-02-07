package com.example.jorge.ocafe;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment {

    private static Product product;

    private static TextView productNameDetailsTextView;
    private static TextView productCategoryDetailsTextView;
    private static TextView productDescriptionDetailsTextView;
    private static ImageView productImageDetailsImageView;
    private static TextView productPriceDetailsTextView;
    private static TextView productStockDetailsTextView;

    private static Context context;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity().findViewById(R.id.fragment_container_portrait) == null) {
            this.context = getActivity();
            productNameDetailsTextView = getActivity().findViewById(R.id.textViewProductNameDetails);
            productCategoryDetailsTextView = getActivity().findViewById(R.id.textViewProductCategoryDetails);
            productDescriptionDetailsTextView = getActivity().findViewById(R.id.textViewProductDescriptionDetails);
            productImageDetailsImageView = getActivity().findViewById(R.id.imageViewProductImageDetails);
            productPriceDetailsTextView = getActivity().findViewById(R.id.textViewProductPriceDetails);
            productStockDetailsTextView = getActivity().findViewById(R.id.textViewProductStockDetails);
            if (this.product != null) {
                ProductDetailsFragment.updateDetails();
            }
        }
    }

    public static void onArticleSelected(Product product) {
        ProductDetailsFragment.product = product;
        ProductDetailsFragment.updateDetails();
    }

    private static void updateDetails(){
        ProductDetailsFragment.productNameDetailsTextView.setText(ProductDetailsFragment.product.getName());
        Glide.with(ProductDetailsFragment.context).load(ProductDetailsFragment.product.getImage()).into(productImageDetailsImageView);
        ProductDetailsFragment.productCategoryDetailsTextView.setText(ProductDetailsFragment.product.getCategory());
        ProductDetailsFragment.productDescriptionDetailsTextView.setText(ProductDetailsFragment.product.getDescription());
        ProductDetailsFragment.productPriceDetailsTextView.setText(Double.toString(ProductDetailsFragment.product.getPrice()) + " €");
        ProductDetailsFragment.productStockDetailsTextView.setText(Integer.toString(ProductDetailsFragment.product.getStock()));
    }
}