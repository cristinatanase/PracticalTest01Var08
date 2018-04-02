package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    EditText riddle;
    EditText answer;
    Button play;
    private int serviceStatus = Constants.SERVICE_STOPPED;
    private IntentFilter intentFilter = new IntentFilter();
    Random random = new Random();

    private PlayClickListener playClickListener = new PlayClickListener();
    private class PlayClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.Play && !riddle.getText().toString().isEmpty() && !answer.getText().toString().isEmpty()) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08PlayActivity.class);
                String riddle1 = riddle.getText().toString();
                String answer1 = answer.getText().toString();
                intent.putExtra("riddle", riddle1);
                intent.putExtra("answer", answer1);
                startActivityForResult(intent, 1);
            }

            String leftNumberOfClicks = riddle.getText().toString();


            if (serviceStatus == Constants.SERVICE_STOPPED) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
                intent.putExtra("firstNumber", leftNumberOfClicks);
                getApplicationContext().startService(intent);
                serviceStatus = Constants.SERVICE_STARTED;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        riddle = (EditText)findViewById(R.id.riddle);
        answer = (EditText)findViewById(R.id.answer);
        play = (Button)findViewById(R.id.Play);

        play.setOnClickListener(playClickListener);

        EditText usernameEditText = (EditText)findViewById(R.id.riddle);
        if ((savedInstanceState != null) && (savedInstanceState.getString(Constants.TEXT1) != null)) {
            usernameEditText.setText(savedInstanceState.getString(Constants.TEXT1));
        }

        EditText passwordEditText = (EditText)findViewById(R.id.answer);
        if ((savedInstanceState != null) && (savedInstanceState.getString(Constants.TEXT2) != null)) {
            passwordEditText.setText(savedInstanceState.getString(Constants.TEXT2));
        }

        intentFilter.addAction("a");


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(resultCode == RESULT_OK) {
            Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show();
        }
        if(resultCode == RESULT_FIRST_USER) {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        EditText username = (EditText) findViewById(R.id.riddle);
        savedInstanceState.putString(Constants.TEXT1, String.valueOf(username.getText()));
        EditText password = (EditText) findViewById(R.id.answer);
        savedInstanceState.putString(Constants.TEXT2, String.valueOf(password.getText()));

        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var08Service.class);
        stopService(intent);
        super.onDestroy();
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
