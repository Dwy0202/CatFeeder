package com.example.derek.testapplication;

import android.app.Application;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.util.Set;

import io.particle.android.sdk.cloud.ParticleCloudException;
import io.particle.android.sdk.cloud.ParticleDevice;

/**
 * Created by Derek on 11/26/17.
 */

public class MyGlobalDevice {
        //implements Parcelable {
//public class MyGlobalDevice extends Application {

    private static MyGlobalDevice application;
    private ParticleDevice myDevice;

    public MyGlobalDevice(ParticleDevice device){

        myDevice = device;
    }

    public MyGlobalDevice(Parcel in){
        super();
        //readFromParcel(in);
    }

    public static final Parcelable.Creator<MyGlobalDevice> CREATOR = new
            Parcelable.Creator<MyGlobalDevice>(){
        public MyGlobalDevice createFromParcel(Parcel in){

            return new MyGlobalDevice(in);
        }

        public MyGlobalDevice[] newArray(int size){

            return new MyGlobalDevice[size];
        }
    };

    public MyGlobalDevice getInstance() {

        return application;
    }

////    @Override
////    public void onCreate() {
////        super.onCreate();
////        application = this;
//    }

    public ParticleDevice getMyDevice(){

        return myDevice;
    }

    public void setMyDevice(ParticleDevice deviceID){

        myDevice = deviceID;
    }

    public String MyGetName(){

        return myDevice.getName();
    }

    public Set<String> MyGetFunctions(){

        return myDevice.getFunctions();
    }

    public int MyCallFunction(String var){
        try {
            System.out.println("this is var: " + var);
            return myDevice.callFunction(var);
        } catch (ParticleCloudException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParticleDevice.FunctionDoesNotExistException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
