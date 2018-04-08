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
    private TextView lblHostPart = null;
    private TextView lblHosts = null;
    private TextView lblNetmask = null;
    private TextView lblNetPart = null;

    private Calc calc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtIp = (EditText) findViewById(R.id.txt_ip);
        this.txtNetmask = (EditText) findViewById(R.id.txt_mask);

        this.lblNetId = (TextView) findViewById(R.id.lbl_net_id);
        this.lblBroadcast = (TextView) findViewById(R.id.lbl_broadcast);
        this.lblHostPart = (TextView) findViewById(R.id.lbl_hosts_part);
        this.lblHosts = (TextView) findViewById(R.id.lbl_host);
        this.lblNetmask = (TextView) findViewById(R.id.lbl_netmask);
        this.lblNetPart = (TextView) findViewById(R.id.lbl_net_part);
    }

    public void calculate (View view) {
        this.calc = new Calc(this.txtIp.getText().toString(), Integer.parseInt(this.txtNetmask.getText().toString()));

        String info[] = this.calc.geInfo();

        this.lblNetmask.setText("Netmask: " + info[0]);
        this.lblHosts.setText("Wildcard: " + info[1]);
        this.lblNetId.setText("Network: " + info[2]);
        this.lblBroadcast.setText("Broadcast: " + info[3]);
        this.lblHostPart.setText("Hosts: " + info[4]);
        this.lblNetPart.setText("Nets: " + info[5]);
    }
}
