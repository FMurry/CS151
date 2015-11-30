package com.sixamigos.sjsucanvasapp.color;

import android.graphics.Color;

import com.sixamigos.sjsucanvasapp.R;

public class Colors {

  public static int getColor(int i) {
    switch (i) {
      case 0: return R.color.sjsuGray;
      case 1: return R.color.sjsuGold;
      case 2: return R.color.sjsuBlue;
      default: return getColor(i - 3);
    }
  }

  public static int getDarkColor(int i) {
    switch (i) {
      case 0: return R.color.darkGray;
      case 1: return R.color.darkGold;
      case 2: return R.color.darkBlue;
      default: return getDarkColor(i - 3);
    }
  }

}
