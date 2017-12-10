package course.examples.Services.KeyClient;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FragmentActivity extends Activity implements
        ListCalls.ListSelectionListener {

   // public static String[] mTitleArray;
    //public static String[] mQuoteArray;
    private APICall mDetailsFragment;

    public static List<String> listsx = KeyServiceUser.lists1;
    public static List<String> listsy = KeyServiceUser.lists2;



    private static final String TAG = "QuoteViewerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        setContentView(R.layout.activity_fragment);

        // Get a reference to the QuotesFragment
        mDetailsFragment =
                (APICall) getFragmentManager().findFragmentById(R.id.details);
    }

    // Implement ListSelectionListener interface
    // Called by TitlesFragment when the user selects an item in the TitlesFragment
    @Override
    public void onListSelection(int index) {
        if (mDetailsFragment.getShownIndex() != index) {

            // Tell the QuoteFragment to show the quote string at position index
            mDetailsFragment.showQuoteAtIndex(index);
        }
    }

}
