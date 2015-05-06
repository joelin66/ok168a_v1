package ex.ok168a_v1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;


public class ScoreCounter extends ActionBarActivity {
    boolean  flag_toggle;
    long escapeTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_counter);
        // init timer
        final Chronometer timer = (Chronometer)findViewById(R.id.timer);
        // init bottom
        final Button TMR_TOGGLE = (Button)findViewById(R.id.tmr_btn);
        TMR_TOGGLE.setOnClickListener(new Button.OnClickListener(){
            @Override
            // 開始計數
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (flag_toggle) {
                    flag_toggle = false;
                    TMR_TOGGLE.setBackgroundColor(Color.RED);

                    TMR_TOGGLE.setText(R.string.tmr_stop);
                    escapeTime = timer.getBase() - SystemClock.elapsedRealtime();

                    timer.stop();
                }
                else
                {
                    flag_toggle = true;
                    TMR_TOGGLE.setBackgroundColor(Color.GREEN);
                    TMR_TOGGLE.setText(R.string.tmr_start);
                    timer.setBase(SystemClock.elapsedRealtime() + escapeTime);
                    timer.start();
                }
            }});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score_counter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// action bar settting
        switch (R.id.opt_settings) {
            case R.id.opt_settings:

                switch (item.getItemId())
                {
                    case R.id.opt_sub1_30:
                        item.setChecked(true);
                        return true;
                    case R.id.opt_sub2_25:
                        item.setChecked(true);
                        return true;
                    case R.id.opt_sub3_20:
                        item.setChecked(true);
                        return true;
                    case R.id.opt_sub4_15:
                        item.setChecked(true);
                        return true;
                    default:
                        return super.onOptionsItemSelected(item);
                }


            case R.id.action_quit:
                new AlertDialog.Builder(this)
                        .setTitle("離開程式")
                        .setMessage("確定離開?")
                        .setPositiveButton(" 是 ",new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which)
                            {
                                //onBackPressed();
                                finish();
                            }
                        })
                        .setNegativeButton("否，返回程式", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
