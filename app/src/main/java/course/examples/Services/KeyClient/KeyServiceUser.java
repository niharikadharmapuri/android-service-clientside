package course.examples.Services.KeyClient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.List;
import course.examples.Services.KeyCommon.KeyGenerator;
import static java.lang.Thread.sleep;


public class KeyServiceUser extends Activity {

    TextView txt;
    EditText editText, editText1;
    Button api1, api2, api3, bind, unbind;
    static final List<String> lists1 = new ArrayList<>();
    static final List<String> lists2 = new ArrayList<>();
    protected static final String TAG = "ServiceUser";
    private KeyGenerator mKeyGeneratorService;
    private boolean mIsBound = false;
    String status;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        txt = (TextView) findViewById(R.id.tex);
        editText1 = (EditText) findViewById(R.id.user_input1);
        editText=(EditText)findViewById(R.id.user_input);



        //calling  api 1 method on click of api1 button
        api1 = (Button) findViewById(R.id.api1);
        api1.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                editText = (EditText) findViewById(R.id.user_input);
                final int year_value = Integer.parseInt(editText.getText().toString());

                Runnable api1_Runnable = new Runnable() {
                    @Override
                    public void run() {

                        if (mIsBound)
                            try {
                            //calling the api method
                                final List<String> z = mKeyGeneratorService.api1(year_value);
                            //converting array list to a string
                                StringBuilder sb = new StringBuilder();
                                for (String s : z)
                                {
                                    sb.append(s);
                                    sb.append("\t");
                                }

                                final String result1 = sb.toString();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //displaying the retrieved data on Ui for our convenience
                                        txt.setText(result1);
                                    }
                                });
                                lists1.add("api1" + " " + year_value);
                                lists2.add(result1);

                            } catch (Exception e) {

                            }
                    }
                };
                Thread api1_Thread = new Thread((api1_Runnable));
                api1_Thread.start();

            }
        });

        api2 = (Button) findViewById(R.id.api2);
        api2.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                String date=(editText1.getText()).toString();
                //splitting the date and no. of working days
                String [] dateParts = date.split("/");
                String day = dateParts[0];
                String month = dateParts[1];
                String year = dateParts[2];
                String wdays=dateParts[3];

                final int day1 =Integer.parseInt(day);
                final int month1 =Integer.parseInt(month);
                final int year1 =Integer.parseInt(year);
                final int wdays1 =Integer.parseInt(wdays);

                Runnable api2_Runnable = new Runnable() {
                    @Override
                    public void run() {

                        if (mIsBound)
                            try {
                                //calling the api method
                                final List<String> z = mKeyGeneratorService.api2(day1,month1,year1,wdays1);
                                //converting array list to a string
                                StringBuilder sb = new StringBuilder();
                                for (String s : z)
                                {
                                    sb.append(s);
                                    sb.append("\t");
                                }
                                final String result2 = sb.toString();


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        txt.setText(result2);

                                    }
                                });
                                lists1.add("api2" + " "+day1+month1+year1+wdays1 );
                                lists2.add(result2);

                            } catch (Exception e) {

                            }
                    }
                };
                Thread api2_Thread = new Thread((api2_Runnable));
                api2_Thread.start();

            }

        });

        api3 = (Button) findViewById(R.id.api3);
        api3.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.user_input);

                final int year_value = Integer.parseInt(editText.getText().toString());

                Runnable api3_Runnable = new Runnable() {
                    @Override
                    public void run() {

                        if (mIsBound)
                            try {
                            //calling the api method
                                final String result3 = mKeyGeneratorService.api3(year_value);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        txt.setText(result3);
                                    }
                                });
                                lists1.add("api3" + " " + year_value);
                                lists2.add(result3);

                            } catch (Exception e) {

                            }
                    }
                };
                Thread api3_Thread = new Thread((api3_Runnable));
                api3_Thread.start();

            }

        });


         bind = (Button) findViewById(R.id.bind);
        bind.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                editText = (EditText) findViewById(R.id.user_input);

                if (mIsBound)
                    try {
                        status = "Service bound and running";
                        //calling the api method status and sending the status to be displayed by the server app
                        String p = mKeyGeneratorService.status(status);
                        Toast.makeText(getApplicationContext(), "service is bound", Toast.LENGTH_SHORT).show();

                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
            }
        });

        unbind = (Button) findViewById(R.id.unbind);
        unbind.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if (mIsBound) {
                    status = "Service is unbound!";
                    try {
                        //calling the api method status
                        String j = mKeyGeneratorService.status(status);
                    } catch (Exception e) {

                    }
                    unbindService(mConnection);
                    Toast.makeText(getApplicationContext(), "service is unbound", Toast.LENGTH_SHORT).show();
                }

            }
        });

       /* // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();*/


        final Button activity = (Button) findViewById(R.id.activity);
        activity.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FragmentActivity.class);
                startActivity(intent);
            }
        });
    }

    // Bind to KeyGenerator Service
    @Override
    protected void onResume() {
        super.onResume();

        if (!mIsBound) {
            boolean b = false;
            Intent intent = new Intent(KeyGenerator.class.getName());
            // Must make intent explicit or lower target API level to 19.
            ResolveInfo info = getPackageManager().resolveService(intent,Context.BIND_AUTO_CREATE );
            intent.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));

            //binding the service
            b = bindService(intent, this.mConnection, Context.BIND_AUTO_CREATE);
            if (b) {
                Log.i(TAG, " bindService() succeeded!");
            } else {
                Log.i(TAG, " bindService() failed!");
            }

        }
    }

    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder iservice) {

            mKeyGeneratorService = KeyGenerator.Stub.asInterface(iservice);

            mIsBound = true;
            status = "Service bound but idle!";
            try {
                //calling the api method status
                String p = mKeyGeneratorService.status(status);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName className) {

            mKeyGeneratorService = null;

            mIsBound = false;

        }
    };


}