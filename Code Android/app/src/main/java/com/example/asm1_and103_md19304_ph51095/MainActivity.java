package com.example.asm1_and103_md19304_ph51095;//package com.example.asm1_and103_md19304_ph51095;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ListView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class MainActivity extends AppCompatActivity {
//
//    ListView lvMain;
//    List<PhoneModel> listPhoneModel;
//    PhoneAdapter phoneAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        lvMain = findViewById(R.id.listviewMain);
//        FloatingActionButton btn_add = findViewById(R.id.btn_add);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(APIService.DOMAIN)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIService apiService = retrofit.create(APIService.class);
//
//        Call<List<PhoneModel>> call = apiService.getPhones();
//
//        call.enqueue(new Callback<List<PhoneModel>>() {
//            @Override
//            public void onResponse(Call<List<PhoneModel>> call, Response<List<PhoneModel>> response) {
//                if (response.isSuccessful()) {
//                    listPhoneModel = response.body();
//
//                    phoneAdapter = new PhoneAdapter(getApplicationContext(),listPhoneModel, apiService);
//
//                    lvMain.setAdapter(phoneAdapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<PhoneModel>> call, Throwable t) {
//                Log.e("Main", t.getMessage());
//            }
//        });
//
//        btn_add.setOnClickListener(v -> {
//            PhoneModel dt = new PhoneModel("Huawei Mate XT", "Huawei", 2024, 5000);
//
//            Call<List<PhoneModel>> callAddPhone = apiService.addPhone(dt);
//
//            callAddPhone.enqueue(new Callback<List<PhoneModel>>() {
//                @Override
//                public void onResponse(Call<List<PhoneModel>> call, Response<List<PhoneModel>> response) {
//                    if (response.isSuccessful()) {
//
//                        listPhoneModel.clear();
//
//                        listPhoneModel.addAll(response.body());
//
//                        phoneAdapter.notifyDataSetChanged();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<PhoneModel>> call, Throwable t) {
//                    Log.e("Main", t.getMessage());
//                }
//            });
//        });
//    }
//}

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView lvMain;
    List<PhoneModel> listPhoneModel;

    PhoneAdapter phoneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvMain = findViewById(R.id.listviewMain);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);

        Call<List<PhoneModel>> call = apiService.getPhones();

        call.enqueue(new Callback<List<PhoneModel>>() {
            @Override
            public void onResponse(Call<List<PhoneModel>> call, Response<List<PhoneModel>> response) {
                if (response.isSuccessful()) {
                    listPhoneModel = response.body();
                    phoneAdapter = new PhoneAdapter(getApplicationContext(), listPhoneModel, apiService);
                    lvMain.setAdapter(phoneAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<PhoneModel>> call, Throwable t) {
                Log.e("Main", t.getMessage());
            }
        });

        findViewById(R.id.btn_add).setOnClickListener(v -> {
            PhoneModel dt = new PhoneModel("DT1", "Apple", 2023, 1200);

            Call<List<PhoneModel>> callAddXe = apiService.addPhone(dt);


            callAddXe.enqueue(new Callback<List<PhoneModel>>() {
                @Override
                public void onResponse(Call<List<PhoneModel>> call, Response<List<PhoneModel>> response) {
                    if (response.isSuccessful()) {

                        listPhoneModel.clear();

                        listPhoneModel.addAll(response.body());

                        phoneAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<PhoneModel>> call, Throwable t) {
                    Log.e("Main", t.getMessage());
                }
            });
        });

    }
}