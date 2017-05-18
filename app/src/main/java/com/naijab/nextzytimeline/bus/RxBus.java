package com.naijab.nextzytimeline.bus;

import com.hwangjr.rxbus.Bus;

public class RxBus {

    private static Bus sBus;

    public static synchronized Bus get() {
        if (sBus == null) {
            sBus = new Bus();
        }
        return sBus;
    }
}
