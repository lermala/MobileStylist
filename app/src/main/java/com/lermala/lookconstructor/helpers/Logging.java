package com.lermala.lookconstructor.helpers;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class Logging {
    // https://github.com/orhanobut/logger?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=1658

    public void firstlyInit(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
