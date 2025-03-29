package com.as.instagram;
 
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

public class MainActivity extends Activity { 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		FloaterModMenu.Start(this);
		//startService(new Intent(this, FloaterModMenu.class));
    }
}
