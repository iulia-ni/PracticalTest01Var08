package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {
    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    EditText editText1;
    EditText editText2;
    Button playButton;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.play_button:
                    String riddle = editText1.getText().toString();
                    String answer = editText2.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02PlayActivity.class);
                    if (!riddle.isEmpty() && !answer.isEmpty()) {
                        intent.putExtra("riddle", riddle);
                        intent.putExtra("answer", answer);
                        startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);

                        Intent intentService = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
                        intentService.putExtra("answer", answer);
                        getApplicationContext().startService(intentService);
                    }

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        editText1 = findViewById(R.id.edit_text1);
        editText2 = findViewById(R.id.edit_text2);
        playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(buttonClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            //Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("ed1", editText1.getText().toString());
        savedInstanceState.putString("ed2", editText2.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("ed1")) {
            editText1.setText(savedInstanceState.getString("ed1"));
        } else {
            editText1.setText("");
        }

        if (savedInstanceState.containsKey("ed1")) {
            editText2.setText(savedInstanceState.getString("ed1"));
        } else {
            editText2.setText("");
        }
    }

}
