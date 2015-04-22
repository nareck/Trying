package com.test.narek.trymemoryleaks;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


public class HomeActivity extends Activity {

    private Map<String, String> hadMap;

    private Handler handler = new Handler();
    private TextView textView;

    private Runnable task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        textView = (TextView) findViewById(R.id.hello_text);
        handler.postDelayed(task = new Runnable() {
            @Override
            public void run() {
                textView.setText("Changed World");
                System.out.println("inside the runnable:true");
            }
        }, 1000 * 1000);

        genData();
    }

    private void genData() {
        hadMap = new HashMap<String, String>();
        for (int i = 0; i < 10000; i++) {
            hadMap.put(new String("asdf_" + i), new String("qwer_" + i));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(task);

        System.out.println("^^^^^^^^^ onDestroy() called");
    }

}
