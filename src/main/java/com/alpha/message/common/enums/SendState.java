package com.alpha.message.common.enums;


public enum SendState {

    DRAFT( 0),
    TOBESEND( 10),
    JOB_HANDLED(15),
    SENDING( 20),
    SENT( 30),
    READ(40),
    FAILED( 99);

    private int  value;

    SendState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SendState fromValue(int  value) {
        for (SendState flag : SendState.values()) {
            if (flag.getValue() == value) {
                return flag;
            }
        }
        return null;
    }
}
