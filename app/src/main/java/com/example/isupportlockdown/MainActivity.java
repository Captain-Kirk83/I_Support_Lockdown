package com.example.isupportlockdown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    int a=5;
    TableLayout tl;
    Button add;
    Button send;
    TextInputLayout shn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tl=(TableLayout)findViewById(R.id.table);
        tl.setStretchAllColumns(true);
        add=(Button)findViewById(R.id.addrow);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableRow tr;
                tr = new TableRow(MainActivity.this);
                TableRow.LayoutParams params1 = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT, 11f);
                tr.setLayoutParams(params1);
                TextView t=new TextView(MainActivity.this);
                a++;
                t.setText(String.valueOf(a));
                t.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                t.setLayoutParams(params2);
                tr.addView(t);
                EditText e1=new EditText(MainActivity.this);
                e1.findViewById(R.id.item5);
                TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 8f);
                e1.setLayoutParams(params3);
                EditText e2=new EditText(MainActivity.this);
                e2.findViewById(R.id.q5);
                TableRow.LayoutParams params4 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2f);
                e2.setLayoutParams(params4);
                tr.addView(e1);
                tr.addView(e2);
                tl.addView(tr);
            }
        });
        shn=(TextInputLayout)findViewById(R.id.shopno);
        send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout name=(TextInputLayout)findViewById(R.id.name);
                TextInputLayout add=(TextInputLayout)findViewById(R.id.address);
                int c=0;String message=name.getEditText().getText().toString()+"\n"+add.getEditText().getText().toString();
                for(int i=1;i<=a;i++)
                {
                    TableRow row = (TableRow)tl.getChildAt(i);
                        EditText e1 = (EditText) row.getChildAt(1);
                    EditText e2 = (EditText) row.getChildAt(2);// get child index on particular row
                        String str = e1.getText().toString();
                        if (str.isEmpty()){
                            continue;
                        }
                        else{
                            c++;
                            str=Integer.toString(c)+"."+str+"-"+e2.getText().toString();
                        }
                        message=message+"\n"+str;


                }
                String no="91"+shn.getEditText().getText().toString();
                share(message,no);
            }

            private void share(String message, String no) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,message);
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                sendIntent.putExtra("jid", no + "@s.whatsapp.net");
                startActivity(sendIntent);
            }
        });
    }
}
