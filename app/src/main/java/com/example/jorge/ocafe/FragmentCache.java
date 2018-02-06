package com.example.jorge.ocafe;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;

/**
 * Created by jorge on 06/02/2018.
 */

public class FragmentCache {
    static ProductListFragment productsListFragmentPortrait;
    static ProductListFragment productsListFragmentLand;
    static ProductDetailsFragment productDetailsFragment;

    public static ProductListFragment getProductsListFragmentPortrait() {
        if (null == productsListFragmentPortrait) productsListFragmentPortrait = new ProductListFragment();
        return productsListFragmentPortrait;
    }

    public static ProductListFragment getProductsListFragmentLand() {
        if (null == productsListFragmentLand) productsListFragmentLand = new ProductListFragment();
        return productsListFragmentLand;
    }

    public static ProductDetailsFragment getProductDetailsFragment() {
        if (null == productDetailsFragment) productDetailsFragment = new ProductDetailsFragment();
        return productDetailsFragment;
    }
}
