package com.example.Square_Run;

import android.widget.Switch;
import min3d.core.Object3d;
import min3d.core.Object3dContainer;
import min3d.objectPrimitives.Box;
import min3d.vos.Color4;

import java.util.Random;

/**
 * Created by Артем on 10.05.2015.
 */
public class MyMultyObject extends Object3dContainer {
    MyMultyObject(){

        Random random = new Random();
        Color4 color=new Color4(0,0,0,255);
        Color4 colorS=new Color4(0,0,0,255);
        switch (random.nextInt(2)){
            case 0:{
                color = new Color4(51,102,0,255);
                colorS = new Color4(70,42,14,255);
                break;
            }
            case 1:{
                color = new Color4(127,255,0,255);
                colorS = new Color4(245,245,200,255);
                break;
            }
        }
        Object3d stvol  =new Box(0.99f,3,0.99f,colorS);
        stvol.colorMaterialEnabled(true);
        addChild(stvol);
        stvol.position().y=0.5f;

        Object3d cr1  =new Box(5f,2.8f,5,color);
        cr1.colorMaterialEnabled(true);
        addChild(cr1);
        cr1.position().y=4;

        Object3d cr2  =new Box(3,4,3,color);
        cr2.colorMaterialEnabled(true);
        addChild(cr2);
        cr2.position().y=4;




    }
}
