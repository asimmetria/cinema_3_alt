package com.kata.cinema.base.models.enums;

import java.util.List;
import java.util.Random;

public enum MPAA {
    GENERAL_AUDIENCES,
    PARENTAL_GUIDANCE_SUGGESTED,
    PARENTS_STRONGLY_CAUTIONED,
    NO_ONE_SEVENTEEN_AND_UNDER_ADMITTED;

    private static final List<MPAA> VALUES =
            List.of(values());

    public static MPAA randomMPAA() {
        return VALUES.get(new Random().nextInt(0, VALUES.size()));
    }
}
