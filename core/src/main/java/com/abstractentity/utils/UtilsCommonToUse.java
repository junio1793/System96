package com.abstractentity.utils;

import java.util.Objects;

public class UtilsCommonToUse {

    public static boolean equals(Object a, Object b) {
        return Objects.deepEquals(a, b);
    }

}
