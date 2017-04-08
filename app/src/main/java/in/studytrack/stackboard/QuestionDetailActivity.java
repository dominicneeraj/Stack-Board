package in.studytrack.stackboard;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neeraj on 3/19/2017.
 */

public class QuestionDetailActivity extends AppCompatActivity {
    private static Response response;
    private String link;

    private TextView txt;
    private android.webkit.WebView mywebview;
    private String TAG = QuestionDetailActivity.class.getSimpleName();
    private String htmlData;
    private String MAIN_URL;
    private String question_id;
    private JSONObject json;
    private List<Answer> answerList;
    private JSONArray item;
    private Document document;
    private AnswersAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        if (extras!= null) {

          link= extras.getString("link");
            question_id= extras.getString("question_id");

        }
        answerList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new AnswersAdapter(QuestionDetailActivity.this, answerList);

        recyclerView.setAdapter(adapter);

        MAIN_URL ="https://api.stackexchange.com/2.2/questions/"+question_id+"/answers?order=desc&sort=activity&site=stackoverflow";
        new GetDataTask().execute();

        new getContent().execute();






    }

    class GetDataTask extends AsyncTask<Void, Void, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(QuestionDetailActivity.this);
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
            answerList.clear();
            for (int i = 0; i < item.length(); i++) {
                Answer answer = new Answer();

                JSONObject js = null;
                try {
                    js = item.getJSONObject(i);

                   answer.setId(js.getString("answer_id"));


                    answerList.add(answer);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }





        }

    }



    // Title AsyncTask
    private class getContent extends AsyncTask<Object, Object, Document> {
        String title;
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(QuestionDetailActivity.this);
            dialog.setTitle("Please wait...");
            dialog.setMessage("Loading");
            dialog.show();


        }

        @Override
        protected Document doInBackground(Object... params) {
            try {

                 document = Jsoup.connect(link).get();

//                htmlData = document.getElementById("answer-43284244").outerHtml();
return document;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Document Response) {
            super.onPostExecute(Response);
            dialog.dismiss();
           for(int i=0;i<answerList.size();i++)
           {
               htmlData = Response.getElementById("answer-"+answerList.get(i).getId()).outerHtml();
               answerList.get(i).setLink(htmlData);

           }


            adapter.notifyDataSetChanged();


        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();

        if (i == android.R.id.home) {

            finish();
            return true;

        }

        else {
            return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
  
}