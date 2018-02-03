package com.abc.project.logic.rounder;

import java.math.BigDecimal;

public interface Rounder<T> {
    T round(T taxValue);
}

