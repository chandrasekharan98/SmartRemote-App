package info.androidhive.bottomnavigation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.content.Context;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;


import com.bumptech.glide.Glide;
import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.WaveDetector;

import java.util.ArrayList;
import java.util.Locale;


public class RemoteActivity extends AppCompatActivity implements SensorEventListener{

    private ActionBar toolbar;
    ImageView chplus, chminus, upButton, downButton, leftButton, rightButton,menu,mute,numeric,sensor,mike,power;
    int currVolume;
    SeekBar volumeBar;

    //sensor variables
    private SensorManager senSensorManager;
    private Sensor senAccelerometer,magnetometer;
    float[] mGravity;
    float[] mGeomagnetic;
    float azimuth, pitch, roll;
    float azimuth1,pitch1,roll1;
    int flag=0,sensorFlag=0,x=0,masterflag=0, onflag=1;

    // Text to Speech Variables
    TextToSpeech t1;
    private static final int REQ_CODE_SPEECH_INPUT = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = getSupportActionBar();
        setContentView(R.layout.activity_remote);
        chplus = (ImageView) findViewById(R.id.chplus);
        chminus = (ImageView) findViewById(R.id.chminus);
        upButton = (ImageView) findViewById(R.id.upButton);
        downButton = (ImageView) findViewById(R.id.downButton);
        leftButton = (ImageView) findViewById(R.id.leftButton);
        rightButton = (ImageView) findViewById(R.id.rightButton);
        menu = (ImageView) findViewById(R.id.menu);
        mute = (ImageView) findViewById(R.id.mute);
        numeric = (ImageView) findViewById(R.id.numeric);
        sensor = (ImageView) findViewById(R.id.sensor);
        volumeBar = (SeekBar) findViewById(R.id.volumebar);
        mike = (ImageView) findViewById(R.id.mike);
        power = (ImageView) findViewById(R.id.power);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle("Smart Remote");
        navigation.setSelectedItemId(R.id.navigation_remote);

        // Text to Speech
        mike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });


        //sensor init
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensey.getInstance().init(getApplicationContext());
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer=senSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
        senSensorManager.registerListener(this, magnetometer , SensorManager.SENSOR_DELAY_NORMAL);
        Sensey.getInstance().startWaveDetection(waveListener);

        //for ch+ button
        chplus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int pointerIndex = motionEvent.getActionIndex();
                int pointerId = motionEvent.getPointerId(pointerIndex);
                int maskedAction = motionEvent.getActionMasked();
                switch (maskedAction) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN: {
                        // TODO use data
                        Log.d("tag", "pressed");
                        chplus.setImageResource(R.drawable.chpluspressed);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        // TODO use data
                        Log.d("tag", "release");
                        chplus.setImageResource(R.drawable.chplus);
                        break;
                    }
                }
                return true;
            }
        });

        //for ch_ button
        chminus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int pointerIndex = motionEvent.getActionIndex();
                int pointerId = motionEvent.getPointerId(pointerIndex);
                int maskedAction = motionEvent.getActionMasked();
                switch (maskedAction) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN: {
                        // TODO use data
                        Log.d("tag", "pressed");
                        chminus.setImageResource(R.drawable.chminuspressed);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        // TODO use data
                        Log.d("tag", "release");
                        chminus.setImageResource(R.drawable.chminus);
                        break;
                    }
                }
                return true;
            }
        });

        //for up button
        upButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int pointerIndex = motionEvent.getActionIndex();
                int pointerId = motionEvent.getPointerId(pointerIndex);
                int maskedAction = motionEvent.getActionMasked();
                switch (maskedAction) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN: {
                        // TODO use data
                        Log.d("tag", "pressed");
                        upButton.setImageResource(R.drawable.buttonpressed);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        // TODO use data
                        Log.d("tag", "release");
                        upButton.setImageResource(R.drawable.button);
                        break;
                    }
                }
                return true;
            }
        });

        //for right button
        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int pointerIndex = motionEvent.getActionIndex();
                int pointerId = motionEvent.getPointerId(pointerIndex);
                int maskedAction = motionEvent.getActionMasked();
                switch (maskedAction) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN: {
                        // TODO use data
                        Log.d("tag", "pressed");
                        rightButton.setImageResource(R.drawable.buttonpressed);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        // TODO use data
                        Log.d("tag", "release");
                        rightButton.setImageResource(R.drawable.button);
                        break;
                    }
                }
                return true;
            }
        });

        //for down button
        downButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int pointerIndex = motionEvent.getActionIndex();
                int pointerId = motionEvent.getPointerId(pointerIndex);
                int maskedAction = motionEvent.getActionMasked();
                switch (maskedAction) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN: {
                        // TODO use data
                        Log.d("tag", "pressed");
                        downButton.setImageResource(R.drawable.buttonpressed);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        // TODO use data
                        Log.d("tag", "release");
                        downButton.setImageResource(R.drawable.button);
                        break;
                    }
                }
                return true;
            }
        });


        //for left button
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int pointerIndex = motionEvent.getActionIndex();
                int pointerId = motionEvent.getPointerId(pointerIndex);
                int maskedAction = motionEvent.getActionMasked();
                switch (maskedAction) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN: {
                        // TODO use data
                        Log.d("tag", "pressed");
                        leftButton.setImageResource(R.drawable.buttonpressed);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        // TODO use data
                        Log.d("tag", "release");
                        leftButton.setImageResource(R.drawable.button);
                        break;
                    }
                }
                return true;
            }
        });


        //for menu button
        menu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int pointerIndex = motionEvent.getActionIndex();
                int pointerId = motionEvent.getPointerId(pointerIndex);
                int maskedAction = motionEvent.getActionMasked();
                switch (maskedAction) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN: {
                        // TODO use data
                        Log.d("tag", "pressed");
                        menu.setImageResource(R.drawable.menupressed);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        // TODO use data
                        Log.d("tag", "release");
                        menu.setImageResource(R.drawable.menu);
                        break;
                    }
                }
                return true;
            }
        });


        //for numeric button
        numeric.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int pointerIndex = motionEvent.getActionIndex();
                int pointerId = motionEvent.getPointerId(pointerIndex);
                int maskedAction = motionEvent.getActionMasked();
                switch (maskedAction) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN: {
                        // TODO use data
                        Log.d("tag", "pressed");
                        numeric.setImageResource(R.drawable.numericpressed);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        // TODO use data
                        Log.d("tag", "release");
                        numeric.setImageResource(R.drawable.numeric);
                        Intent i=new Intent(RemoteActivity.this, KeypadActivity.class);
                        startActivity(i);
                        break;
                    }
                }
                return true;
            }
        });

        //for sensor
        sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0) {
                    sensor.setImageResource(R.drawable.sensoron);
                    pitch1 = pitch;
                    roll1 = roll;
                    flag = 1;
                }
                else if(flag == 1) {
                    sensor.setImageResource(R.drawable.sensoroff);
                    flag = 0;
                }
            }
        });
/*
if(flag==0) {
            pitch1 = pitch;
            roll1 = roll;
            flag = 1;
        }
       else
            flag=0;

* */

        //for mute
        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sensorFlag == 0) {
                    mute.setImageResource(R.drawable.mutepressed);
                    currVolume = volumeBar.getProgress();
                    volumeBar.setProgress(0);
                    sensorFlag = 1;
                }
                else if(sensorFlag == 1) {
                    mute.setImageResource(R.drawable.mute);
                    volumeBar.setProgress(currVolume);
                    sensorFlag = 0;
                }
            }
        });



        //for power
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onflag == 1) {
                    texttospeech("Switching TV Off");
                    onflag = 0;
                }
                else if(onflag == 0) {
                    texttospeech("Switching TV ON");
                    onflag = 1;
                }
            }
        });

       volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar volumeBar, int i, boolean b) {
               if( i == 0 && b == true) {
                   if(sensorFlag == 1) {
                       mute.setImageResource(R.drawable.mute);
                       sensorFlag = 0;
                   }
       }

           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });

    }



    WaveDetector.WaveListener waveListener=new WaveDetector.WaveListener() {
        @Override public void onWave() {
            onandoff();
        }
    };
    public void onandoff()
    {
        if(flag==1) {
            if (onflag == 1) {
                Toast.makeText(getApplicationContext(), "Switching TV Off.", Toast.LENGTH_SHORT).show();
                texttospeech("Switching TV Off");
                onflag = 0;
            } else {
                Toast.makeText(getApplicationContext(), "Switching TV On.", Toast.LENGTH_SHORT).show();
                texttospeech("Switching TV On");
                onflag = 1;
            }
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Home");
                    Intent i=new Intent(RemoteActivity.this, HomeActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_category:
                    toolbar.setTitle("What's on TV");
                    Intent i2=new Intent(RemoteActivity.this, WhatsOnTvActivity.class);
                    startActivity(i2);
                    return true;
                case R.id.navigation_remote:
                    toolbar.setTitle("Remote");

                    return true;
                case R.id.navigation_wishlist:
                    toolbar.setTitle("wish list");
                    Intent i1=new Intent(RemoteActivity.this, BannerActivity.class);
                    startActivity(i1);
                    return true;
                case R.id.navigation_camera:
                    toolbar.setTitle("Camera");
                    Intent i3=new Intent(RemoteActivity.this, CameraActivity.class);
                    startActivity(i3);
                    return true;
            }

            return false;
        }
    };
    public void texttospeech(String result) {
        t1.speak(result, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        senSensorManager.unregisterListener(this);
        Sensey.getInstance().stopWaveDetection(waveListener);
    }

    protected void onDestroy() {
        super.onDestroy();
        Sensey.getInstance().stopWaveDetection(waveListener);
        senSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;
        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float orientationData[] = new float[3];
                SensorManager.getOrientation(R, orientationData);
                azimuth = orientationData[0];
                pitch = orientationData[1];
                roll = orientationData[2];
                if(flag==1)
                {
                    if(roll<roll1-0.8){
                        senSensorManager.unregisterListener(this);
                        backchannel();
                    }
                    if (roll > roll1 + 0.4) {
                        senSensorManager.unregisterListener(this);
                        nextchannel();
                    }
                }

            }
        }

    }
    public void nextchannel()
    {
        Toast.makeText(getApplicationContext(), "Next Channel", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                reListenSensor();
            }
        }, 2000);
    }
    public void backchannel()
    {
        Toast.makeText(getApplicationContext(), "Previous Channel", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                reListenSensor();
            }
        }, 2000);
    }


    public void reListenSensor(){
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        senSensorManager.registerListener(this, magnetometer , SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    if (result.get(0).contains("volume")) {
                        if (result.get(0).contains("increase") || (result.get(0).contains("up"))) {
                            texttospeech("Volume increased");
                        } else if (result.get(0).contains("decrease") || (result.get(0).contains("down"))) {
                            texttospeech("Volume decreased");
                        }
                    }
                    else if ((result.get(0).contains("power")) || (result.get(0).contains("switch"))) {
                        if (result.get(0).contains("on"))
                            texttospeech("switching TV on");
                        else
                            texttospeech("switching TV off");
                    }

                    else if ((result.get(0).contains("go to")) || (result.get(0).contains("change")) || (result.get(0).contains("open"))) {
                        if (result.get(0).contains("Romedy Now"))
                            texttospeech(" Opening Romedy Now");
                        if (result.get(0).contains("HBO"))
                            texttospeech(" Opening HBO");
                        if (result.get(0).contains("Z cafe"))
                            texttospeech(" Opening Z cafe");
                        if (result.get(0).contains("Comedy Central"))
                            texttospeech(" Opening Comedy Central");
                    }
                    else {
                        texttospeech("Sorry please repeat again");
                    }
                }
            }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
