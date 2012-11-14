package br.zero.euportinari;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ObrasActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_obras);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_obras, menu);
		return true;
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
