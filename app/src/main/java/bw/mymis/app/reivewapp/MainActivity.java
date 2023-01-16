package bw.mymis.app.reivewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import bw.mymis.app.reivewapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
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