package br.zero.euportinari;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class HomeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        TextView name = (TextView) findViewById(R.id.TextView1);
        
        String selectedName = getSelectedName();
        
        name.setText(selectedName);
    }

    private String getSelectedName() {
		return (String) getIntent().getExtras().get("name");
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }
}
