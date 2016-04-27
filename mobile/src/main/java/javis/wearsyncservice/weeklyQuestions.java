package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/16/16.
 */
public class weeklyQuestions extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_questions);
    }

    public void toLearnMore(View v) {
        Intent next = new Intent(this, learnMore.class);
        startActivity(next);
    }
}
