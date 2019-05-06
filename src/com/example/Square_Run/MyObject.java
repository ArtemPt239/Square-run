package com.example.Square_Run;

import android.graphics.Bitmap;
import android.graphics.Color;
import min3d.Shared;
import min3d.Utils;
import min3d.core.Object3d;
import min3d.core.Object3dContainer;
import min3d.objectPrimitives.Box;
import min3d.objectPrimitives.Sphere;
import min3d.objectPrimitives.Torus;
import min3d.vos.Color4;

import java.util.Random;

/**
* Created by artem on 2/24/14.
*/
public class MyObject extends Object3dContainer {

    Color4 objectColor;
    public static int AIR=0,BLOCK=1,COIN=2,BONUSDOUBLE=3,BONUSVIEW=4,BONUSSHIELD=5,BONUS1=6,BONUS2=7;
    public int type=AIR;
    public int texturesCode=0;

    Object3d object;

    MyObject(int thisTexturesCode){
        texturesCode=thisTexturesCode;
    }

    public void spawn(int thisType) {
        if(!Shared.textureManager().contains("lava")) {
            Bitmap b = Utils.makeBitmapFromResourceId(R.drawable.mylava);
            Shared.textureManager().addTextureId(b, "lava", false);
            b.recycle();
        }
        type=thisType;
        removeChild(object);

        switch (texturesCode){
            case 0:{
                if(thisType==BLOCK) {

                    object = new Box(0.99f, 2, 0.99f, new Color4(255, 255, 255, 255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==COIN){

                    object = new Torus(0.3f,0.05f,30,10,new Color4(212,175,55,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(90,0,0);
                }
                if(thisType==AIR){

                }
                if(thisType==BONUSDOUBLE){
                    object = new Sphere(0.4f,15,15,new Color4(0,255,255,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUSVIEW){
                    object = new Sphere(0.4f,15,15,new Color4(0,0,255,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUSSHIELD){
                    object = new Sphere(0.4f,15,15,new Color4(255,0,150,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUS1){

                }
                if(thisType==BONUS2){

                }
                break;
            }
            case 1:{
                if(thisType==BLOCK) {

                    object = new Box(0.99f, 2, 0.99f);
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(false);
                    addChild(object);

                    object.texturesEnabled(true);
                    object.textures().addById("lava");


                    object.rotation().setAll(0,0,0);
                }
                if(thisType==COIN){

                    object = new Torus(0.3f,0.05f,30,10,new Color4(212,175,55,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(90,0,0);
                }
                if(thisType==AIR){

                }
                if(thisType==BONUSDOUBLE){
                    object = new Sphere(0.4f,15,15,new Color4(0,255,255,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUSVIEW){
                    object = new Sphere(0.4f,15,15,new Color4(0,0,255,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUSSHIELD){
                    object = new Sphere(0.4f,15,15,new Color4(255,0,150,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUS1){

                }
                if(thisType==BONUS2){

                }
                break;
            }
            case 2:{
                if(thisType==BLOCK) {
                    Random random = new Random();
                    object = new Box(0.99f, 2, 0.99f, new Color4(random.nextInt(128)+50,random.nextInt(128)+50, random.nextInt(128)+50, 255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==COIN){

                    object = new Torus(0.3f,0.05f,30,10,new Color4(212,175,55,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(90,0,0);
                }
                if(thisType==AIR){

                }
                if(thisType==BONUSDOUBLE){
                    object = new Sphere(0.4f,15,15,new Color4(0,255,255,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUSVIEW){
                    object = new Sphere(0.4f,15,15,new Color4(0,0,255,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUSSHIELD){
                    object = new Sphere(0.4f,15,15,new Color4(255,0,150,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUS1){

                }
                if(thisType==BONUS2){

                }
                break;
            }
            case 3:{
                if(thisType==AIR) {

                    object = new Box(0.99f, 2, 0.99f, new Color4(255, 255, 255, 255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);

                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==COIN){

                    object = new Torus(0.3f,0.05f,30,10,new Color4(212,175,55,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(90,0,0);
                }
                if(thisType==BLOCK){

                }
                if(thisType==BONUSDOUBLE){
                    object = new Sphere(0.4f,15,15,new Color4(0,255,255,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUSVIEW){
                    object = new Sphere(0.4f,15,15,new Color4(0,0,255,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUSSHIELD){
                    object = new Sphere(0.4f,15,15,new Color4(255,0,150,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUS1){

                }
                if(thisType==BONUS2){

                }
                break;
            }
            case 4:{
                if(thisType==BLOCK) {

                    Random random=new Random();

                    if(random.nextInt(10)==0) {

                        object = new MyMultyObject();
                        object.position().setAll(0, 0, 0);
                        object.colorMaterialEnabled(true);

                        addChild(object);
                        object.rotation().setAll(0, 0, 0);
                    }else{
                        object = new Box(0.99f, 2, 0.99f, new Color4(255, 255, 255, 255));
                        object.position().setAll(0, 0, 0);
                        object.colorMaterialEnabled(true);
                        addChild(object);
                        object.rotation().setAll(0,0,0);
                    }
                }
                if(thisType==COIN){

                    object = new Torus(0.3f,0.05f,30,10,new Color4(212,175,55,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(90,0,0);
                }
                if(thisType==AIR){

                }
                if(thisType==BONUSDOUBLE){
                    object = new Sphere(0.4f,15,15,new Color4(0,255,255,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUSVIEW){
                    object = new Sphere(0.4f,15,15,new Color4(0,0,255,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUSSHIELD){
                    object = new Sphere(0.4f,15,15,new Color4(255,0,150,255));
                    object.position().setAll(0, 0, 0);
                    object.colorMaterialEnabled(true);
                    addChild(object);
                    object.rotation().setAll(0,0,0);
                }
                if(thisType==BONUS1){

                }
                if(thisType==BONUS2){

                }
                break;
            }
        }


    }

    public void kill() {

        removeChild(object);
    }



}
