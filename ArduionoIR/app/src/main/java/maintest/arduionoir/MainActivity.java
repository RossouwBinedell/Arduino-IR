package maintest.arduionoir;

import android.hardware.ConsumerIrManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ConsumerIrManager irManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        irManager = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);

        if (irManager.hasIrEmitter()){
            Button irGreen = (Button) findViewById(R.id.activity_main_IRGreen);
            irGreen.setOnClickListener(this);
            Button irOrange = (Button) findViewById(R.id.activity_main_IROrange);
            irOrange.setOnClickListener(this);
            Button irRed = (Button) findViewById(R.id.activity_main_IRRed);
            irRed.setOnClickListener(this);
        }else{
            Toast.makeText(this, "Unfortunately your phone does not support IR", Toast.LENGTH_LONG).show();
        }

    }

    public void onClick(View v){
        if  (v.getId()==R.id.activity_main_IRGreen){
            int freqPattern[] = {2000,27000,400,1500,500,3500,500,62200};
            irManager.transmit(21000, freqPattern );
        }
        if (v.getId()==R.id.activity_main_IROrange){
            int freqPattern[] = {2000,26000,400,3000,500,1500,500,62200};
            irManager.transmit(35000, freqPattern );
        }
        if (v.getId()==R.id.activity_main_IRRed){
            int freqPattern[] = {2000,26000,400,5000,500,5000,500,62200};
            irManager.transmit(40000, freqPattern );
        }
    }
}
