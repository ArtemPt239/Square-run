package com.example.Square_Run;

import android.content.Intent;
import android.graphics.*;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import min3d.Shared;
import min3d.Utils;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Box;
import min3d.objectPrimitives.Rectangle;
import min3d.objectPrimitives.SkyBox;
import min3d.vos.Color4;
import min3d.vos.Face;
import min3d.vos.Light;
import min3d.vos.Number3d;

import java.util.Random;

/**
 * Created by artem on 2/22/14.
 */
public class ExtraActivity extends RendererActivity {
    float startX = 0,startY = 0;
    float speed = 0,startSpeed=0.20f; //0.08f 0.4 - max
    float time=0,bestTime=0,createTime=5,startCreateTime=5,bonuscreatetime=50,nowBonusActiveTime=0,tipeOfActiveBonus=MyObject.BONUSDOUBLE;
    boolean isBonusActive=false,isShieldActive=false;
    public long best,result=0,coinsCollected=0;
    String safeState="No", playerState="Normal";


    public static final String BESTRESULTKEY = "myBestResultKey",COINKEY="myCoinKey",TEXTURESKEY="myTexturesKey",STARTSHIELDKEY="myStartShieldKey";
    public int textureCode=0;

    static final float coinsRotationSpeed=1.3f;
    float coinsRotationY=0;

    static final int lengh = 40;//40=perfect

    Box[] road = new Box[lengh/2];

    MyObject[] jampack = new MyObject[lengh];

    MyObject[][] objects = new MyObject[lengh/4][3];
    int numOfLine =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=this.getIntent();
        if(intent.hasExtra(TEXTURESKEY)) {
            textureCode = intent.getIntExtra(TEXTURESKEY,0);
        }
        if(intent.hasExtra(STARTSHIELDKEY)) {
            isShieldActive = intent.getBooleanExtra(STARTSHIELDKEY,false);
        }

    }

    //Number3d startPos;

    public void initScene() {
//        scene.fogColor(new Color4(0, 0, 0, 255) );
//        scene.fogNear(10);
//        scene.fogFar(40);
//        scene.fogEnabled(true);
        skyBoxCreate();
        //best = getIntent().getExtras().getLong(MyActivity.KEYOUT);

        createRoad();

        for(int i=0;i<lengh/4;i++){
            objects[i][0] = new MyObject(textureCode);
            objects[i][1] = new MyObject(textureCode);
            objects[i][2] = new MyObject(textureCode);
            spawnLine(i);
        }
        for(float i = -1f;i<2;i++){
            Color4 c = new Color4(120,120,119,255);
            Box b = new Box(0.1f,0.1f,lengh,c );
            scene.addChild(b);
            b.colorMaterialEnabled(true);
            b.position().setAll(i,0.55f,-lengh/2+4);
        }
        scene.lights().add(new Light());
        scene.camera().position.setAll(0,2,5);
        scene.camera().target.setAll(scene.camera().position.x, 0, lengh * (-1));


        _glSurfaceView.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getX()<glSurfaceView().getWidth()/2){
                    if(scene.camera().position.x>-1)
                        scene.camera().position.x-=1;
                }else{
                    if(scene.camera().position.x<1)
                        scene.camera().position.x+=1;
                }


//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        startX = motionEvent.getX();
//                        startY = motionEvent.getY();
//                        startPos = scene.camera().position.clone();
//                        return true;
//                    case MotionEvent.ACTION_MOVE:
//                        float diffX = -(motionEvent.getX() - startX) * 0.3f;
//                        float diffY = (motionEvent.getY() - startY) * 0.03f;
//                        scene.camera().position = startPos.clone();
//                        //scene.camera().position.z += diffZ;
//                        //scene.camera().position.rotateY((float) (diffZ * Math.PI / 180));
//                        //scene.camera().position.y += diffY;
//                        if(diffX<0 && scene.camera().position.x> -1 )
//                            scene.camera().position.x-=1;
//                        if(diffX>0 && scene.camera().position.x< 1 )
//                            scene.camera().position.x+=1;
//                        scene.camera().target.setAll(scene.camera().position.x,0, lengh * (-1));
//                        return true;
//                }


                return false;
            }


        }
        );
    }

    private void createRoad(){
        if(textureCode!=2) {
            for (int i = 0; i < lengh / 2; i++) {
                Color4 c = new Color4(255, 255, 255, 255);
                road[i] = new Box(5, 1, 1, c);
                scene.addChild(road[i]);
                road[i].colorMaterialEnabled(true);
                road[i].position().setAll(0, 0, lengh * (-1) + i * 2);
            }
        }else{
            for (int i = 0; i < lengh / 2; i++) {
                Color4 c = new Color4(255, 255, 255, 255);
                switch (i-i/7*7) {
                    case 0: {
                        c=new Color4(204,0,0,255);
                        break;
                    }
                    case 1: {
                        c=new Color4(204,85,0,255);
                        break;
                    }
                    case 2: {
                        c=new Color4(253,233,16,255);
                        break;
                    }
                    case 3: {
                        c=new Color4(124,252,0,255);
                        break;
                    }
                    case 4: {
                        c=new Color4(0,125,255,255);
                        break;
                    }
                    case 5: {
                        c=new Color4(0,0,128,255);
                        break;
                    }
                    case 6: {
                        c=new Color4(153,17,153,255);
                        break;
                    }
                }
                road[i] = new Box(5, 1, 1, c);
                scene.addChild(road[i]);
                road[i].colorMaterialEnabled(true);
                road[i].position().setAll(0, 0, lengh * (-1) + i * 2);
            }
        }
    }

    private  void skyBoxCreate(){
        SkyBox skyBox = new SkyBox(80,2);



        switch (textureCode){
            case 0 :{
                skyBox.isVisible(false);
                break;
            }
            case 1 :{
                skyBox.addTexture(SkyBox.Face.Down,R.drawable.mylava,"mylava");
                skyBox.addTexture(SkyBox.Face.South,R.drawable.myvolcano2,"myvolcano");
                break;
            }
            case 2 :{
                skyBox.addTexture(SkyBox.Face.Down,R.drawable.lightcolorsdown,"mylightcolorsdown");
                skyBox.addTexture(SkyBox.Face.South,R.drawable.lightcolorsfront,"mylightcolorsfront");
                break;
            }
            case 3 :{
                skyBox.isVisible(false);
                break;
            }
            case 4 :{
                skyBox.addTexture(SkyBox.Face.Down,R.drawable.forestdown,"myforestdown");
                skyBox.addTexture(SkyBox.Face.South,R.drawable.forestfront,"mysorestfront");
                break;
            }
        }

        scene.addChild(skyBox);
        skyBox.position().y=40.4f;
    }

    private void gameOver(){

        Intent intent = new Intent();
        intent.putExtra(BESTRESULTKEY, result);
        intent.putExtra(COINKEY,coinsCollected);
        setResult(RESULT_OK, intent);
        finish();
//        AlertDialog.Builder builder = new AlertDialog.Builder(ExtraActivity.this);
//        builder.setTitle("message")
//               .setMessage(String.format("Ваш результат = %f, рекорд = %f"))
//               .setCancelable(false)
//               .setNegativeButton("Заново", new DialogInterface.OnClickListener() {
//                   public void onClick(DialogInterface dialog, int id) {
//                       resetGame();
//                       dialog.cancel();
//                   }
//               });
//        AlertDialog alert = builder.create();
//        alert.show();
    }



    private void spawnLine(int num){
        if(time>createTime){
            time=0;
            Random random = new Random();
            int thisX;
            int x=0;
            if(result<200)
                thisX=random.nextInt(3);
            else
                thisX=random.nextInt(6);
            if(result>400)
                thisX=random.nextInt(3)+3;
            switch(thisX){
                case 0:{
                    objects[num][0].spawn( MyObject.BLOCK);
                    objects[num][1].spawn( MyObject.AIR);
                    objects[num][2].spawn( MyObject.AIR);
                    x=random.nextInt(2)+1;
                    break;
                }
                case 1:{
                    objects[num][0].spawn( MyObject.AIR);
                    objects[num][1].spawn( MyObject.BLOCK);
                    objects[num][2].spawn( MyObject.AIR);
                    if(random.nextInt(2)==0)
                        x=0;
                    else
                        x=2;
                    break;
                }
                case 2:{
                    objects[num][0].spawn( MyObject.AIR);
                    objects[num][1].spawn( MyObject.AIR);
                    objects[num][2].spawn( MyObject.BLOCK);
                    x=random.nextInt(2);
                    break;
                }
                case 3:{
                    objects[num][0].spawn( MyObject.BLOCK);
                    objects[num][1].spawn( MyObject.BLOCK);
                    objects[num][2].spawn( MyObject.AIR);
                    x=2;
                    break;
                }
                case 4:{
                    objects[num][0].spawn( MyObject.AIR);
                    objects[num][1].spawn( MyObject.BLOCK);
                    objects[num][2].spawn( MyObject.BLOCK);
                    x=0;
                    break;
                }
                case 5:{
                    objects[num][0].spawn( MyObject.BLOCK);
                    objects[num][1].spawn( MyObject.AIR);
                    objects[num][2].spawn( MyObject.BLOCK);
                    x=1;
                    break;
                }
            }

            if (bonuscreatetime>createTime*30+random.nextInt(100)){
                switch (random.nextInt(3)){
                    case 0:{
                        objects[num][x].spawn( MyObject.BONUSDOUBLE);
                        break;
                    }
                    case 1:{
                        objects[num][x].spawn( MyObject.BONUSVIEW);
                        break;
                    }
                    case 2:{
                        objects[num][x].spawn( MyObject.BONUSSHIELD);
                        break;
                    }
                }
                bonuscreatetime=0;

            }else{
                if(random.nextInt(10)==0){
                    objects[num][x].spawn( MyObject.COIN);
                }
            }
            scene.addChild(objects[num][0]);
            objects[num][0].position().setAll(-1, 1.5f, lengh * (-1));
            objects[num][0].rotation().setAll(0,0,0);
            scene.addChild(objects[num][1]);
            objects[num][1].position().setAll(0, 1.5f, lengh * (-1));
            objects[num][1].rotation().setAll(0,0,0);
            scene.addChild(objects[num][2]);
            objects[num][2].position().setAll(1, 1.5f, lengh * (-1));
            objects[num][2].rotation().setAll(0, 0, 0);


            //здесь будет код спавна окружения




            numOfLine++;
            if(numOfLine >= lengh /4){
                numOfLine =0;
            }
            time=0;
        }

    }

    private void killLine(int num){
        objects[num][0].kill();
        objects[num][1].kill();
        objects[num][2].kill();
    }

    public void moveLine(int num,float dz){
        objects[num][0].position().z+=dz;
        objects[num][1].position().z+=dz;
        objects[num][2].position().z+=dz;
    }

    public void updateScene(){
        time+=speed;
        bonuscreatetime+=speed;
        nowBonusActiveTime-=speed;
        coinsRotationY+=coinsRotationSpeed;
        for(int i = 0;i< lengh/2;i++){
            road[i].position().z=road[i].position().z + speed ;
            if(road[i].position().z>4){

                road[i].position().z+=lengh * (-1);

            }
        }
        for(int i = 0;i< lengh/4;i++){
            moveLine(i, speed );
            if(objects[i][0].position().z>4){
                killLine(i);
            }
            if(objects[i][(int)scene.camera().position.x+1].position().z > 3f &&  objects[i][(int)scene.camera().position.x+1].position().z < 4 ){
                if(objects[i][(int)scene.camera().position.x+1].type==MyObject.BLOCK ){
                    if(isShieldActive){
                        isShieldActive=false;
                        killLine(i);
                        objects[i][0].spawn(MyObject.AIR);
                        objects[i][1].spawn(MyObject.AIR);
                        objects[i][2].spawn(MyObject.AIR);
                        if(i<lengh/4) {
                            killLine(i + 1);
                            objects[i+1][0].spawn(MyObject.AIR);
                            objects[i+1][1].spawn(MyObject.AIR);
                            objects[i+1][2].spawn(MyObject.AIR);
                        }else{
                            killLine(0);
                            objects[0][0].spawn(MyObject.AIR);
                            objects[0][1].spawn(MyObject.AIR);
                            objects[0][2].spawn(MyObject.AIR);
                        }

                    }else {
                        gameOver();
                    }
                }
                if(objects[i][(int)scene.camera().position.x+1].type==MyObject.COIN){
                    if(isBonusActive&&(tipeOfActiveBonus==MyObject.BONUSDOUBLE)){
                        coinsCollected+=2;
                    }else{
                        coinsCollected++;
                    }

                    objects[i][(int)scene.camera().position.x+1].kill();
                    objects[i][(int)scene.camera().position.x+1].spawn(MyObject.AIR);
                }
                if(objects[i][(int)scene.camera().position.x+1].type==MyObject.BONUSDOUBLE){
                    tipeOfActiveBonus=MyObject.BONUSDOUBLE;
                    isBonusActive=true;
                    objects[i][(int)scene.camera().position.x+1].kill();
                    objects[i][(int)scene.camera().position.x+1].spawn(MyObject.AIR);
                    nowBonusActiveTime=createTime*15;
                }
                if(objects[i][(int)scene.camera().position.x+1].type==MyObject.BONUSVIEW){
                    tipeOfActiveBonus=MyObject.BONUSVIEW;
                    isBonusActive=true;
                    objects[i][(int)scene.camera().position.x+1].kill();
                    objects[i][(int)scene.camera().position.x+1].spawn(MyObject.AIR);
                    nowBonusActiveTime=createTime*15;
                }
                if(objects[i][(int)scene.camera().position.x+1].type==MyObject.BONUSSHIELD){
                    isShieldActive=true;
                    objects[i][(int)scene.camera().position.x+1].kill();
                    objects[i][(int)scene.camera().position.x+1].spawn(MyObject.AIR);

                }

            }
            for(int j=0;j<3;j++){
                if(objects[i][j].type==MyObject.COIN){
                    objects[i][j].rotation().y=coinsRotationY;
                }
            }
        }

        if(nowBonusActiveTime<0)
            isBonusActive=false;

        if((isBonusActive && (tipeOfActiveBonus==MyObject.BONUSVIEW))||(textureCode==3)){
            scene.camera().position.y=3;
        }else{
            scene.camera().position.y=2;
        }

        bestTime+=speed;
        if(bestTime>=1){
            if(isBonusActive&&(tipeOfActiveBonus==MyObject.BONUSDOUBLE)){
                result+=2;
            }else{
                result++;
            }
            bestTime-=1;
        }


        speed=startSpeed+((float)result)/2455;//its too hard to reach 1000meters
        createTime=startCreateTime+result/110;//so, just don^t touch this constants
        if(speed>0.5)
            speed=0.5f;


        spawnLine(numOfLine);

    }
}


