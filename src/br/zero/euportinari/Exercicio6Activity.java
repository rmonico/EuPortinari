package br.zero.euportinari;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Exercicio6Activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio6);
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
