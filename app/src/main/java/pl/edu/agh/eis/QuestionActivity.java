package pl.edu.agh.eis;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
	AlertDialog dialog;
	class Question{
		String ultimateQuestion = "Czy podoba Ci się broda prowadzącego?";
		String [] answers = {"Tak.", "Zdecydowanie tak.", "Sam(a) chciał(a)bym taką mieć."};
		public int [] correctAnswers = {0,1};
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
        intent.putExtra("time", czas);
        intent.putExtra("good", goodAnsw());
        intent.putExtra("bad", badAnsw());
        startActivity(intent);
    }
    public void previousClick(){
        Intent intent = new Intent(this, StartActivity.class);
        intent.putExtra("time", czas);
        intent.putExtra(USER_ANSWER, q.answer);
//        Log.d("AAA",goodAnsw()+"");
//        Log.d("AAA",badAnsw()+"");

        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG,"onPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG,"onStop");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG,"onRestart");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG,"onDestroy");
	}
	

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// Save the answer
		outState.putInt(USER_ANSWER, q.answer);
	    
	    // Always call the superclass so it can save the view hierarchy state
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState   (Bundle savedInstanceState) {
		// Always call the superclass so it can restore the view hierarchy
		super.onRestoreInstanceState(savedInstanceState);
		
		// Restore the answer
		q.answer = savedInstanceState.getInt(USER_ANSWER);
	}
	/**
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
    public int goodAnsw(){
        for(int i : q.correctAnswers){
            if(i == q.answer){
                return q.answer;
            }
        }
        return 0;
    }
    public int badAnsw(){
        for(int i : q.correctAnswers){
            if(i == q.answer){
                return 0;
            }
        }
        return 1;
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);//getApplicationContext());
        ab.setCancelable(false);
        ab.setTitle("?");
        ab.setMessage("U sure? U will loose ur answers")
          .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    finish();
                }
          })
          .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
                    dialog.dismiss();
                }
          });
        AlertDialog alertdialog = ab.create();
        alertdialog.show();
    }

    public void showScores(View view){
		Log.i(TAG,Integer.toString(q.answer));
	}

}
