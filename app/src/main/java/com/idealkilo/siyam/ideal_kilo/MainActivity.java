package com.idealkilo.siyam.ideal_kilo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    EditText edit_boy;
    EditText edit_kilo;
    RadioButton rdErkek,rdKız;
    TextView text_sonuc;

    String sonuc ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_boy = (EditText) findViewById(R.id.editText);
        edit_kilo = (EditText) findViewById(R.id.editText2);
        rdErkek = (RadioButton) findViewById(R.id.radioButton3);
        rdKız = (RadioButton) findViewById(R.id.radioButton4);
        text_sonuc = (TextView) findViewById(R.id.textView8);

        MobileAds.initialize(this,"ca-app-pub-9261721980516763~7132455261");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void button1_click(View view)  {


        //virgüleri nokta yapma

        String kilo =  edit_kilo.getText().toString().replace(',','.');
        String boy =  edit_boy.getText().toString().replace(',','.');

        double dKilo=0;
        double dBoy=0;
         try
         {
             dKilo =Double.valueOf( kilo);
             dBoy =Double.valueOf( boy);
         }
         catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"Hata : Yazı İfadesi Girmeyiniz ! "+ e.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }

        if(dKilo<30 || dKilo > 180)
        {
            Toast.makeText(getApplicationContext(),"kilo 30 ile 180 arasında olmalı", Toast.LENGTH_SHORT).show();
            return;
        }
        if(dBoy<1.00 || dBoy > 2.2)
        {
            Toast.makeText(getApplicationContext(),"boy 1.00 ile 2.20 arasında olmalı", Toast.LENGTH_SHORT).show();
            return;
        }

        İnsan insan ;
        try
        {
            insan = new İnsan(dKilo,dBoy,rdErkek.isChecked());
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"Hata : "+e.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }


        sonuc +="Vücut Kitle İndex :\t\t";
        sonuc += String.valueOf(İnsan.vucutKitleIndexHesap(insan)).substring(0,4).toString(); //ilk 4 basamak alınır, substring ile (virgül dahil)
        sonuc += "\n\n";
        sonuc +="Durum :\t\t";
        sonuc += String.valueOf(insan.getDurum());
        sonuc += "\n\n";
        sonuc +="İdeal Kilonuz :\t\t";
        sonuc += String.valueOf(İnsan.idealKiloHesap(insan)).substring(0,4).toString();
        sonuc += "\n\n";
        sonuc +="Yağsız Kilonuz :\t\t";
        sonuc += String.valueOf(İnsan.yağsızKiloHesapla(insan)).substring(0,4).toString();
        sonuc += "\n\n";
        sonuc +="Vücut Yüzey Alanınız:\t\t";
        sonuc += String.valueOf(İnsan.vücutYüzeyAlanı(insan)).substring(0,4).toString();

        text_sonuc.setText( sonuc );

        sonuc="";

        edit_boy.onEditorAction(EditorInfo.IME_ACTION_DONE); // textlerle işimizin bittiğini, klavyenin kapatılacağını bildirir.
        edit_kilo.onEditorAction(EditorInfo.IME_ACTION_DONE);
    }
}
