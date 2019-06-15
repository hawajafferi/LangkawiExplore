package com.myproject.langkawiexplore;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView lvplace;
    ArrayList<HashMap<String, String>> placelist;
    String userid, name, phone;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvplace = findViewById(R.id.listPlaces);
        button = findViewById(R.id.button7);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userid = bundle.getString("userid");
        name = bundle.getString("name");
        phone = bundle.getString("phone");
        lvplace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, restlist.get(position).get("restid"), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, PlaceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("placeid", placelist.get(position).get("placeid"));
                bundle.putString("name", placelist.get(position).get("name"));
                bundle.putString("phone", placelist.get(position).get("phone"));
                bundle.putString("address", placelist.get(position).get("address"));
                bundle.putString("location", placelist.get(position).get("location"));
                bundle.putString("userid", userid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                places();
            }
        });

        loadPlaces();

    }

    private void places() {
        Intent intent = new Intent(MainActivity.this,PlaceActivity.class);
        MainActivity.this.finish();
        startActivity(intent);
    }

    private void loadPlaces() {
        class LoadRestaurant extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {

                HashMap<String, String> hashMap = new HashMap<>();
                String loc = null;
                hashMap.put("location", loc);
                RequestHandler rh = new RequestHandler();
                placelist = new ArrayList<>();
                String s = rh.sendPostRequest
                        ("http://lastyeartit.com/langkawi/load_place.php", hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                // Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                placelist.clear();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray placearray = jsonObject.getJSONArray("place");
                    Log.e("Hawa", jsonObject.toString());
                    for (int i = 0; i < placearray.length(); i++) {
                        JSONObject c = placearray.getJSONObject(i);
                        String pid = c.getString("placeid");
                        String pname = c.getString("name");
                        String pphone = c.getString("phone");
                        String paddress = c.getString("address");
                        String plocation = c.getString("location");
                        HashMap<String, String> restlisthash = new HashMap<>();
                        restlisthash.put("placeid", pid);
                        restlisthash.put("name", pname);
                        restlisthash.put("phone", pphone);
                        restlisthash.put("address", paddress);
                        restlisthash.put("location", plocation);
                        placelist.add(restlisthash);
                    }
                } catch (final JSONException e) {
                    Log.e("JSONERROR", e.toString());
                }
            }

            }

            LoadRestaurant loadRestaurant = new LoadRestaurant();
        loadRestaurant.execute();
        }

    }

