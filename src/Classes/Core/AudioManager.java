/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rpg.Core;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 *
 * @author Manolo
 */
public class AudioManager {

    static private AudioManager instance;
    private HashMap<String, AudioClip> clips;

    private AudioManager() {
        this.clips = new HashMap<String, AudioClip>();
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public AudioClip loadAudio(String fileName) throws IOException {
        URL url = getClass().getResource("/" + fileName);
        if (url == null) {
            throw new RuntimeException("Audio /" + fileName + " não foi encontrado!");
        } else {
            if (this.clips.containsKey(fileName)) {
                return this.clips.get(fileName);
            } else {
                AudioClip clip = Applet.newAudioClip(getClass().getResource("/" + fileName));
                this.clips.put(fileName, clip);
                return clip;
            }
        }
    }
}
