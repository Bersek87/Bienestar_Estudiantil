/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author Eden
 */

import java.io.File;
import javax.sound.sampled.*;
import javax.sound.midi.*;
public class GrabarDeclaracion {
public GrabarDeclaracion(){
try {
    AudioFormat aF = null;
    DataLine.Info dLI = new DataLine.Info(TargetDataLine.class,aF);
    TargetDataLine tD = (TargetDataLine)AudioSystem.getLine(dLI);
    new CapThread().start();
    System.out.println("Grabando durante 10s...");
    Thread.sleep(10000);
    tD.close();
    }catch (Exception e) {}
    }
    class CapThread extends Thread {
        private Object tD;
        @Override
    public void run() {
    try {
    //tD.open(aF);
    //tD.start();
    //AudioSystem.write(new AudioInputStream((TargetDataLine) tD), aFF_T, f);
    }catch (Exception e){}
    }
    }
}
     