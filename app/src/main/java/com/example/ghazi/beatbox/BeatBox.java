package com.example.ghazi.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ghazi on 8/3/2016.
 */
public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 22;
    private AssetManager mAssetManager ;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public BeatBox(Context context){
        mAssetManager = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
        loadSounds(mAssetManager);
    }

    public void loadSounds(AssetManager assets){
        String[] soundNames = null;
        try{
            soundNames = assets.list(SOUNDS_FOLDER);
            Log.i(TAG,"Found " + soundNames.length + " sounds" );
        }
        catch (IOException ioe){
            Log.d(TAG,"could not list assets",ioe);
        }

        for (String filename :
                soundNames) {
            try {

                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            }
            catch (IOException ioe){
                Log.d(TAG,"Could not load sound.");
            }
        }

    }

    public void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssetManager.openFd(sound.getAssetPath());
        int soundID = mSoundPool.load(afd,1);
        sound.setSoundID(soundID);
    }

    public void play(Sound sound){
        Integer soundID = sound.getSoundID();
        if (soundID == null){
            return;
        }
        mSoundPool.play(soundID,1.0f,1.0f,1,0,1.0f);
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    public void release(){
        mSoundPool.release();
    }
}
