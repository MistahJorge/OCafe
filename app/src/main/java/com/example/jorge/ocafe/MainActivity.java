package com.example.jorge.ocafe;

import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String fakeResponse = "[\n" +
            "   {\n" +
            "       \"Category\": \"Drinks\",\n" +
            "       \"Name\": \"Coffee\",\n" +
            "       \"Description\": \"Coffee is a brewed drink prepared from roasted coffee beans, which are the seeds of berries from the Coffea plant.\",\n" +
            "       \"Image\": \"https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/A_small_cup_of_coffee.JPG/275px-A_small_cup_of_coffee.JPG\",\n" +
            "       \"Price\": 0.75,\n" +
            "       \"Stock\": 50\n" +
            "   },\n" +
            "   {\n" +
            "       \"Category\": \"Food\",\n" +
            "       \"Name\": \"Hamburguer\",\n" +
            "       \"Description\": \"A Hamburguer is a sandwich consisting of one or more cooked patties of ground meat, usually beef, placed inside a sliced bread roll or bun.\",\n" +
            "       \"Image\": \"http://fredlanches.com.br/wp-content/uploads/2016/07/model_hamb4.jpg\",\n" +
            "       \"Price\": 1.80,\n" +
            "       \"Stock\": 20\n" +
            "   },\n" +
            "   {\n" +
            "       \"Category\": \"Food\",\n" +
            "       \"Name\": \"Doughnut\",\n" +
            "       \"Description\": \"A doughnut is a type of fried dough confectionery or dessert food.\",\n" +
            "       \"Image\": \"https://www.duckdonuts.com/wp-content/uploads/2017/06/September_Glazed-310x320.png?x19636\",\n" +
            "       \"Price\": 1.10,\n" +
            "       \"Stock\": 43\n" +
            "   }\n" +
            "]";

    public static List<Product> products;
    public static Product product;

    public static ProductsAdapter productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (findViewById(R.id.fragment_container_portrait) != null) {
            if (FragmentCache.productsListFragmentPortrait != null ||
                    FragmentCache.productDetailsFragmentPortrait != null) {
                return;
            }
            setPortraitFragment(FragmentCache.getProductsListFragmentPortrait());

        } else {
            if (FragmentCache.productsListFragmentLand != null &&
                    FragmentCache.orderlistFragmentLand != null) {
                return;
            }
            setLandscapeFragment(FragmentCache.getProductsListFragmentLand(),
                    FragmentCache.getOrderListFragmentLand());
        }

        if (products == null) {
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPortraitFragment(ProductListFragment productsListFragment) {
        productsListFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_portrait,
                productsListFragment).commit();
    }

    private void setLandscapeFragment(ProductListFragment productsListFragment,
                                      OrderListFragment orderListFragment) {
        productsListFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_land1,
                productsListFragment).commit();

        orderListFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_land2,
                orderListFragment).commit();
    }

    private void performFragmentTransaction() {
        if (findViewById(R.id.fragment_container_portrait) != null) {
            FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container_portrait, FragmentCache.getProductsListFragmentPortrait());
            transaction.commit();
        }
        OrderListFragment.callUpdateOrderList();
    }

    public void confirmOrder(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm Order");
        builder.setMessage(OrderListFragment.orderListContentTextView.getText().toString() +
                "\nThe total price is: " + String.format("%.2f€", OrderListFragment.totalPrice) +
                "\n\nAre you sure?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < products.size(); i++){
                    MainActivity.product = products.get(i);
                    MainActivity.product.confirmStockOnListForProduct();
                    MainActivity.productsAdapter.updateProduct();
                }
                performFragmentTransaction();
                Toast.makeText(MainActivity.this, "Confirmed.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog cancelOrderDialog = builder.create();
        cancelOrderDialog.show();
    }

    public void cancelOrder(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Cancel Order");
        builder.setMessage("Are you sure?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < products.size(); i++){
                    MainActivity.product = products.get(i);
                    MainActivity.product.resetStockOnList();
                    MainActivity.productsAdapter.updateProduct();
                }
                performFragmentTransaction();
                Toast.makeText(MainActivity.this, "Order canceled.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog cancelOrderDialog = builder.create();
        cancelOrderDialog.show();
    }
}