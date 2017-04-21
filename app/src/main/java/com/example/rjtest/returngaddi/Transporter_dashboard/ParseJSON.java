
package com.example.rjtest.returngaddi.Transporter_dashboard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fujitsu on 12/02/2017.
 */

public class ParseJSON
{
    public static String[] pickplace;
    public static String[] drpplace;
    public static String[] dattext;
    public static String[] vehiclename;
    public static String[] costtext;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_PICKUP = "pickup";
    public static final String KEY_DROP = "drop";
    public static final String KEY_DATE = "date";
    public static final String KEY_VEHICLE = "vehicle";
    public static final String KEY_COST = "cost";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            pickplace = new String[users.length()];
            drpplace = new String[users.length()];
            dattext = new String[users.length()];
            vehiclename =  new String[users.length()];
            costtext = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                pickplace[i] = jo.getString(KEY_PICKUP);
                drpplace[i] = jo.getString(KEY_DROP);
                dattext[i] = jo.getString(KEY_DATE);
                vehiclename[i] = jo.getString(KEY_VEHICLE);
                costtext[i] = jo.getString(KEY_COST);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

