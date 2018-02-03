package com.example.jorge.ocafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProductListFragment productsListFragment;
    ProductDetailsFragment productDetailsFragment;

    private static final String fakeResponse = "[\n" +
            "   {\n" +
            "       \"Category\": \"Drinks\",\n" +
            "       \"Name\": \"Coffee\",\n" +
            "       \"Description\": \"Coffee is a brewed drink prepared from roasted coffee beans, which are the seeds of berries from the Coffea plant.\",\n" +
            "       \"Image\": \"https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/A_small_cup_of_coffee.JPG/275px-A_small_cup_of_coffee.JPG\",\n" +
            "       \"Price\" : 0.75,\n" +
            "       \"Stock\" : 50\n" +
            "   },\n" +
            "   {\n" +
            "       \"Category\": \"Food\",\n" +
            "       \"Name\": \"Doughnut\",\n" +
            "       \"Description\": \"A doughnut is a type of fried dough confectionery or dessert food.\",\n" +
            "       \"Image\": \"https://www.duckdonuts.com/wp-content/uploads/2017/06/September_Glazed-310x320.png?x19636\",\n" +
            "       \"Price\" : 1.10,\n" +
            "       \"Stock\" : 28\n" +
            "   }\n" +
            "]";

    public static List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        products = new ArrayList<>();
        try {
            JSONArray fakeJSONArray = new JSONArray(fakeResponse);
            for (int i = 0; i < fakeJSONArray.length(); i++) {
                JSONObject jsonObject = fakeJSONArray.getJSONObject(i);

                String category = jsonObject.optString("Category");
                String name = jsonObject.optString("Name");
                String description = jsonObject.optString("Description");
                String image = jsonObject.optString("Image");
                double price = jsonObject.optDouble("Price");
                int stock = jsonObject.optInt("Stock");

                Product product = new Product(category, name, description, image, price, stock);

                products.add(product);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

        if (findViewById(R.id.fragment_container_portrait) != null) {
            if (productsListFragment != null) {
                return;
            }
            productsListFragment = new ProductListFragment();
            productsListFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_portrait, productsListFragment).commit();
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
            if (productDetailsFragment != null) {
                return;
            }
            productDetailsFragment = new ProductDetailsFragment();
            productDetailsFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_land2, productDetailsFragment).commit();
        }
    }
}