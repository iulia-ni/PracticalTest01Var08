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

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {
    EditText riddleEditText;
    EditText answerEditText;
    EditText attemptEditText;
    Button checkButton;
    Button backButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.check_button:
                    if (answerEditText.getText().toString().equals(attemptEditText.getText().toString())) {
                        Toast.makeText(PracticalTest01Var02PlayActivity.this, "Correct", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(PracticalTest01Var02PlayActivity.this, "Incorrect", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.back_button:
                    setResult(RESULT_OK, null);
                    finish();
                    break;
            }
        }
    }

    private IntentFilter intentFilter = new IntentFilter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);
        riddleEditText = findViewById(R.id.second_edit_text1);
        answerEditText = findViewById(R.id.second_edit_text2);
        attemptEditText = findViewById(R.id.second_edit_text3);
        checkButton =  findViewById(R.id.check_button);
        backButton =  findViewById(R.id.back_button);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("riddle")) {
            String riddle = intent.getStringExtra("riddle");
            String answer = intent.getStringExtra("answer");
            riddleEditText.setText(riddle);
            answerEditText.setText(answer);
        }
        checkButton.setOnClickListener(buttonClickListener);
        backButton.setOnClickListener(buttonClickListener);
        intentFilter.addAction("hint");
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
            String message = intent.getStringExtra("message");
            Log.d("[Service]", message);
            Toast.makeText(PracticalTest01Var02PlayActivity.this, "Hint = " + message, Toast.LENGTH_SHORT).show();
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
