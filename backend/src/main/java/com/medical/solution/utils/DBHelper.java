package com.medical.solution.utils;

import java.util.UUID;

public class DBHelper {

    public static long getId(){
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

}
