package in.studytrack.stackboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Neeraj on 19/03/17.
 */
public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.MyViewHolder> {
    private String TAG = QuestionsAdapter.class.getSimpleName();
    private Context mContext;
    private List<Question> questionList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, vote,owner;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            vote = (TextView) view.findViewById(R.id.score);
            owner = (TextView) view.findViewById(R.id.owner);


        }
    }


    public QuestionsAdapter(Context mContext, List<Question> questionList) {
        this.mContext = mContext;
        this.questionList = questionList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Question question = questionList.get(position);
        holder.title.setText(question.getTitle());
        holder.vote.setText(question.getVote());
        holder.owner.setText(question.getOwner());





        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(mContext, QuestionDetailActivity.class);
//                Log.e(TAG, "True"+ question.getUrl());
//                intent.putExtra("question", question.getSong());
//                intent.putExtra("url", question.getUrl());
//                intent.putExtra("artists", question.getArtists());
//                intent.putExtra("cover", question.getCover());
//                intent.putExtra("position", position);

                mContext.startActivity(intent);






            }
        });
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
        return questionList.size();
    }
}
