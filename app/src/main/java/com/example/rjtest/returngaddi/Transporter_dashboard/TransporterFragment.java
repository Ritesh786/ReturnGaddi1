
package com.example.rjtest.returngaddi.Transporter_dashboard;




import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rjtest.returngaddi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransporterFragment extends Fragment implements View.OnClickListener {
    private Button mcreatebtn, mpostbtn,msearchbtn,mbookingstatus;

    public TransporterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transporter,
                container, false);
        // return inflater.inflate(R.layout.fragment_transporter, container, false);
        mcreatebtn = (Button) view.findViewById(R.id.button3);
        mpostbtn = (Button) view.findViewById(R.id.mypst_btn);
        msearchbtn = (Button)view.findViewById(R.id.search_btn);
        mbookingstatus = (Button)view.findViewById(R.id.bookingstatus_btn);
        msearchbtn.setOnClickListener(this);
        mcreatebtn.setOnClickListener(this);
        mpostbtn.setOnClickListener(this);
        mbookingstatus.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
            CreatePostFrag fragment = new CreatePostFrag();
            FragmentManager manager = getFragmentManager();
            manager.beginTransaction().replace(R.id.frame_trans, fragment).addToBackStack("Create Post").commit();
          break;
            case R.id.mypst_btn:
                MyPostFrag fragment1 = new MyPostFrag();
                FragmentManager manager1 = getFragmentManager();
                manager1.beginTransaction().replace(R.id.frame_trans, fragment1).addToBackStack("My Post").commit();
                break;
             case R.id.search_btn:
                 SearchLoadFrag fragment2 = new SearchLoadFrag();
                 FragmentManager manager2 = getFragmentManager();
                 manager2.beginTransaction().replace(R.id.frame_trans, fragment2).addToBackStack("Search Load").commit();
                 break;
            case R.id.bookingstatus_btn:
                BookingStatutsFrag fragment3 = new BookingStatutsFrag();
                FragmentManager manager3 = getFragmentManager();
                manager3.beginTransaction().replace(R.id.frame_trans, fragment3).addToBackStack("Booking status").commit();
                break;
            default:
                break;




        }
    }
}