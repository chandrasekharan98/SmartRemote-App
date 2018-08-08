package info.androidhive.bottomnavigation;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.davidmiguel.numberkeyboard.NumberKeyboard;
import com.davidmiguel.numberkeyboard.NumberKeyboardListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class KeypadActivity extends AppCompatActivity implements NumberKeyboardListener{
    private static final int MAX_ALLOWED_AMOUNT = 99999;
    private TextView amountEditText;
    private int amount;
    private NumberFormat nf = NumberFormat.getInstance();

    private List<ChannelSearch> channels = new ArrayList<>();
    private RecyclerView recyclerView;
    private ChannelSearchAdapter csAdapter;

    String[] channelName= new String[]{"Movies Now","HBO","Times Now","NDTV","Discovery","Nat GEO","Vijay TV"};
    String[] channelNumber= new String[]{"423","324","573","889","825","570","193"};
    String searchTerm="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keypad);
       // otherActivity();
        amountEditText = findViewById(R.id.amount);
        NumberKeyboard numberKeyboard = findViewById(R.id.numberKeyboard);
        numberKeyboard.setListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        csAdapter=new ChannelSearchAdapter(this,channels);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(csAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ChannelSearch search = channels.get(position);
                Toast.makeText(getApplicationContext(), "Changing to: "+search.getChannelname(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        populateSearch();
    }

    @Override
    public void onNumberClicked(int number) {
        int newAmount = (int) (amount * 10.0 + number);
        if (newAmount <= MAX_ALLOWED_AMOUNT) {
            amount = newAmount;
            showAmount();
        }
    }

    @Override
    public void onLeftAuxButtonClicked() {
        // Nothing to do
    }

    @Override
    public void onRightAuxButtonClicked() {
        amount = (int) (amount / 10.0);
        showAmount();
    }

    private void showAmount() {
        amountEditText.setText(nf.format(amount));
        searchTerm=Integer.toString(amount);
        populateSearch();
    }

    public void populateSearch(){
        if(searchTerm.length()!=0){
            channels.clear();
            for(int i=0;i<channelName.length;i++){
                if(channelNumber[i].indexOf(searchTerm)!=-1){
                    ChannelSearch search=new ChannelSearch(channelName[i],channelNumber[i]);
                    channels.add(search);
                }
            }
            csAdapter.notifyDataSetChanged();
        }
    }
}
