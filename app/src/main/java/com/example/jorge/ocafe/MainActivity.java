package com.example.jorge.ocafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String[] names = {
            "Coffe",
            "Cookie"
    };
    public static String[] descriptions = {
            "Article One\n\nExcepteur pour-over occaecat squid biodiesel umami gastropub, nulla " +
                    "laborum salvia dreamcatcher fanny pack. Ullamco culpa retro ea, trust fund " +
                    "excepteur eiusmod direct trade banksy nisi lo-fi cray messenger bag. " +
                    "Nesciunt esse carles selvage put a bird on it gluten-free, wes anderson ut " +
                    "trust fund twee occupy viral. Laboris small batch scenester pork belly, " +
                    "leggings ut farm-to-table aliquip yr nostrud iphone viral next level. Craft " +
                    "beer dreamcatcher pinterest truffaut ethnic, authentic brunch. Esse " +
                    "single-origin coffee banksy do next level tempor. Velit synth dreamcatcher, " +
                    "magna shoreditch in american apparel messenger bag narwhal PBR ennui " +
                    "farm-to-table.",
            "Article Two\n\nVinyl williamsburg non velit, master cleanse four loko banh mi. Enim " +
                    "kogi keytar trust fund pop-up portland gentrify. Non ea typewriter dolore " +
                    "deserunt Austin. Ad magna ethical kogi mixtape next level. Aliqua pork " +
                    "belly thundercats, ut pop-up tattooed dreamcatcher kogi accusamus photo " +
                    "booth irony portland. Semiotics brunch ut locavore irure, enim etsy laborum " +
                    "stumptown carles gentrify post-ironic cray. Butcher 3 wolf moon blog synth, " +
                    "vegan carles odd future."
    };

    ProductListFragment productsListFragment;
    ProductDescriptionFragment productDescriptionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, names[0], Toast.LENGTH_SHORT).show();
        Toast.makeText(this, descriptions[0], Toast.LENGTH_LONG).show();

        Toast.makeText(this, names[1], Toast.LENGTH_SHORT).show();
        Toast.makeText(this, descriptions[1], Toast.LENGTH_LONG).show();

        if (findViewById(R.id.fragment_container_portrait) != null) {
            if (productDescriptionFragment != null) {
                return;
            }
            productDescriptionFragment = new ProductDescriptionFragment();
            productDescriptionFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_portrait, productDescriptionFragment).commit();
            Toast.makeText(this, "Fragment Created.", Toast.LENGTH_SHORT).show();
        }

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
