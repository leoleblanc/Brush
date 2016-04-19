package javis.wearsyncservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class InstructionActivity extends WearableActivity {

    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private RelativeLayout mContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("InstructionActivity", "oncreate");
        setContentView(R.layout.activity_instruction);

        mContainerView = (RelativeLayout) findViewById(R.id.container);


        // set GridView
        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
        pager.setAdapter(new BrushGridViewPagerAdapter(this));
        Log.d("InstructionActivity", "oncreate");


    }




    // GridView
    private class BrushGridViewPagerAdapter extends GridPagerAdapter {
        final Context mContext;

        public BrushGridViewPagerAdapter(Context context) {
            Log.d("InstructionActivity", "initiated");
            mContext = context;
        }


        @Override
        public int getRowCount() {
            return 1;
        }

        @Override
        public int getColumnCount(int i) {
            return 4;
        }

        @Override
        public Object instantiateItem(ViewGroup viewGroup, int row, int col) {
            Log.d("InstructionActivity", "running");
            final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.inst_grid_view_pager, viewGroup, false);
            final Button button = (Button) view.findViewById(R.id.button);

            button.setVisibility(View.GONE);
            if (col == 0) {
                view.setBackgroundResource(R.drawable.switch_hand);
            } else if (col == 1) {
                view.setBackgroundResource(R.drawable.side);
            } else if (col == 2) {
                view.setBackgroundResource(R.drawable.inst_c);
            } else if (col == 3) {
                view.setBackgroundResource(R.drawable.timer);
                button.setVisibility(View.VISIBLE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("TAG", "clicked");
                        Intent i = new Intent(InstructionActivity.this, TimerActivity.class);
                        startActivity(i);
                    }
                });
            }
            Log.d("InstructionActivity", "successful");
            viewGroup.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup viewGroup, int row, int col, Object obj) {
            viewGroup.removeView((View) obj);
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view==obj;
        }
    }






}
