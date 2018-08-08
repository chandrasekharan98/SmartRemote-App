package info.androidhive.bottomnavigation;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        otherActivity();
    }
    public void otherActivity(){
        Intent i4=new Intent(Intent.ACTION_MAIN);

        PackageManager manager = getPackageManager();

        i4 = manager.getLaunchIntentForPackage("com.dinesh.ar");//apk name

        i4.addCategory(Intent.CATEGORY_LAUNCHER);

        startActivity(i4);
    }
}
