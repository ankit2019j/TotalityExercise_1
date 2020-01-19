package ankit.com.totalityexercise;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class Adapter extends ArrayAdapter {

    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;
    private static final String URL_BASE = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json";
    List<fact_sets> items;

    public Adapter(@NonNull Context context) {
        super(context, 0);

        requestQueue = Volley.newRequestQueue(context);

        jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL_BASE,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        items = parseJson(response);
                        notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error1", "Error  JSON: " + error.getMessage());
                    }
                }
        );
        requestQueue.add(jsArrayRequest);
    }

    @Override
    public int getCount() {

        if(items==null){
            return 0;
        }
        else
        {
           return items.size();
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItemView;

        listItemView=  null == convertView ? layoutInflater.inflate(
                R.layout.listrow,
                parent,
                false
        ): convertView;

        final fact_sets item = items.get(position);

        TextView textoTitle = (TextView) listItemView.findViewById(R.id.title);
        TextView textoDescription = (TextView) listItemView.findViewById(R.id.description);
        final ImageView imageH = (ImageView) listItemView.findViewById(R.id.image);

        textoTitle.setText(item.getTitle());
        textoDescription.setText(item.getDescription());


        ImageRequest request = new ImageRequest(
                item.getImage(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
//                        Log.e("imgURLL", "onResponse: "+response);
//                       response.toString();
                        imageH.setImageBitmap(response);
                        Log.e("imgURLL", "onResponse: "+response.toString());

                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        imageH.setImageResource(R.drawable.ic_launcher_background);
                        Log.e("error2", "Image not loaded"+ error.getMessage());
                    }
                }
        );

        requestQueue.add(request);
        return listItemView;
    }

    public List<fact_sets> parseJson(JSONObject jsonObject) {
        List<fact_sets> facts = new ArrayList<>();
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("rows");

            for (int i = 0 ; i< jsonArray.length(); i++){
                try {
                    JSONObject object = jsonArray.getJSONObject(i);

                    fact_sets factss = new fact_sets(
                            object.getString("title"),
                            object.getString("description"),
                            object.getString("imageHref")
                    );
                    Log.d("imgtaggg", "parseJson: "+factss.getImage());

                    facts.add(factss);
                }catch (JSONException e){
                    Log.e("error3", "Error in parsing: "+ e.getMessage());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return facts;
    }
}

