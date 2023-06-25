package com.kata.cinema.base.models.enums;

import java.util.List;
import java.util.Random;

public enum RARS {
    ZERO_PLUS,
    SIX_PLUS,
    TWELVE_PLUS,
    SIXTEEN_PLUS,
    EIGHTEEN_PLUS;

    private static final List<RARS> VALUES =
            List.of(values());

    public static RARS randomRARS() {
        return VALUES.get(new Random().nextInt(0, VALUES.size()));
    }
}
