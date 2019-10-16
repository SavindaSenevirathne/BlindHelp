package com.maverick.android.blindhelp;

public class GlobalVariables {

    private static GlobalVariables mInstance= null;

    public String currentNote;
    public MainActivity.Modes currentMode;

    protected GlobalVariables(){}

    public static synchronized GlobalVariables getInstance() {
        if(null == mInstance){
            mInstance = new GlobalVariables();
        }
        return mInstance;
    }
}
