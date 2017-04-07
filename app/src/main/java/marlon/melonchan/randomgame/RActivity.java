package marlon.melonchan.randomgame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r);

        Bundle bundle = getIntent().getExtras();
        ArrayList<String> num = bundle.getStringArrayList("number");
        ArrayList<String> type = bundle.getStringArrayList("type");

        String[] str = new String[num.size()];
        int[] num_st = new int[type.size()];


        for (int j = 0 ; j < type.size() ; j++ ){
            String sp = type.get(j);
            int o = Integer.parseInt(sp);
            if (o == 0 || o  == 2){
                //Toast.makeText(this,type.get(j),Toast.LENGTH_SHORT).show();
                num_st[j] = R.drawable.cancel;

                //num_st[j] = 0;
            }
            else if (o == 1){
                //Toast.makeText(this,type.get(j),Toast.LENGTH_SHORT).show();
                num_st[j] = R.drawable.checked;
                //num_st[j] = 1;
            }
        }

        for (int i = 0 ; i < num.size() ; i++ ){
            str[i] = num.get(i);
        }

        CustomListView adapter = new CustomListView(this,str,num_st);
        ListView listView = (ListView)findViewById(R.id.listViews);
        listView.setAdapter(adapter);


    }

}
