package pl.edu.agh.eis;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class StartActivity extends Activity {
	
	public static final String NAME = "name";
	public static final String SURNAME = "surname";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*Setting the layout*/
		setContentView(R.layout.activity_start);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}
	
	/**
	 * Called by onClick event of go_button
	 * @param view
	 */
	public void startQuiz(View view){
		/* Creatig intent */
		Intent intent = new Intent(this, QuestionActivity.class);
		
		/*Getting text from editable fields */
		EditText name = (EditText) findViewById(R.id.name_edit_text);
		EditText surname = (EditText) findViewById(R.id.surname_edit_text);
		
		/*Passing date between activities*/
		intent.putExtra(NAME, name.getText().toString());
		intent.putExtra(SURNAME, surname.getText().toString());

		/*Starting activity */
		startActivity(intent);
	}

}
