/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.sun.speech.freetts.*;
/**
 *
 * @author ID
 */
public class Speak {
    
    private static final String VOICE = "kevin16";
    public void Sound(String s){
        Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICE);
        voice.allocate();
        try{
            voice.speak(s);
        }catch(Exception e){  
        }
    }  
}