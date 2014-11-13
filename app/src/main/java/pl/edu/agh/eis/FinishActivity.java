package pl.edu.agh.eis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class FinishActivity extends Activity {
    private static final String USER_ANSWER = "answer";
    Button finish;
    long czas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        czas = getIntent().getLongExtra("time", 0);
        setContentView(R.layout.activity_finish);
        finish = (Button) findViewById(R.id.finish_button);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                czas = System.currentTimeMillis() - czas;
                Intent intent = new Intent(getApplicationContext(), ScoreActivity.class );
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("time", czas);
                intent.putExtra(USER_ANSWER, getIntent().getStringExtra(USER_ANSWER));
                intent.putExtra("good", getIntent().getIntExtra("good",0));
                intent.putExtra("bad",getIntent().getIntExtra("bad", 0));
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finish, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
