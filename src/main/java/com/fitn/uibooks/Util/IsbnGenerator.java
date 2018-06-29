package com.fitn.uibooks.Util;

import java.util.Random;

public class IsbnGenerator implements NumberGenerator {
    @Override
    public String generateNumber() {
        return "13-122" + Math.abs(new Random().nextInt());
    }
}
