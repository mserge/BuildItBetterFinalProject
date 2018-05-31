package info.markovy.jokeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String PARAM_JOKE = "info.markovy.jokeandroid.PARAM_JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        TextView textView = findViewById(R.id.textViewJoke);
        if(intent != null){
            String stringExtra = intent.getStringExtra(PARAM_JOKE);
            if(stringExtra != null) {
                textView.setText(stringExtra);
            } else {
                textView.setText(R.string.no_joke);
            }
        } else {
            textView.setText(R.string.empty_intent);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
