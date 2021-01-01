package app.mma.jsonxml;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_xmlpp, btn_json, btn_xmljdom, btn_tours;
    ListView listview;

    private List<Flower> flowers;
    private ArrayAdapter<Flower> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //21
        //fj
        //456
        btn_xmlpp = (Button) findViewById(R.id.btn_xml_pp);
        btn_xmljdom = (Button) findViewById(R.id.btn_xml_jdom);
        btn_json = (Button) findViewById(R.id.btn_json);
        btn_tours = (Button) findViewById(R.id.btn_tours);
        listview = (ListView) findViewById(R.id.listview);

        btn_xmlpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flowers = new FlowerXmlPullParser(MainActivity.this).parseXml();
                Toast.makeText(MainActivity.this, "XmlPullParser : Returned " + flowers.size() + " items.",
                        Toast.LENGTH_SHORT).show();
                refreshDisplay();
            }
        });
        btn_xmljdom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream input = getResources().openRawResource(R.raw.flowers_xml);
                flowers = new FlowerJdomParserV2(input).parseXml();
                Toast.makeText(MainActivity.this, "XmlJdomParser : Returned " + flowers.size() + " items.",
                        Toast.LENGTH_SHORT).show();
                refreshDisplay();
            }
        });

        btn_json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream input = getResources().openRawResource(R.raw.flowers_json);
                flowers = FlowerJsonParser.parseJson(input);
                Toast.makeText(MainActivity.this, "JsonParser : Returned " + flowers.size() + " items.",
                        Toast.LENGTH_SHORT).show();
                refreshDisplay();
            }
        });
        btn_tours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream input = getResources().openRawResource(R.raw.tours);
                List<Tour> tours = new TourJdomParser(input).parseXml();
                Toast.makeText(MainActivity.this,
                        "TourXmlParser : Returned " + tours.size() + " items.",
                        Toast.LENGTH_SHORT)
                        .show();
                // display tours
                ArrayAdapter<Tour> tourAdapter = new ArrayAdapter<Tour>(
                        MainActivity.this, android.R.layout.simple_list_item_1, tours);
                listview.setAdapter(tourAdapter);
                // create tours.json
                JSONArray json = Tour.tourListToJsonArray(tours);
                try{
                    FileOutputStream fos = openFileOutput("tours.json", MODE_PRIVATE);
                    fos.write(json.toString().getBytes());
                    fos.close();
                } catch (IOException e) {e.printStackTrace();}

            }
        });
    }



    public void refreshDisplay(){
        if(flowers == null){
            flowers = new ArrayList<>();
        }
        adapter = new FlowerAdapter(this, flowers);
        listview.setAdapter(adapter);
    }





}
