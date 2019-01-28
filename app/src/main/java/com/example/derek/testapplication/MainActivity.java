package com.example.derek.testapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.cloud.exceptions.ParticleCloudException;
import io.particle.android.sdk.utils.Async;
import io.particle.android.sdk.utils.Toaster;



public class MainActivity extends AppCompatActivity {

    MyGlobalDevice myDevice;
    // ParticleLogin controlDev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final EditText login = (EditText) findViewById(R.id.username);
//        final EditText password = (EditText) findViewById(R.id.password);
        Button loginbtn, Plus5, Minus5, OutletON, OutletOFF, TempRequest;


        loginbtn = findViewById(R.id.login);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        new ParticleLogin().execute();


            }

        });


        //Move servo +180 units
        Plus5 = findViewById(R.id.plus5);
        Plus5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View h) {

                Toaster.s(MainActivity.this, "Open Request");

                try {
                    Async.executeAsync(myDevice.getMyDevice(), new Async.ApiWork<ParticleDevice, Integer>() {

                        public Integer callApi(ParticleDevice particleDevice) {
                            System.out.println(myDevice.MyCallFunction("setpos"));


                            return 0;
                        }

                        @Override
                        public void onSuccess(Integer value) {
                            Toaster.s(MainActivity.this, "Food Door is Open");
                        }

                        @Override
                        public void onFailure(ParticleCloudException e) {
                            Log.e("some tag", "Something went wrong making an SDK call: ", e);
                            Toaster.l(MainActivity.this, "Uh oh, something went wrong.");
                        }
                    });
                } catch (ParticleCloudException e) {
                    e.printStackTrace();
                }


            }

        });


        //Move the servo -180 units
        Minus5 = findViewById(R.id.minus5degree);
        Minus5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View P) {


                Toaster.s(MainActivity.this, "Close Request");

                try {
                    Async.executeAsync(myDevice.getMyDevice(), new Async.ApiWork<ParticleDevice, Integer>() {

                        public Integer callApi(ParticleDevice particleDevice) {
                            System.out.println(myDevice.MyCallFunction("subpos"));


                            return 0;
                        }

                        @Override
                        public void onSuccess(Integer value) {
                            Toaster.s(MainActivity.this, "Food Door is Closed");
                        }

                        @Override
                        public void onFailure(ParticleCloudException e) {
                            Log.e("some tag", "Something went wrong making an SDK call: ", e);
                            Toaster.l(MainActivity.this, "Uh oh, something went wrong.");
                        }
                    });
                } catch (ParticleCloudException e) {
                    e.printStackTrace();
                }


            }

        });

        //Activate D3 digitalWrite HIGH
        OutletON = findViewById(R.id.outlet_on);
        OutletON.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {


                Toaster.s(MainActivity.this, "Power Outlet ON Request");

                try {
                    Async.executeAsync(myDevice.getMyDevice(), new Async.ApiWork<ParticleDevice, Integer>() {

                        public Integer callApi(ParticleDevice particleDevice) {
                            System.out.println(myDevice.MyCallFunction("outletON"));


                            return 0;
                        }

                        @Override
                        public void onSuccess(Integer value) {
                            Toaster.s(MainActivity.this, "Power Outlet is ON");
                        }

                        @Override
                        public void onFailure(ParticleCloudException e) {
                            Log.e("some tag", "Something went wrong making an SDK call: ", e);
                            Toaster.l(MainActivity.this, "Uh oh, something went wrong.");
                        }
                    });
                } catch (ParticleCloudException e) {
                    e.printStackTrace();
                }


            }

        });


        //Activate D3 digitalWrite LOW
        OutletOFF = findViewById(R.id.outlet_off);
        OutletOFF.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                Toaster.s(MainActivity.this, "Power Outlet OFF Request");

                try {
                    Async.executeAsync(myDevice.getMyDevice(), new Async.ApiWork<ParticleDevice, Integer>() {

                        public Integer callApi(ParticleDevice particleDevice) {
                            System.out.println(myDevice.MyCallFunction("outletOFF"));


                            return 0;
                        }

                        @Override
                        public void onSuccess(Integer value) {
                            Toaster.s(MainActivity.this, "Power Outlet is OFF");
                        }

                        @Override
                        public void onFailure(ParticleCloudException e) {
                            Log.e("some tag", "Something went wrong making an SDK call: ", e);
                            Toaster.l(MainActivity.this, "Uh oh, something went wrong.");
                        }

                    });
                } catch (ParticleCloudException e) {
                    e.printStackTrace();
                }
            }

        });

        TempRequest = findViewById(R.id.temp_request);
        TempRequest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {

                Toaster.s(MainActivity.this, "Temperature Requested");

                try {
                    Async.executeAsync(myDevice.getMyDevice(), new Async.ApiWork<ParticleDevice, Integer>() {

                        public Integer callApi(ParticleDevice particleDevice) {
                            return myDevice.MyCallFunction("getTemp");


                        }

                        @Override
                        public void onSuccess(Integer value) {
                            Toaster.s(MainActivity.this, "Temperature is " + value + " degrees");
                        }

                        @Override
                        public void onFailure(ParticleCloudException e) {
                            Log.e("Tag", "Something went wrong with making SDK call: ", e);
                            Toaster.l(MainActivity.this, "Uh oh, something went wrong.");
                        }

                    });
                } catch (ParticleCloudException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    abstract class ParticleLogin extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(String s) {


//            This will launch to a new activity page
//            Intent i = new Intent(MainActivity.this, ControlActivity.class);
//            i.putExtra("myDevice");
//            startActivity(i);
        }


        @Override
        protected String doInBackground(String... params) {

            try {

                ParticleCloudSDK.getCloud().logIn("TestEmail@gmail.com", "123456");
                Toaster.s(MainActivity.this, "Login Successful");
                //myDevice = (MyGlobalDevice) getApplication();

                myDevice.setMyDevice(ParticleCloudSDK.getCloud().getDevice("3c004e000351353531234567"));

                String nameString = myDevice.MyGetName();
                Toaster.s(MainActivity.this, "Connected to: " + nameString);
                for (String funcName : myDevice.MyGetFunctions()) {
                    Log.i("some tag", "Device has function: " + funcName);
                    Toaster.s(MainActivity.this, "Function Calls on device: " + funcName);

                }
                Toaster.s(MainActivity.this, "Feeder is ready to use!");


            } catch (ParticleCloudException e) {
                e.printStackTrace();
                Toaster.s(MainActivity.this, "Login Failed, Please try again.");
            }

            return null;
        }

    }

}









