package br.zero.euportinari;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PresentationActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_presentation);
	}

	public void continuarOnClick(View view) {
		Intent intent = new Intent(this, NameSelectActivity.class);

		startActivity(intent);
	}
}
