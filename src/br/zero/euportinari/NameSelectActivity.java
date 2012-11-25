package br.zero.euportinari;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;

public class NameSelectActivity extends Activity {

	public static Typeface strangeLoveFont;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_name_select);

		EditText editTextName = (EditText) findViewById(R.id.editTextName);

		Context context = getApplicationContext();
		strangeLoveFont = Typeface.createFromAsset(context.getAssets(),
				"fonts/strangelovenextnarrow.ttf");
		editTextName.setTypeface(strangeLoveFont);
		editTextName.setTextSize(TypedValue.COMPLEX_UNIT_PT, 12);
	}

	public void selectNameOnClick(View view) {
		Intent intent = new Intent(this, HomeActivity.class);

		intent.putExtra("name", getName());

		startActivity(intent);
	}

	private String getName() {
		EditText name = (EditText) findViewById(R.id.editTextName);

		return name.getText().toString();
	}
}
