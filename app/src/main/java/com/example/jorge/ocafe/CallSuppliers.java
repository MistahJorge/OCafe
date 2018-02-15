package com.example.jorge.ocafe;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.LinkedHashMap;
import java.util.Map;

public class CallSuppliers extends AppCompatActivity implements OnMapReadyCallback {

    private Scene showAllThreeSuppliers;
    private Scene getSupplierLocationScene;
    private ViewGroup sceneRoot;
    private Transition fadeTransition;

    public static SharedPreferences sharedPreferences;

    public static final String PREFERENCE_FILENAME = "preferences";

    public static Map<String,String> supplierName = new LinkedHashMap<>();
    public static final String PREFERENCE_SUPPLIER_NAME = "suppliername";
    public static final String PREFERENCE_SUPPLIER_NAME_2 = "suppliername2";
    public static final String PREFERENCE_SUPPLIER_NAME_3 = "suppliername3";

    public static Map<String,String> supplierPhoneNumber = new LinkedHashMap<>();
    public static final String PREFERENCE_SUPPLIER_NUMBER = "suppliernumber";
    public static final String PREFERENCE_SUPPLIER_NUMBER_2 = "suppliernumber2";
    public static final String PREFERENCE_SUPPLIER_NUMBER_3 = "suppliernumber3";

    public static Map<String,String> supplierLatitude = new LinkedHashMap<>();
    public static final String PREFERENCE_SUPPLIER_LATITUDE = "supplierlatitude";
    public static final String PREFERENCE_SUPPLIER_LATITUDE_2 = "supplierlatitude2";
    public static final String PREFERENCE_SUPPLIER_LATITUDE_3 = "supplierlatitude3";

    public static Map<String,String> supplierLongitude = new LinkedHashMap<>();
    public static final String PREFERENCE_SUPPLIER_LONGITUDE = "supplierlongitude";
    public static final String PREFERENCE_SUPPLIER_LONGITUDE_2 = "supplierlongitude2";
    public static final String PREFERENCE_SUPPLIER_LONGITUDE_3 = "supplierlongitude3";

    private String choosenSupplierName;
    private double choosenSupplierLatitude;
    private double choosenSupplierLongitude;

    private boolean called = false;

    private MapFragment mMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_suppliers);

        Toolbar oCafeToolbar = findViewById(R.id.o_cafe_custom_toolbar);
        oCafeToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(oCafeToolbar);

        sharedPreferences = this.getSharedPreferences(PREFERENCE_FILENAME, MODE_PRIVATE);

        supplierName.put("suppliername", sharedPreferences.getString(
                PREFERENCE_SUPPLIER_NAME, "Limini Coffee"));
        supplierName.put("suppliername2", sharedPreferences.getString(
                PREFERENCE_SUPPLIER_NAME_2, "Veneziano Coffee"));
        supplierName.put("suppliername3", sharedPreferences.getString(
                PREFERENCE_SUPPLIER_NAME_3, "Prima Coffee"));

        supplierPhoneNumber.put("suppliernumber", sharedPreferences.getString(
                PREFERENCE_SUPPLIER_NUMBER, "123"));
        supplierPhoneNumber.put("suppliernumber2", sharedPreferences.getString(
                PREFERENCE_SUPPLIER_NUMBER_2, "124"));
        supplierPhoneNumber.put("suppliernumber3", sharedPreferences.getString(
                PREFERENCE_SUPPLIER_NUMBER_3, "125"));

        supplierLatitude.put("supplierlatitude", sharedPreferences.getString(
                PREFERENCE_SUPPLIER_LATITUDE, "53.770032"));
        supplierLatitude.put("supplierlatitude2", sharedPreferences.getString(
                PREFERENCE_SUPPLIER_LATITUDE_2, "-27.483778"));
        supplierLatitude.put("supplierlatitude3", sharedPreferences.getString(
                PREFERENCE_SUPPLIER_LATITUDE_3, "38.1841066"));

        supplierLongitude.put("supplierlongitude", sharedPreferences.getString(
                PREFERENCE_SUPPLIER_LONGITUDE, "-1.7694477"));
        supplierLongitude.put("supplierlongitude2", sharedPreferences.getString(
                PREFERENCE_SUPPLIER_LONGITUDE_2, "153.0004323"));
        supplierLongitude.put("supplierlongitude3", sharedPreferences.getString(
                PREFERENCE_SUPPLIER_LONGITUDE_3, "-85.6997615"));
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateTextViews();
    }

    private void updateTextViews() {
        TextView supplierNameTextView = findViewById(R.id.textViewSupplierName);
        supplierNameTextView.setText(supplierName.get("suppliername"));

        TextView supplierNumberTextView = findViewById(R.id.textViewSupplierNumber);
        supplierNumberTextView.setText(supplierPhoneNumber.get("suppliernumber"));

        TextView supplierName2TextView = findViewById(R.id.textViewSupplierName2);
        supplierName2TextView.setText(supplierName.get("suppliername2"));

        TextView supplier2NumberTextView = findViewById(R.id.textViewSupplierNumber2);
        supplier2NumberTextView.setText(supplierPhoneNumber.get("suppliernumber2"));

        TextView supplierName3TextView = findViewById(R.id.textViewSupplierName3);
        supplierName3TextView.setText(supplierName.get("suppliername3"));

        TextView supplier3NumberTextView = findViewById(R.id.textViewSupplierNumber3);
        supplier3NumberTextView.setText(supplierPhoneNumber.get("suppliernumber3"));
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onStart() {
        super.onStart();
        sceneRoot = findViewById(R.id.scene_root);

        // Create the scenes
        showAllThreeSuppliers = Scene.getSceneForLayout(sceneRoot, R.layout.call_suppliers_scene, this);
        getSupplierLocationScene = Scene.getSceneForLayout(sceneRoot, R.layout.check_supplier_location_scene, this);

        fadeTransition = TransitionInflater.from(this)
                .inflateTransition(R.transition.fade_transition);
    }

    public void callSupplier(View view) {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);

        if (view == findViewById(R.id.buttonCallSupplier))
            phoneIntent.setData(Uri.parse("tel:" + supplierPhoneNumber.get("suppliernumber")));
        else if (view == findViewById(R.id.buttonCallSupplier2))
            phoneIntent.setData(Uri.parse("tel:" + supplierPhoneNumber.get("suppliernumber2")));
        else
            phoneIntent.setData(Uri.parse("tel:" + supplierPhoneNumber.get("suppliernumber3")));

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "You do not have permission to call to the supplier",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(phoneIntent);
        called = true;
    }

    public void confirmSupplier(View view) {
        if (called) {
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Call the supplier, will ya?\nIf you just wan't to " +
                            "go back, then press back",
                    Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void checkLocation(View view) {
        if (view == findViewById(R.id.buttonAnimationTransitionBack)) {
            TransitionManager.go(showAllThreeSuppliers, fadeTransition);
            updateTextViews();
        } else{
            if (view == findViewById(R.id.buttonCheckSupplier)) {
                choosenSupplierName = supplierLatitude.get("suppliername");
                choosenSupplierLatitude = Double.parseDouble(supplierLatitude.get("supplierlatitude"));
                choosenSupplierLongitude = Double.parseDouble(supplierLongitude.get("supplierlongitude"));
            } else if (view == findViewById(R.id.buttonCheckSupplier2)){
                choosenSupplierName = supplierLatitude.get("suppliername2");
                choosenSupplierLatitude = Double.parseDouble(supplierLatitude.get("supplierlatitude2"));
                choosenSupplierLongitude = Double.parseDouble(supplierLongitude.get("supplierlongitude2"));
            } else {
                choosenSupplierName = supplierLatitude.get("suppliername3");
                choosenSupplierLatitude = Double.parseDouble(supplierLatitude.get("supplierlatitude3"));
                choosenSupplierLongitude = Double.parseDouble(supplierLongitude.get("supplierlongitude3"));
            }

            TransitionManager.go(getSupplierLocationScene, fadeTransition);

            TextView supplierNameTextView = findViewById(R.id.textViewSupplierNameSubtitle);
            supplierNameTextView.setText(choosenSupplierName);

            TextView supplierLatitudeTextView = findViewById(R.id.textViewSupplierLatitude);
            supplierLatitudeTextView.setText("Latitude: " + choosenSupplierLatitude);

            TextView supplierLongitudeTextView = findViewById(R.id.textViewSupplierLongitude);
            supplierLongitudeTextView.setText("Longitude: " + choosenSupplierLongitude);


            mMapFragment = MapFragment.newInstance();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.map_container, mMapFragment);
            mMapFragment.getMapAsync(this);

            fragmentTransaction.commit();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        float zoomLevel = 16.0f;
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(choosenSupplierLatitude,
                choosenSupplierLongitude), zoomLevel));
    }
}
