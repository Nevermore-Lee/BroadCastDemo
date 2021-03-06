package nevermore.io.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetChangeBroadCast netChangeBroadCast;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netChangeBroadCast = new NetChangeBroadCast();
        registerReceiver(netChangeBroadCast,intentFilter);
        bt = (Button) findViewById(R.id.bt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netChangeBroadCast);
    }

    public void doClick(View view){
        switch(view.getId()){
            case R.id.bt:
                Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
                sendBroadcast(intent);
                break;
        }

    }

    public static class MyBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"recevied in MyBroadCastReceiver", Toast.LENGTH_SHORT).show();
        }
    }

    class  NetChangeBroadCast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null&&networkInfo.isAvailable()){
                Toast.makeText(MainActivity.this, "network is available", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
