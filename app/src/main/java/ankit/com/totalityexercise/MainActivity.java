package ankit.com.totalityexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter adapter;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {    //Button to Refresh the layout
            @Override
            public void onClick(View view) {
                listView = findViewById(R.id.listview);
                adapter = new Adapter(getApplicationContext());
                listView.setAdapter(adapter);
            }
        });

        listView = findViewById(R.id.listview);
        adapter = new Adapter(getApplicationContext());  //setting values
        listView.setAdapter(adapter);   //populating listview
    }


}
