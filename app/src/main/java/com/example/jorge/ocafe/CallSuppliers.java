package com.example.jorge.ocafe;

import android.Manifest;
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

import java.util.LinkedHashMap;
import java.util.Map;

public class CallSuppliers extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;

    public static final String PREFERENCE_FILENAME = "preferences";

    public static final String PREFERENCE_SUPPLIER1 = "supplier1";
    public static final String PREFERENCE_SUPPLIER2 = "supplier2";
    public static final String PREFERENCE_SUPPLIER3 = "supplier3";

    public static Map<String,String> supplierId = new LinkedHashMap<>();

    Scene _1stScene;
    Scene _2ndScene;
    ViewGroup sceneRoot;
    Transition fadeTransition;

    private boolean called = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_suppliers);

        Toolbar oCafeToolbar = findViewById(R.id.o_cafe_custom_toolbar);
        oCafeToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(oCafeToolbar);

        sharedPreferences = this.getSharedPreferences(PREFERENCE_FILENAME, MODE_PRIVATE);

        supplierId.put("supplier1", sharedPreferences.getString(PREFERENCE_SUPPLIER1, "123"));
        supplierId.put("supplier2", sharedPreferences.getString(PREFERENCE_SUPPLIER2, "124"));
        supplierId.put("supplier3", sharedPreferences.getString(PREFERENCE_SUPPLIER3, "125"));

        TextView LiminicoffeeNumberTextView = findViewById(R.id.textViewtextViewLiminicoffeeNumber);
        LiminicoffeeNumberTextView.setText(supplierId.get("supplier1"));

        TextView VenezianocoffeeNumberTextView = findViewById(R.id.textViewtextViewVenezianocoffeeNumber);
        VenezianocoffeeNumberTextView.setText(supplierId.get("supplier2"));

        TextView primacoffeeNumberTextView = findViewById(R.id.textViewPrimacoffeeNumber);
        primacoffeeNumberTextView.setText(supplierId.get("supplier3"));
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onStart() {
        super.onStart();
        sceneRoot = findViewById(R.id.scene_root);

        // Create the scenes
        _1stScene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_1, this);
        _2ndScene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_2, this);

        fadeTransition = TransitionInflater.from(this)
                .inflateTransition(R.transition.fade_transition);
    }

    public void callSupplier(View view) {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);

        if (view == findViewById(R.id.buttonCallSupplier))
            phoneIntent.setData(Uri.parse("tel:" + supplierId.get("supplier1")));
        else if (view == findViewById(R.id.buttonCallSupplier2))
            phoneIntent.setData(Uri.parse("tel:" + supplierId.get("supplier2")));
        else
            phoneIntent.setData(Uri.parse("tel:" + supplierId.get("supplier3")));

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
        if (called == true) {
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Call the supplier, will ya?",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void checkLocation(View view) {
        if (view == findViewById(R.id.buttonAnimationTransitionBack))
        TransitionManager.go(_1stScene, fadeTransition);
        else
        TransitionManager.go(_2ndScene, fadeTransition);
    }
}
