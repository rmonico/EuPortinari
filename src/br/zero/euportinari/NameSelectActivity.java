package br.zero.euportinari;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NameSelectActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_select);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_name_select, menu);
        return true;
    }
}
