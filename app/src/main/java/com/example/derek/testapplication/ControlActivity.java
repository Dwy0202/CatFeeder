package com.example.derek.testapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import io.particle.android.sdk.cloud.exceptions.ParticleCloudException;
import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.utils.Async;
import io.particle.android.sdk.utils.Toaster;

public class ControlActivity extends AppCompatActivity {

    MyGlobalDevice myDevice;

    ControlActivity(MyGlobalDevice tempDevice){
        myDevice = tempDevice;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);




        Button openDoor;


        openDoor = findViewById(R.id.plus180);
        openDoor.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Toaster.s(ControlActivity.this,"Open Request");

                try {
                    Async.executeAsync(myDevice.getMyDevice(), new Async.ApiWork<ParticleDevice, Integer>() {

                        public Integer callApi(ParticleDevice particleDevice) {
                            System.out.println(myDevice.MyCallFunction("setpos"));


                            return 0;
                        }

                        @Override
                        public void onSuccess(Integer value) {
                            Toaster.s(ControlActivity.this, "Food Door is Open");
                        }

                        @Override
                        public void onFailure(ParticleCloudException e) {
                            Log.e("some tag", "Something went wrong making an SDK call: ", e);
                            Toaster.l(ControlActivity.this, "Uh oh, something went wrong.");
                        }
                    });
                } catch (ParticleCloudException e) {
                    e.printStackTrace();
                }


            }

        });
    }
}
