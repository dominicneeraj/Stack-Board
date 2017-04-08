package in.studytrack.stackboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Neeraj on 19/03/17.
 */
public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.MyViewHolder> {
    private String TAG = AnswersAdapter.class.getSimpleName();
    private Context mContext;
    private List<Answer> answerList;
    private String htmlData;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public WebView title;

        public MyViewHolder(View view) {
            super(view);
            title = (WebView) view.findViewById(R.id.title);



        }
    }


    public AnswersAdapter(Context mContext, List<Answer> answerList) {
        this.mContext = mContext;
        this.answerList = answerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answer_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Answer answer = answerList.get(position);
        htmlData = "<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" /> <style type=\"text/css\">\n" +

                ".answer accepted-answer {width:10%;}\n" +
                "p {margin-left:20px;}\n" +

                "</style></head>" + answer.getLink();
        holder.title.loadDataWithBaseURL("file:///android_assets/.", htmlData, "text/html", "UTF-8", null);
        Log.e(TAG, htmlData );








    }

//    /**
//     * Showing popup menu when tapping on 3 dots
//     */
//    private void showPopupMenu(View view) {
//        // inflate menu
//        PopupMenu popup = new PopupMenu(mContext, view);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.menu_song, popup.getMenu());
//        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
//        popup.show();
//    }

    /**
     * Click listener for popup menu items
     */
//    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
//
//        public MyMenuItemClickListener() {
//        }
//
//        @Override
//        public boolean onMenuItemClick(MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.action_add_favourite:
//                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.action_play_next:
//                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
//                    return true;
//                default:
//            }
//            return false;
//        }
//    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }
}
