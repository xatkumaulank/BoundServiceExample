package com.example.boundserviceexample;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyBoundService extends Service {

    private MyBinder mMyBinder = new MyBinder();
    private MediaPlayer mMediaPlayer;

    public class MyBinder extends Binder{
        MyBoundService getMyBoundService(){
            return MyBoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyBoundService","onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMyBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("MyBoundService","onUnbind");
        return super.onUnbind(intent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyBoundService","onDestroy");
        if (mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void startMusic(){
        if (mMediaPlayer == null){
            mMediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.my_music);
        }
        mMediaPlayer.start();
    }
}
