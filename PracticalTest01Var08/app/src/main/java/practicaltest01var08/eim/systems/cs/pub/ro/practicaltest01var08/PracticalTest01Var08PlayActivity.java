package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var08PlayActivity extends AppCompatActivity {

    private EditText riddle2;
    private EditText answer2;
    private EditText text;
    private Button check;
    private Button back;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.check) {
                if (answer2.getText().toString().equals(text.getText().toString())) {
                    setResult(RESULT_OK, null);
                } else {
                    setResult(RESULT_FIRST_USER, null);
                }
            } else if (view.getId() == R.id.back) {
                setResult(RESULT_CANCELED, null);
            }

            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_play);

        riddle2 = (EditText)findViewById(R.id.riddle2);
        answer2 = (EditText)findViewById(R.id.answer2);
        text = (EditText)findViewById(R.id.ed_test);

        check = (Button)findViewById(R.id.check);
        back = (Button)findViewById(R.id.back);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("riddle") && intent.getExtras().containsKey("answer")) {
            String clicks = intent.getStringExtra("riddle");
            riddle2.setText(String.valueOf(clicks));
            clicks = intent.getStringExtra("answer");
            answer2.setText(String.valueOf(clicks));
        }
        check = (Button)findViewById(R.id.check);
        check.setOnClickListener(buttonClickListener);
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(buttonClickListener);
    }

}
