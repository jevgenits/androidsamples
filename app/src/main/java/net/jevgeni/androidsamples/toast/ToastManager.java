package net.jevgeni.androidsamples.toast;

import android.content.Context;
import android.widget.Toast;

public class ToastManager {

  public void printText(String text, Context context) {
    if (text != null) {
      Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
  }
}
