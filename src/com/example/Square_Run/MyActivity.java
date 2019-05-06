package com.example.Square_Run;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity {
    long best = 0,coins=0;
    boolean isShieldBought=false,isDoubleCoinsBought=false;
    public static final int GETRESULT = 0;
    public static final String BESTCOUNTER = "myBestCounter",COINCOUNTER = "myCoinCounter",BUYEDTEXTURES="myBuyedTextures",
            ISSHIELDBOUGHTKEY="myIsShieldBoughtKey",ISDOUBLECOINSBOUGHTKEY="myIsDoubleCoinsBoughtKey";
    public static final String KEYOUT = "myBestKeyOut";
    SharedPreferences mySettings;


    private MyTextureInfo textureInfo = new MyTextureInfo();
    public boolean[] isBuyed;
    int nowTextureNumber=0;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        isBuyed= new boolean[textureInfo.kolvo];
        mySettings = getSharedPreferences(KEYOUT, Context.MODE_PRIVATE);
        if (mySettings.contains(BESTCOUNTER)) {
            best = mySettings.getLong(BESTCOUNTER, 0);
            ((TextView) findViewById(R.id.text)).setText(String.format("Ваш рекорд = %d", best));
        } else {
            best = 0;
        }
        if (mySettings.contains(COINCOUNTER)) {
            coins = mySettings.getLong(COINCOUNTER, 0);
            ((TextView) findViewById(R.id.coinsText)).setText(String.format("У вас %d монет(a/ы)", coins));
        } else {
            coins = 0;
        }
        if (mySettings.contains(BUYEDTEXTURES+"0")) {
            for(int i=0;i<textureInfo.kolvo;i++){
                isBuyed[i]=mySettings.getBoolean(String.format(BUYEDTEXTURES+"%d",i),false);
            }
        }else{
            isBuyed=textureInfo.defaultIsBuyed;
        }
        if (mySettings.contains(ISSHIELDBOUGHTKEY)) {
            isShieldBought=mySettings.getBoolean(ISSHIELDBOUGHTKEY,false);
        }else{
            isShieldBought=false;
        }
        if (mySettings.contains(ISDOUBLECOINSBOUGHTKEY)) {
            isDoubleCoinsBought=mySettings.getBoolean(ISDOUBLECOINSBOUGHTKEY,false);
        }else{
            isDoubleCoinsBought=false;
        }



        ((TextView) findViewById(R.id.texturesText)).setText(String.format(textureInfo.names[nowTextureNumber]+"\n %d монет",textureInfo.prices[nowTextureNumber]));
        if(isBuyed[nowTextureNumber]) {
            findViewById(R.id.button).setBackgroundColor(Color.GREEN);
        }else{
            findViewById(R.id.button).setBackgroundColor(Color.RED);
        }

        //ExtraActivity exAct = new ExtraActivity();
        //findViewById(R.id.frameLayout).
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isBuyed[nowTextureNumber]) {
                    Intent intent = new Intent(MyActivity.this, ExtraActivity.class);
                    //intent.putExtra(KEYOUT, best);
                    intent.putExtra(ExtraActivity.TEXTURESKEY,nowTextureNumber);
                    intent.putExtra(ExtraActivity.STARTSHIELDKEY,isShieldBought);
                    isShieldBought=false;


                    startActivityForResult(intent, GETRESULT);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Выбранные текстуры недоступны", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowTextureNumber++;
                if(nowTextureNumber>textureInfo.kolvo-1)
                    nowTextureNumber=0;
                ((TextView) findViewById(R.id.texturesText)).setText(String.format(textureInfo.names[nowTextureNumber]+"\n %d монет",textureInfo.prices[nowTextureNumber]));
                if(isBuyed[nowTextureNumber]) {
                    findViewById(R.id.button).setBackgroundColor(Color.GREEN);
                }else{
                    findViewById(R.id.button).setBackgroundColor(Color.RED);
                }
            }
        });
        findViewById(R.id.buttonPrevious).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowTextureNumber--;
                if(nowTextureNumber<0)
                    nowTextureNumber=textureInfo.kolvo-1;
                ((TextView) findViewById(R.id.texturesText)).setText(String.format(textureInfo.names[nowTextureNumber]+"\n %d монет",textureInfo.prices[nowTextureNumber]));
                if(isBuyed[nowTextureNumber]) {
                    findViewById(R.id.button).setBackgroundColor(Color.GREEN);
                }else{
                    findViewById(R.id.button).setBackgroundColor(Color.RED);
                }
            }
        });
        findViewById(R.id.buttonBuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isBuyed[nowTextureNumber]&&(coins>=textureInfo.prices[nowTextureNumber])){
                    coins-=textureInfo.prices[nowTextureNumber];
                    isBuyed[nowTextureNumber]=true;
                    findViewById(R.id.button).setBackgroundColor(Color.GREEN);
                    ((TextView) findViewById(R.id.coinsText)).setText(String.format("У вас %d монет(a/ы)", coins));
                }else{
                    if(isBuyed[nowTextureNumber]){
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Текстуры уже приобретены", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Недостаточно монет", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });
//        findViewById(R.id.cheat).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                coins+=50;
//            }
//        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GETRESULT) {
            if (resultCode == RESULT_OK) {
                long result = data.getLongExtra(ExtraActivity.BESTRESULTKEY, GETRESULT);
                if (best < result) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Рекорд побит!!!", Toast.LENGTH_LONG);
                    toast.show();
                    best=result;
                }
                ((TextView) findViewById(R.id.text)).setText(String.format("Ваш результат = %d, Ваш рекорд = %d",result, best));
                if(isDoubleCoinsBought){
                    coins+=data.getLongExtra(ExtraActivity.COINKEY,GETRESULT)*2;
                    isDoubleCoinsBought=false;
                }else{
                    coins+=data.getLongExtra(ExtraActivity.COINKEY,GETRESULT);
                }

                ((TextView) findViewById(R.id.coinsText)).setText(String.format("У вас %d монет(a/ы)", coins));
            }
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = mySettings.edit();
        editor.putLong(BESTCOUNTER, best);
        editor.putLong(COINCOUNTER,coins);
        editor.putBoolean(ISSHIELDBOUGHTKEY,isShieldBought);
        editor.putBoolean(ISDOUBLECOINSBOUGHTKEY,isDoubleCoinsBought);
        for(int i=0;i<textureInfo.kolvo;i++){
            editor.putBoolean(String.format(BUYEDTEXTURES+"%d",i),isBuyed[i]);
        }
        editor.apply();
    }


    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(0,0,0,"Buy shield(15)");//будь внимателен при смене цены!
        menu.add(0,1,1,"Buy double coins(6)");
        menu.add(0,2,2,"Help");
        return true;
    }
int selectedAction= -1;
    public boolean onOptionsItemSelected(MenuItem item){
        selectedAction=item.getItemId();
        switch (selectedAction) {
            case 0:{
                if(!isShieldBought&&(coins>=15)){
                    coins-=15;
                    isShieldBought=true;
                    ((TextView) findViewById(R.id.coinsText)).setText(String.format("У вас %d монет(a/ы)", coins));
                }else{
                    if(isShieldBought){
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Щит уже приобретен", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Недостаточно монет", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                break;
            }
            case 1:{
                if(!isDoubleCoinsBought&&(coins>=6)){
                    coins-=6;
                    isDoubleCoinsBought=true;
                    ((TextView) findViewById(R.id.coinsText)).setText(String.format("У вас %d монет(a/ы)", coins));
                }else{
                    if(isDoubleCoinsBought){
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Удвоение монет уже приобретено", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Недостаточно монет", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                break;
            }
            case 2:{
                ((TextView)findViewById(R.id.infoText)).setText(String.format("Для управления используйте левую и правую половины экрана\n" +
                        "Бонусы:\n" +
                        "Синий-бонус обзора\n" +
                        "Бирюзовый-удвоение очков и монет\n" +
                        "Красный-активация щита"));
                break;
            }
        }
        return true;
    }
}















//