package info.androidhive.bottomnavigation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class WhatsOnTvActivity extends AppCompatActivity {
    private ActionBar toolbar;
    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter;
    private ArrayList<Game> games;
    private String[] tvchannel=new String[]{"Star Sports","Ten Sports","Neo Sports","DD Sports","Set Max"};
    private String[] program=new String[]{"Cricket- IND vs SA PayTm cup","Football- LALIGA Live","Chennai Open Live","London Olympics Preview","Women Hockey- IND vs ENG"};
    private String[] channelno=new String[]{"401","402","403","404","405"};
    private TextView ch,pr,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_on_tv);

        ch=(TextView)findViewById(R.id.channelname);
        pr=(TextView)findViewById(R.id.progname);
        no=(TextView)findViewById(R.id.progamnumber);
        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle("Home");
        navigation.setSelectedItemId(R.id.navigation_category);

        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        settingDummyData();
        adapter = new CoverFlowAdapter(this, games);
        coverFlow.setAdapter(adapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Home");
                    Intent i=new Intent(WhatsOnTvActivity.this, HomeActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_category:
                    toolbar.setTitle("What's on TV");

                    return true;
                case R.id.navigation_remote:
                    toolbar.setTitle("Remote");
                    Intent i2=new Intent(WhatsOnTvActivity.this, RemoteActivity.class);
                    startActivity(i2);

                    return true;
                case R.id.navigation_wishlist:
                    toolbar.setTitle("wish list");
                    Intent i1=new Intent(WhatsOnTvActivity.this, BannerActivity.class);
                    startActivity(i1);
                    return true;
                case R.id.navigation_camera:
                    toolbar.setTitle("Camera");
                    Intent i3=new Intent(WhatsOnTvActivity.this, CameraActivity.class);
                    startActivity(i3);
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                ch.setText(tvchannel[position]);
                pr.setText(program[position]);
                no.setText("Channel: "+channelno[position]);
            }

            @Override
            public void onScrolling() {
                Log.i("MainActivity", "scrolling");
            }
        };
    }

    private void settingDummyData() {
        games = new ArrayList<>();
        games.add(new Game(R.drawable.cricket_backdrop, "Cricket"));
        games.add(new Game(R.drawable.football_backdrop, "FootBall"));
        games.add(new Game(R.drawable.tennis_backdrop, "Tennis"));
        games.add(new Game(R.drawable.wrestling_backdrop, "Wrestling"));
        games.add(new Game(R.drawable.hockey_backdrop, "Hockey"));

    }

}
