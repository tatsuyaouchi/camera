package jp.ac.shohoku.hguremetal.tese;
//AndroididX
//import android.supper.v7.app.CompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homegamen);


        Button sendButton3 = findViewById(R.id.zukannbutton);
        sendButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SubActivity3.class);
                startActivity(intent);
            }
        });


    }
}
