package com.example.alejandro.calculadoraip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alejandro.calculadoraip.Classes.Calc;


public class Main extends AppCompatActivity {

    private EditText txtIp = null;
    private EditText txtNetmask = null;

    private TextView lblNetId = null;
    private TextView lblBroadcast = null;
    private TextView lblHostQty = null;
    private TextView lblHosts = null;
    private TextView lblNetmask = null;

    private Calc calc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtIp = (EditText) findViewById(R.id.txt_ip);
        this.txtNetmask = (EditText) findViewById(R.id.txt_mask);

        this.lblNetId = (TextView) findViewById(R.id.lbl_net_id);
        this.lblBroadcast = (TextView) findViewById(R.id.lbl_broadcast);
        this.lblHostQty = (TextView) findViewById(R.id.lbl_hosts_qty);
        this.lblHosts = (TextView) findViewById(R.id.lbl_host);
        this.lblNetmask = (TextView) findViewById(R.id.lbl_netmask);
    }

    public void calculate (View view) {
        this.calc = new Calc(this.txtIp.getText().toString(), Integer.parseInt(this.txtNetmask.getText().toString()));

        String netmask[] = this.calc.geInfo();
        this.lblNetmask.setText("Netmask: " + netmask[0]);
        this.lblHosts.setText("Wildcard: " + netmask[1]);
    }
}
