package br.zero.euportinari;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BackCameraActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_camera);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_back_camera, menu);
        return true;
    }
}
