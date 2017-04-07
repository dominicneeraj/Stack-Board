package in.studytrack.stackboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.okhttp.Response;

/**
 * Created by Neeraj on 3/19/2017.
 */

public class QuestionDetailActivity extends AppCompatActivity {
    private static Response response;
    static final String MAIN_URL = "http://starlord.hackerearth.com/edfora/cokestudio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }




}

