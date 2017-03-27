package timefeel.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import timefeel.com.R;
import timefeel.com.adapter.CountryAdapter;

/**
 * Created by test on 08/03/2017.
 */

public class SelectActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_grid);


        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new CountryAdapter(getApplicationContext()));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String tag =  (String) v.getTag();
                Toast.makeText(getApplicationContext() ,"" + position + tag, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("alpha2",tag);
                startActivity(intent);
                finish();
            }
        });
    }
}
