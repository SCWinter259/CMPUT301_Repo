package com.example.simpleparadox.listycity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener{

    // Declare the variables so that you will be able to reference it later.
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> cityDataList;
    Boolean addCity;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities ={"Edmonton", "Vancouver", "Toronto", "Hamilton", "Denver", "Los Angeles"};
        String []provinces = {"AB", "BC", "ON", "ON", "CO", "CA"};

        cityDataList = new ArrayList<>();

        for(int i = 0; i < cities.length; i++) {
            cityDataList.add(new City(cities[i], provinces[i]));
        }

        cityAdapter = new CustomList(this, cityDataList);

        cityList.setAdapter(cityAdapter);

        final FloatingActionButton addCityButton = findViewById(R.id.add_city_button);
        addCityButton.setOnClickListener(view -> {
            this.addCity = true;
            new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY");
        });

        cityList.setOnItemClickListener((adapterView, view, i, l) -> {
            this.addCity = false;
            this.position = i;
            new AddCityFragment(cityDataList.get(i)).show(getSupportFragmentManager(), "EDIT_CITY");
        });
    }

    @Override
    public void onOkPressed(City newCity) {
        if(this.addCity) {
            cityAdapter.add(newCity);
        }
        else {
            cityDataList.set(this.position, newCity);
            cityList.invalidateViews();
        }
    }

}
