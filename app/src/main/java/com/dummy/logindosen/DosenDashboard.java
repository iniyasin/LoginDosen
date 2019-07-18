package com.dummy.logindosen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class DosenDashboard extends AppCompatActivity{

    public static TextView tv_result;
    public Button btn_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_dashboard);

        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_qr = (Button) findViewById(R.id.btn_qr);

        btn_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DosenDashboard.this, ScanActivity.class);
                startActivity(intent);
            }
        });
    }
}
