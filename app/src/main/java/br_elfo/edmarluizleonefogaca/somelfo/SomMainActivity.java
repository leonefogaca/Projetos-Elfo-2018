package br_elfo.edmarluizleonefogaca.somelfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


public class SomMainActivity extends AppCompatActivity {
    private SoundPool soundPool;
    private int sound1, sound2, sound3, sound4, sound5, sound6;
    private int sound3StreamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_som_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }

        sound1 = soundPool.load(this, R.raw.sound1, 1);
        sound2 = soundPool.load(this, R.raw.sound2, 1);
        sound3 = soundPool.load(this, R.raw.sound3, 1);
        sound4 = soundPool.load(this, R.raw.sound3, 1);
        sound5 = soundPool.load(this, R.raw.sound1, 1);
        sound6 = soundPool.load(this, R.raw.sound6, 1);
    }

    public void playSound(View v) {
        switch (v.getId()) {
            case R.id.button_sound1:
                soundPool.play(sound1, 1, 1, 0, 0, 1);
                //soundPool.pause(sound3StreamId);
                soundPool.autoPause();
                break;
            case R.id.button_sound2:
                soundPool.play(sound2, 1, 1, 0, 0, 1);
                break;
            case R.id.button_sound3:
                sound3StreamId = soundPool.play(sound3, 1, 1, 0, 0, 1);
                break;
            case R.id.button_sound4:
                soundPool.play(sound4, 1, 1, 0, 0, 1);
                break;
            case R.id.button_sound5:
                soundPool.play(sound5, 1, 1, 0, 0, 1);
                break;
            case R.id.button_sound6:
                soundPool.play(sound6, 1, 1, 0, 0, 1);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.rotate) {
            //rotating image
            //imageView.setOrientation((imageView.getOrientation() + 90) % 360);
        }else{
            if (item.getItemId() == R.id.about) {
                TextView content = (TextView) getLayoutInflater().inflate(R.layout.about_view, null);
                content.setMovementMethod(LinkMovementMethod.getInstance());
                content.setText(Html.fromHtml(getString(R.string.about_body)));
                new AlertDialog.Builder(this)
                        .setTitle(R.string.about)
                        .setView(content)
                        .setInverseBackgroundForced(true)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
            }

        }

        return super.onOptionsItemSelected(item);
    }
}