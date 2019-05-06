package com.example.Square_Run;

/**
 * Created by artem on 06.05.15.
 */
public class MyTextureInfo {
    public static int kolvo=5;
    public static final String[] names = {"Standard","Volcanic","Light colors","Inversion","Forest"};//добавлять только справа(иначе-жопа)
   //код==номер элемента в массиве
    public static final int[] prices = {0,60,100,1,80};
    public boolean[] defaultIsBuyed = {true,false,false,false,false};
    //при добавление/изменение текстур изменять все консттанты сверху

    //для корректной работы текстур необходимо синхронизировать этот класс и MyObject

}
