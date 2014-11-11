package pl.edu.agh.eis;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuestionActivity extends Activity {
	
	private static final String TAG = "QuestionActivity";
	private static final String USER_ANSWER = "answer";
	long czas;
	String name;
	String surname;
	
	class Question{
		String ultimateQuestion = "Czy podoba Ci się broda prowadzącego?";
		String [] answers = {"Tak.", "Zdecydowanie tak.", "Sam(a) chciał(a)bym taką mieć."};
		int [] correctAnswers = {0,1,2};
		int answer = -1;
	}
	
    Question q;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		czas = System.currentTimeMillis();
		q = new Question();
		
		// Get datat passed previously
	    Bundle extras = getIntent().getExtras();
	    name = extras.getString(StartActivity.NAME);
	    surname = extras.getString(StartActivity.SURNAME);
		
		TextView questionText = (TextView) findViewById(R.id.question_text);
		RadioButton rb1 = (RadioButton) findViewById(R.id.answer1);
		RadioButton rb2 = (RadioButton) findViewById(R.id.answer2);
		RadioButton rb3 = (RadioButton) findViewById(R.id.answer3);
        Button button = (Button) findViewById(R.id.prev_button);
        Button button1 = (Button) findViewById(R.id.next_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextClick();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousClick();
            }
        });
		questionText.setText(q.ultimateQuestion);
		rb1.setText(q.answers[0]);
		rb2.setText(q.answers[1]);
		rb3.setText(q.answers[2]);
		
		Log.d(TAG, "onCreate");
	}
	public void nextClick(){
        Intent intent = new Intent(this, FinishActivity.class);
        intent.putExtra("czas", czas);
        startActivity(intent);
    }
    public void previousClick(){
        Intent intent = new Intent(this, StartActivity.class);
        intent.putExtra("czas", czas);
        startActivity(intent);
    }
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		Log.d(TAG, "onStart");
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		Log.d(TAG, "onResume");
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		Log.d(TAG,"onPause");
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG,"onStop");
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d(TAG,"onRestart");
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG,"onDestroy");
	}
	
	/**
	 *  UNCOMENT FOR SAVING/RESTORING STATE
	 *
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// Save the answer
		outState.putInt(USER_ANSWER, q.answer);
	    
	    // Always call the superclass so it can save the view hierarchy state
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// Always call the superclass so it can restore the view hierarchy
		super.onRestoreInstanceState(savedInstanceState);
		
		// Restore the answer
		q.answer = savedInstanceState.getInt(USER_ANSWER);
	}
	
	 * UNCOMENT FOR SAVING/RESTORING STATE
	 * 
	 **/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question, menu);
		return true;
	}
	
	public void recordAnswer(View view){
		boolean checked = ((RadioButton) view).isChecked();
		// Check which radio button was clicked
		switch(view.getId()) {
	        case R.id.answer1:
	            if (checked) q.answer = 0;
	            break;
	        case R.id.answer2:
	            if (checked) q.answer = 1;
	            break;
	        case R.id.answer3:
	            if (checked) q.answer = 2;
	            break;
	    }
	}
	
	public void showScores(View view){
		Log.i(TAG,Integer.toString(q.answer));
	}

}
