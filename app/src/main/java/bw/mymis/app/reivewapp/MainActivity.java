package bw.mymis.app.reivewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import bw.mymis.app.reivewapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 提供一個共用的 Listener 處理所有的 View 事件
        ShareListener listener = new ShareListener();
        //binding.txtEmail.setOnClickListener(listener);
        //binding.txtTel.setOnClickListener(listener);
        binding.txtTel.setOnClickListener(this);
        binding.txtEmail.setOnClickListener(this);
        binding.btnPlay.setOnClickListener(this);
        binding.btnStop.setOnClickListener(this);

        // 下拉選單 設定
        // 資料部分 先使用程式宣告 一組陣列作為選項來源
        // layout simple_spinner_dropdown_item,  simple_spinner_item, simple_list_item1,
        String [] items =  { "西洋歌曲", "中文-國語歌曲" , "中文-台語歌曲" ,"兒歌"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                items);
        binding.spinngerCategory.setAdapter(adapter);


        // 亦可將資料從 SQLite中取出 後 再提供給 spinner 顯示
        // 請參閱官方文件
        // https://developer.android.com/guide/topics/ui/controls/spinner?hl=zh-tw

        //SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("songdb", MODE_PRIVATE,null);
        //Cursor cursor  = db.rawQuery("select cat_name from category", null);
        //CursorAdapter cursorAdapter = new SimpleCursorAdapter(this, cursor );
        //binding.spinngerCategory.setAdapter(cursorAdapter);
        binding.spinngerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "選取項目" + items[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ) {
            case R.id.txtTel:
                Intent telIntent = new Intent();
                telIntent.setAction( Intent.ACTION_DIAL);
                telIntent.setData(Uri.parse("tel:037-123456"));
                Toast.makeText(MainActivity.this, "您點選 電話 (2)", Toast.LENGTH_SHORT).show();
                startActivity(telIntent);
                break;
            case R.id.txtEmail:
                Intent mailIntent = new Intent();
                mailIntent.setAction( Intent.ACTION_VIEW);
                mailIntent.setData(Uri.parse("mailto: service@gmail.com"));
                startActivity(mailIntent);
                Toast.makeText(MainActivity.this, "您點選 emal (2)", Toast.LENGTH_SHORT).show();
                break;
            case R.id.txtGJun:
                Intent geoIntent = new Intent();
                geoIntent.setAction( Intent.ACTION_VIEW);
                //geoIntent.setData(Uri.parse("geo:"+24.802957 +","+ 120.973136 ));
                geoIntent.setData( Uri.parse("https://www.google.com/maps/place/新竹市東區中華路二段377號"));
                startActivity(geoIntent);
                break;
            case R.id.btnPlay:
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.q1);
                mediaPlayer.start();
                //mediaPlayer.stop();
                //mediaPlayer.pause();
                //mediaPlayer.reset();
                //mediaPlayer.isPlaying();
                break;
            case R.id.btnStop:
                // mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.q1);
                mediaPlayer.stop();
                //mediaPlayer.stop();
                //mediaPlayer.pause();
                //mediaPlayer.reset();
                //mediaPlayer.isPlaying();
                break;
        }
    }

    // 這是一個共用的 Class 然後必須實作 View.OnClickListener
    class ShareListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch ( v.getId() ) {
                case R.id.txtTel:
                    Toast.makeText(MainActivity.this, "您點選 電話", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.txtEmail:
                    Toast.makeText(MainActivity.this, "您點選 emal", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}