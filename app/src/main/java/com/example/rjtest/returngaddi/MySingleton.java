
package com.example.rjtest.returngaddi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Fujitsu on 09/02/2017.
 */

public class MySingleton  {

    private static  MySingleton minstance;
    private RequestQueue requestQueue;
    private  static Context mctx;


    private MySingleton(Context context)
    {
        mctx = context;
        requestQueue = getRequestQueue();

    }

    public static synchronized MySingleton getTnstance(Context context)
    {
             if(minstance==null)
             {
                 minstance = new MySingleton(context);
             }
        return minstance;
    }



    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue = Volley.newRequestQueue(mctx.getApplicationContext());
        }
      return requestQueue;
    }

    public<T> void addTorequestQueue(Request<T> request)
    {
         requestQueue.add(request);
    }

}
