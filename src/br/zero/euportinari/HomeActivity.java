package br.zero.euportinari;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		TextView name = (TextView) findViewById(R.id.textView1);

		String selectedName = getSelectedName();

		name.setText(selectedName);
	}

	private String getSelectedName() {

		if ((getIntent() != null) && (getIntent().getExtras() != null))
			return (String) getIntent().getExtras().get("name");
		else
			return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

	public void onPiaoClick(View view) {
		Intent intent = new Intent(this, CameraActivity.class);

		startActivity(intent);
	}

	public void onInfoClick(View view) {
		Intent intent = new Intent(this, InfoActivity.class);

		startActivity(intent);
	}

	public void onObrasClick(View view) {
		Intent intent = new Intent(this, ObrasActivity.class);

		startActivity(intent);
	}

	public void onHomeClick(View view) {
		Intent intent = new Intent(this, HomeActivity.class);

		startActivity(intent);
	}

	public void onExerciciosClick(View view) {
		Intent intent = new Intent(this, ExerciciosActivity.class);

		startActivity(intent);
	}

	public void onAboutClick(View view) {
		Intent intent = new Intent(this, AboutActivity.class);

		startActivity(intent);
	}
}
