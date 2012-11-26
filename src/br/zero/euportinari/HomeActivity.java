package br.zero.euportinari;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends Activity {

	private static String selectedName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		TextView name = (TextView) findViewById(R.id.textView1);
		
		name.setTypeface(NameSelectActivity.strangeLoveFont);
		name.setTextSize(TypedValue.COMPLEX_UNIT_PT, 24);
		
		name.setTextColor(NameSelectActivity.nameColor);
		
		if (selectedName == null)
			selectedName = getSelectedName();

		name.setText(selectedName);
	}

	private String getSelectedName() {

		if ((getIntent() != null) && (getIntent().getExtras() != null))
			return (String) getIntent().getExtras().get("name");
		else
			return null;
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
		Intent intent = new Intent(this, Exercicio1Activity.class);

		startActivity(intent);
	}

	public void onAboutClick(View view) {
		Intent intent = new Intent(this, AboutActivity.class);

		startActivity(intent);
	}
}
