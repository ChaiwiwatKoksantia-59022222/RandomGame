package marlon.melonchan.randomgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.CountDownTimer;

import java.util.Random;


public class LaunchActivity extends AppCompatActivity {

    CountDownTimer timer;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    RelativeLayout bg_id;
    TextView tv_id;
    int time_c;
    int t1s = 0;
    int count_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        bg_id = (RelativeLayout)findViewById(R.id.s_bg_btn_id);
        tv_id = (TextView)findViewById(R.id.s_btn_id);

        RelativeLayout jump = (RelativeLayout)findViewById(R.id.jump_btn);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        /*jump.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });*/

        final Random random = new Random();

        final int infi = 1000000000;

        t1 = (TextView)findViewById(R.id.t1);
        t2 = (TextView)findViewById(R.id.t2);
        t3 = (TextView)findViewById(R.id.t3);
        t4 = (TextView)findViewById(R.id.t4);
        t5 = (TextView)findViewById(R.id.t5);

        timer = new CountDownTimer(infi*100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //จับเวลา
                String time = String.format("%d",((int)millisUntilFinished/1000)-1);
                int time_i = (int)millisUntilFinished/1000;

                time_c = time_i;

                time_i = (infi/10)-time_i;
                String s = Integer.toString(time_i);

                Start_Random_number();

                if (time_i%3 == 0){
                    int r1 = random.nextInt(20);
                    String str = Integer.toString(r1);
                    t1.setText(str);
                }


            }

            @Override
            public void onFinish() {
            }
        }.start();


    }

    public void Start_Random_number() {
        final Random random = new Random();
        String r1 = Integer.toString(random.nextInt(99));
        String r2 = Integer.toString(random.nextInt(99));
        String r3 = Integer.toString(random.nextInt(99));
        String r4 = Integer.toString(random.nextInt(99));
        String r5 = Integer.toString(random.nextInt(99));

        t1.setText(r1);
        t2.setText(r2);
        t3.setText(r3);
        t4.setText(r4);
        t5.setText(r5);

    }

}
