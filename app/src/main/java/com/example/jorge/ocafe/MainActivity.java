package com.example.jorge.ocafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ProductListFragment productsListFragment;
    ProductDescriptionFragment productDescriptionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container_land1) != null) {
            if (productsListFragment != null) {
                return;
            }
            productsListFragment = new ProductListFragment();
            productsListFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_land1, productsListFragment).commit();
        }

        if (findViewById(R.id.fragment_container_land2) != null) {
            if (productDescriptionFragment != null) {
                return;
            }
            productDescriptionFragment = new ProductDescriptionFragment();
            productDescriptionFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_land2, productDescriptionFragment).commit();
        }
    }
}
