package javis.wearsyncservice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by AshleyTheMagnificant on 4/23/16.
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private static String[] mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(final View v){
            super(v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DashboardAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DashboardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_score_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        vh.mTextView = (TextView) v.findViewById(R.id.overall_score);
        return vh;
    }

    //     Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        String value = mDataset[position];
        // TODO strip data [overallscore_horizontals_verticles_circulars]
        // TODO set the card color here (alternate between 2 colors)
        String[] vals = mDataset[position].split("_");
        int overalls = Integer.parseInt(vals[0]);
        int hs = Integer.parseInt(vals[1]);
        int vs = Integer.parseInt(vals[2]);
        int cs = Integer.parseInt(vals[3]);

        holder.mTextView.setText("86");
        int width = holder.mTextView.getWidth();
        holder.mTextView.setWidth(width*(hs/100));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

}



