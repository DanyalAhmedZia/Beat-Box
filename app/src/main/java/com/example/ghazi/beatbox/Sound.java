package com.example.ghazi.beatbox;

/**
 * Created by ghazi on 8/3/2016.
 */
public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer mSoundID;

    public Sound(String assetPath){
        mAssetPath = assetPath;
        String[] componnents = assetPath.split("/");
        String filename = componnents[componnents.length - 1];
        mName = filename.replace(".wav","");
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getSoundID() {
        return mSoundID;
    }

    public void setSoundID(Integer soundID) {
        mSoundID = soundID;
    }
}
