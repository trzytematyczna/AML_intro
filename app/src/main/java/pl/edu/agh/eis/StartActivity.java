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
    private String name;
    private String surname;
    EditText nameEdit;
    EditText surnameEdit;
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("name", name);
        outState.putString("surname", surname);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            name = savedInstanceState.getString("name");
            surname = savedInstanceState.getString("surname");
//            nameEdit.setText(name);
//            surnameEdit.setText(surname);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
	 * Called by onClick event of go_button
	 * @param view
	 */
	public void startQuiz(View view){
		/* Creatig intent */
		Intent intent = new Intent(this, QuestionActivity.class);
		
		/*Getting text from editable fields */
		nameEdit = (EditText) findViewById(R.id.name_edit_text);
		surnameEdit = (EditText) findViewById(R.id.surname_edit_text);
		
		/*Passing date between activities*/
        name = nameEdit.getText().toString();
        surname = surnameEdit.getText().toString();
		intent.putExtra(NAME, name);
		intent.putExtra(SURNAME, surname);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
      	/*Starting activity */
		startActivity(intent);
	}

}
