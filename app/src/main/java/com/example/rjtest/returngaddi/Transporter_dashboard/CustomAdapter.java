
package com.example.rjtest.returngaddi.Transporter_dashboard;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.rjtest.returngaddi.R;

/**
 * Created by Fujitsu on 11/02/2017.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    private String[] pickplace;
    private String[] drpplace;
    private String[] dattext;
    private String[] vehiclename;
    private String[] costtext;
    private Activity context;

    public CustomAdapter(Context context, String[] pickplace, String[] drpplace, String[] dattext, String[] vehiclename, String[] costtext) {
        super(context, R.layout.fragment_post_fragment, pickplace);
        this.context = (Activity) context;
        this.pickplace = pickplace;
        this.drpplace = drpplace;
        this.dattext = dattext;
        this.vehiclename = vehiclename;
        this.costtext = costtext;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.fragment_post_fragment, null, true);
        TextView frmtext = (TextView) listViewItem.findViewById(R.id.frm_text);
        TextView frmplace = (TextView) listViewItem.findViewById(R.id.frm_place);
        TextView totext = (TextView) listViewItem.findViewById(R.id.to_text);
        TextView toplace = (TextView) listViewItem.findViewById(R.id.to_place);
        TextView triptext = (TextView) listViewItem.findViewById(R.id.trip_text);
        TextView vehicletxt = (TextView) listViewItem.findViewById(R.id.vehicle_text);
        TextView datetext = (TextView) listViewItem.findViewById(R.id.date_text);
        TextView vehiclenametxt = (TextView) listViewItem.findViewById(R.id.vehicle_name_text);
        TextView csttext = (TextView) listViewItem.findViewById(R.id.cost_text);
        TextView viewstatustext = (TextView) listViewItem.findViewById(R.id.viewStatus_text);
        TextView showcsttext = (TextView) listViewItem.findViewById(R.id.showcost_text);
        TextView statustext = (TextView) listViewItem.findViewById(R.id.Status_text);
        Button edtbtn = (Button) listViewItem.findViewById(R.id.edit_btn);
        Button deletebtn = (Button) listViewItem.findViewById(R.id.delete_btn);

        frmplace.setText(pickplace[position]);
        toplace.setText(drpplace[position]);
        datetext.setText(dattext[position]);
        vehiclenametxt.setText(vehiclename[position]);
        showcsttext.setText(costtext[position]);

        return listViewItem;

    }
}