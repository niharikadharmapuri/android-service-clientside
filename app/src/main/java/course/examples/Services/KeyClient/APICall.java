package course.examples.Services.KeyClient;

/**
 * Created by niharika on 12/2/17.
 */

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class APICall extends Fragment{
    private TextView mQuoteView = null;
    private int mCurrIdx = -1;
    private int mQuoteArrayLen;

    private static final String TAG = "QuotesFragment";

    public int getShownIndex() {
        return mCurrIdx;
    }

    // Show the Quote string at position newIndex
    public void showQuoteAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mQuoteArrayLen)
            return;
        mCurrIdx = newIndex;
       // mQuoteView.setText(FragmentActivity.mQuoteArray[mCurrIdx]);
        //mQuoteView.setText("data returned");
        mQuoteView.setText(FragmentActivity.listsy.get(mCurrIdx));
    }

    @Override
    public void onAttach(Context activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
    }

    // Called to create the content view for this Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout defined in quote_fragment.xml
        // The last parameter is false because the returned view does not need to be attached to the container ViewGroup
        return inflater.inflate(R.layout.apicalls, container, false);
    }

    // Set up some information about the mQuoteView TextView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mQuoteView = (TextView) getActivity().findViewById(R.id.quoteView);
       // mQuoteArrayLen = FragmentActivity.mQuoteArray.length;
        mQuoteArrayLen = FragmentActivity.listsy.size();
    }

}
