package com.example.examplevolleyapi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private static final String URL = "http://148.66.133.246:8043/api/user/branch_Statuslist?Status=Active";
    public Spinner spinner;
    public EditText textview,password;
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        spinner = findViewById (R.id.spinnerid);
        textview = findViewById (R.id.Nameid);
        password = findViewById (R.id.Password);
        button = findViewById (R.id.Buttonid);

        button.setOnClickListener (view ->
                                           URL ( ));
    }
    private void URL() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest (Request.Method.GET, URL, null, this::parseJson, Throwable::printStackTrace);
        Volley.newRequestQueue (this).add (jsonArrayRequest);
        System.out.println (jsonArrayRequest);
    }
    private void parseJson(JSONArray response){
            ArrayList<branches> branchesList = new ArrayList<> ( );
            try {
                for (int i = 0; i < response.length ( ); i++) {
                    JSONObject obj = response.getJSONObject (i);
                    String branch_id = obj.getString ("BRANCH_ID");
                    String branch_name = obj.getString ("Branch_Name");
                    branchesList.add (new branches (branch_id, branch_name));
                }
                initSpinner (branchesList);
            } catch (JSONException e) {
                Log.d(URL, response.toString());
               //Log.d(URL, "someOtherMethod()", e);
                System.out.println ("hello");
            }
    }
    private void initSpinner(final ArrayList<branches> branchesList) {
        if (branchesList.size ( ) > 0) {
            ArrayAdapter<branches> adapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, branchesList);
            spinner.setAdapter (adapter);
            spinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    branches branches = branchesList.get(position);
                    String meta ="\nBRANCH_ID: " + branches.getBranch_id ()+ "\nNAME: " +"Branch_name: " + branches.getBranch_name ();
                    textview.setText (meta);
                }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
            });
        }
    }
}
