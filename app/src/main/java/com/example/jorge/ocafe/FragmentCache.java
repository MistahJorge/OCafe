package com.example.jorge.ocafe;

/**
 * Created by jorge on 06/02/2018.
 */

public class FragmentCache {
    static ProductListFragment productsListFragmentPortrait;
    static ProductListFragment productsListFragmentLand;

    static OrderListFragment orderlistFragmentPortrait;
    static OrderListFragment orderlistFragmentLand;

    static ProductDetailsFragment productDetailsFragmentPortrait;
    static ProductDetailsFragment productDetailsFragmentLand;

    public static ProductListFragment getProductsListFragmentPortrait() {
        if (null == productsListFragmentPortrait) productsListFragmentPortrait = new ProductListFragment();
        return productsListFragmentPortrait;
    }

    public static ProductDetailsFragment getProductDetailsFragmentPortrait() {
        if (null == productDetailsFragmentPortrait) productDetailsFragmentPortrait = new ProductDetailsFragment();
        return productDetailsFragmentPortrait; 
    }

    public static OrderListFragment getOrderListFragmentPortrait() {
        if (null == orderlistFragmentPortrait) orderlistFragmentPortrait = new OrderListFragment();
        return orderlistFragmentPortrait;
    }

    public static OrderListFragment getOrderListFragmentLand() {
        if (null == orderlistFragmentLand) orderlistFragmentLand = new OrderListFragment();
        return orderlistFragmentLand;
    }

    public static ProductListFragment getProductsListFragmentLand() {
        if (null == productsListFragmentLand) productsListFragmentLand = new ProductListFragment();
        return productsListFragmentLand;
    }

    public static ProductDetailsFragment getProductDetailsFragmentLand() {
        if (null == productDetailsFragmentLand) productDetailsFragmentLand = new ProductDetailsFragment();
        return productDetailsFragmentLand;
    }
}
