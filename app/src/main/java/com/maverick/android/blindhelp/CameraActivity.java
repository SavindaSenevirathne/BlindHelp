/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.maverick.android.blindhelp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import static android.content.ContentValues.TAG;

/** Main {@code Activity} class for the Camera app. */
public class CameraActivity extends Activity {

  private MainActivity.Modes currentMode;
  private MediaPlayer mediaPlayer;
  private String currentNote;


    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_camera);
    currentMode = GlobalVariables.getInstance().currentMode;
    Log.d(TAG, "onCreate: currentMode " + currentMode);
    switch (currentMode) {
        case Sinhala:
            palySound(R.raw.camera_opened_sinhala);
            break;
        case English:
            palySound(R.raw.camera_opened_english);
            break;
        case Tamil:
            palySound(R.raw.camera_opened_tamil);
            break;
    }
    if (null == savedInstanceState) {
      getFragmentManager()
          .beginTransaction()
          .replace(R.id.container, Camera2BasicFragment.newInstance())
          .commit();
    }
      findViewById(R.id.container).setOnClickListener(v -> {
//        current note values - Error, Not_Detected and labels
          currentNote = GlobalVariables.getInstance().currentNote;
          Log.d(TAG, "onClick: Screen Pressed " + currentNote);
          switch (currentNote) {
              case "Error":
                  switch (currentMode) {
                      case Sinhala:
                          palySound(R.raw.error_sinhala);
                          break;
                      case English:
                          palySound(R.raw.error_english);
                          break;
                      case Tamil:
                          palySound(R.raw.error_tamil);
                          break;
                  }
                  break;
              case "Not_Detected":
                  switch (currentMode) {
                      case Sinhala:
                          palySound(R.raw.not_detected_sinhala);
                          break;
                      case English:
                          palySound(R.raw.not_detected_english);
                          break;
                      case Tamil:
                          palySound(R.raw.not_detected_tamil);
                          break;
                  }
                  break;
              case "fifty":
                  switch (currentMode) {
                      case Sinhala:
                          palySound(R.raw.fifty_sinhala);
                          break;
                      case English:
                          palySound(R.raw.fifty_english);
                          break;
                      case Tamil:
                          palySound(R.raw.fifty_tamil);
                          break;
                  }
                  break;
              case "hundred":
                  switch (currentMode) {
                      case Sinhala:
                          palySound(R.raw.hundred_sinhala);
                          break;
                      case English:
                          palySound(R.raw.hundred_english);
                          break;
                      case Tamil:
                          palySound(R.raw.hundred_tamil);
                          break;
                  }
                  break;
              case "fivehundred":
                  switch (currentMode) {
                      case Sinhala:
                          palySound(R.raw.five_hundred_sinhala);
                          break;
                      case English:
                          palySound(R.raw.five_hundred_english);
                          break;
                      case Tamil:
                          palySound(R.raw.five_hundred_tamil);
                          break;
                  }
                  break;
              case "thousand":
                  switch (currentMode) {
                      case Sinhala:
                          palySound(R.raw.thousand_sinhala);
                          break;
                      case English:
                          palySound(R.raw.thousand_english);
                          break;
                      case Tamil:
                          palySound(R.raw.thousand_tamil);
                          break;
                  }
                  break;
              case "twenty":
                  switch (currentMode) {
                      case Sinhala:
//                          palySound(R.raw.twenty_english);
                          break;
                      case English:
                          palySound(R.raw.twenty_english);
                          break;
                      case Tamil:
//                          palySound(R.raw.thousand_tamil);
                          break;
                  }
                  break;
              case "five coin":
                  switch (currentMode) {
                      case Sinhala:
//                          palySound(R.raw.twenty_english);
                          break;
                      case English:
                          palySound(R.raw.five_coin_english);
                          break;
                      case Tamil:
//                          palySound(R.raw.thousand_tamil);
                          break;
                  }
                  break;
              default:
                  // not detected
                  switch (currentMode) {
                      case Sinhala:
                          palySound(R.raw.not_detected_sinhala);
                          break;
                      case English:
                          palySound(R.raw.not_detected_english);
                          break;
                      case Tamil:
                          palySound(R.raw.not_detected_tamil);
                          break;
                  }
                  break;
          }
      });
  }

    private void stopAllPlayingSounds() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void palySound(int soundFile) {
        stopAllPlayingSounds();
        mediaPlayer = MediaPlayer.create(CameraActivity.this, soundFile);
        mediaPlayer.start();

    }
}
