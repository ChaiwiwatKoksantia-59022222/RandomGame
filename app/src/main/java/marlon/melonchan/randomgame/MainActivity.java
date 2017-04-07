package marlon.melonchan.randomgame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor sensor;
    TextView tv;
    RelativeLayout relativeLayout;
    CountDownTimer timer;

    Boolean fin = false;
    TextView tvst;
    TextView tvsts;
    TextView tvn;
    int score = 0;
    int time_s;
    StringBuilder str = new StringBuilder();
    boolean check = true;
    RelativeLayout lay_t;
    int cn = 0;
    String type_a = "";
    String b = "";
    String v1 = "";

    ArrayList<String> data_num = new ArrayList<String>();
    ArrayList<String> data_type = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        lay_t = (RelativeLayout)findViewById(R.id.lay_text);
        tvst = (TextView)findViewById(R.id.textSt);
        tvsts = (TextView)findViewById(R.id.textSts);

        final RelativeLayout bit1 = (RelativeLayout)findViewById(R.id.bip_1);
        final RelativeLayout bit2 = (RelativeLayout)findViewById(R.id.bip_2);
        final RelativeLayout bit3 = (RelativeLayout)findViewById(R.id.bip_3);
        final RelativeLayout bit4 = (RelativeLayout)findViewById(R.id.bip_4);

        timer = new CountDownTimer(40000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_s = (int)millisUntilFinished/1000;
                if (time_s > 37){
                    tvst.setText("Reardy!?");
                }
                else if (time_s > 34 && time_s <= 37){
                    String s = Integer.toString(time_s-34);
                    tvst.setText("");
                    tvsts.setText(s);
                }
                else if (time_s == 34){
                    tvsts.setText("");
                    lay_t.setVisibility(View.INVISIBLE);
                }
                else {

                    if (time_s == 13 || time_s == 5 || time_s == 9 ) {
                        bit1.setVisibility(View.VISIBLE);
                        bit2.setVisibility(View.INVISIBLE);
                        bit3.setVisibility(View.INVISIBLE);
                        bit4.setVisibility(View.VISIBLE);
                    }
                    if (time_s == 2 || time_s == 6 || time_s == 10) {
                        bit1.setVisibility(View.INVISIBLE);
                        bit2.setVisibility(View.INVISIBLE);
                        bit3.setVisibility(View.INVISIBLE);
                        bit4.setVisibility(View.INVISIBLE);
                    }
                    if (time_s == 3 || time_s == 7 || time_s == 11){
                        bit1.setVisibility(View.INVISIBLE);
                        bit2.setVisibility(View.VISIBLE);
                        bit3.setVisibility(View.VISIBLE);
                        bit4.setVisibility(View.INVISIBLE);
                    }
                    if (time_s == 4 || time_s == 8 || time_s == 12){
                        bit1.setVisibility(View.VISIBLE);
                        bit2.setVisibility(View.VISIBLE);
                        bit3.setVisibility(View.VISIBLE);
                        bit4.setVisibility(View.VISIBLE);
                    }

                    String a = (String)tvn.getText();
                    if ( a != b && a != "PASS" && a != "CORRECT") {
                        if (cn > 0){
                            data_type.add(type_a);
                            //Toast.makeText(MainActivity.this,type_a,Toast.LENGTH_SHORT).show();
                        }
                        if (type_a == "1"){
                            score = score + 1;
                        }
                        b = a;
                        cn = cn + 1;
                        String vv = tvn.getText().toString();
                        //String sq = Integer.toString();
                        data_num.add(vv);
                        //Toast.makeText(MainActivity.this,vv,Toast.LENGTH_SHORT).show();
                    }
                    if (type_a == "2" && fin == false){
                        //Toast.makeText(MainActivity.this,"2",Toast.LENGTH_SHORT).show();
                        data_type.add(type_a);
                        fin = true;
                    }

                }

            }

            @Override
            public void onFinish() {


                timer.cancel();
                /*Intent in = new Intent(MainActivity.this,RActivity.class);
                in.putExtra("number",data_num);
                in.putExtra("type",data_type);*/
                Intent in = new Intent(MainActivity.this,ResultActivity.class);
                in.putExtra("score",score);
                startActivity(in);
                finish();
            }
        }.start();

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensor,sensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        finish();
        sensorManager.unregisterListener(this,sensor);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] value = event.values;
        int x = (int)value[0];
        final int y = (int)value[1];
        final int z = (int)value[2];

        //tv = (TextView)findViewById(R.id.tv_check);

        //tv.setText("X : "+x+"\nY : "+y+"\nZ : "+z);

        //229,57,53

        relativeLayout = (RelativeLayout)findViewById(R.id.Main_Layout);


        tvn = (TextView)findViewById(R.id.textNum);

        TextView time = (TextView)findViewById(R.id.TimeNum);
        String time_sd = Integer.toString(time_s-4);
        time.setText(time_sd);

        if (time_s == 34){
            relativeLayout.setVisibility(View.VISIBLE);
            if (check == true){
                String f = Random_number();
                tvn.setText(f);
                check = false;

            }
        }
        else if (time_s >= 4 && time_s < 34){
            if (check == true){
                String f1 = Random_number();
                tvn.setText(f1);
                check = false;

            }
        }
        else if (time_s >= 1 && time_s < 4){
            relativeLayout.setVisibility(View.INVISIBLE);
            lay_t.setVisibility(View.VISIBLE);
            tvst.setText("Time Out");
            type_a = "2";
        }


        if (z >= 8 && y >= -2 && y <= 2){
            relativeLayout.setBackgroundColor(Color.rgb(255,230,14));
            tvn.setText("PASS");
            type_a = "0";
            check = true;


        }
        else if (z <= -8 && y >= -2 && y <= 2){
            relativeLayout.setBackgroundColor(Color.rgb(100,221,23));
            tvn.setText("CORRECT");
            type_a = "1";
            check = true;

        }
        else {
            relativeLayout.setBackgroundColor(Color.rgb(68,138,255));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public String Random_number(){
        Random random = new Random();
        int num = (int)random.nextInt(99);
        String n = Integer.toString(num);
        return n;
    }


    @Override
    public void onBackPressed() {
        timer.cancel();
        super.onBackPressed();
    }
}
