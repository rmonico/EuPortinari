package br.zero.euportinari;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

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
    
    public void selectNameOnClick(View view) {
    	Toast.makeText(this, "selectNameOnClick", Toast.LENGTH_LONG).show();
    }
}
