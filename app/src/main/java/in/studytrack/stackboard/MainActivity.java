package in.studytrack.stackboard;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String search;
    private static Response response;
    private String TAG = MainActivity.class.getSimpleName();
private TextView title;
     private String MAIN_URL;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private QuestionsAdapter adapter;
    private List<Question> questionList;
    private ProgressDialog pDialog;
    private EditText tagged;

    private Button predict;
    private JSONObject jsarr;
    private String rs;
    private JSONObject locs;
    private JSONArray item;
    private Button more;
    private JSONObject json;
    private JSONObject ow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);



        questionList = new ArrayList<>();


        title=(TextView) findViewById(R.id.title);
        tagged=(EditText)findViewById(R.id.tagged);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new QuestionsAdapter(MainActivity.this, questionList);

        recyclerView.setAdapter(adapter);



        predict = (Button) findViewById(R.id.predict);

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view) {



                search=tagged.getText().toString();
                MAIN_URL ="https://api.stackexchange.com/2.2/search?pagesize=20&fromdate=1278028800&todate=1491436800&order=desc&sort=activity&tagged="+search+"&site=stackoverflow";

                new GetDataTask().execute();



            }
        });




    }





    class GetDataTask extends AsyncTask<Void, Void, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Please wait...");
            dialog.setMessage("Loading");
            dialog.show();


        }

        @Nullable
        @Override
        protected String doInBackground(Void... params) {


            try {
                OkHttpClient client = new OkHttpClient();




                Request request = new Request.Builder()
                        .url(MAIN_URL)
                        .build();
                response = client.newCall(request).execute();

                return response.body().string();
            } catch (@NonNull IOException e) {
                Log.e(TAG, "" + e.getLocalizedMessage());
            }


            return null;
        }

        @Override
        protected void onPostExecute(String Response) {
            super.onPostExecute(Response);
            dialog.dismiss();
json=null;
            try {
                json = new JSONObject(Response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                item = json.getJSONArray("items");

            } catch (JSONException e) {
                e.printStackTrace();
            }
           questionList.clear();
            for (int i = 0; i < item.length(); i++) {
                Question question = new Question();

                JSONObject js = null;
                try {
                    js = item.getJSONObject(i);
                    ow=js.getJSONObject("owner");
                    question.setTitle(js.getString("title"));
                    question.setVote(js.getString("score"));
                    question.setOwner(js.getJSONObject("owner").getString("display_name"));

                    questionList.add(question);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


            adapter.notifyDataSetChanged();


        }

    }



}
