package com.example.jorge.ocafe;

/**
 * Created by jorge on 06/02/2018.
 */

public class FragmentCache {
    static ProductListFragment productsListFragmentPortrait;
    static ProductListFragment productsListFragmentLand;
    static ProductDetailsFragment productDetailsFragmentPortrait;
    static ProductDetailsFragment productDetailsFragmentLand;

    public static ProductListFragment getProductsListFragmentPortrait() {
        if (null == FragmentCache.productsListFragmentPortrait)
            FragmentCache.productsListFragmentPortrait = new ProductListFragment();
        return FragmentCache.productsListFragmentPortrait;
    }

    public static ProductDetailsFragment getProductDetailsFragmentPortrait() {
        if (null == FragmentCache.productDetailsFragmentPortrait)
            FragmentCache.productDetailsFragmentPortrait = new ProductDetailsFragment();
        return FragmentCache.productDetailsFragmentPortrait;
    }

    public static ProductListFragment getProductsListFragmentLand() {
        if (null == FragmentCache.productsListFragmentLand)
            FragmentCache.productsListFragmentLand = new ProductListFragment();
        return FragmentCache.productsListFragmentLand;
    }

    public static ProductDetailsFragment getProductDetailsFragmentLand() {
        if (null == FragmentCache.productDetailsFragmentLand)
            FragmentCache.productDetailsFragmentLand = new ProductDetailsFragment();
        return FragmentCache.productDetailsFragmentLand;
    }
}
