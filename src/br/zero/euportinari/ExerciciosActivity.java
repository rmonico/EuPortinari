package br.zero.euportinari;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ExerciciosActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_exercicios, menu);
        return true;
    }
}
