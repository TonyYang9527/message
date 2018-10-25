package com.alpha.message.common.enums;


public enum MsgTemplateState {
    ENABLED( 0), 
    DISABLED( 1);

    private int  value;

    MsgTemplateState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MsgTemplateState fromValue(byte value) {
        for (MsgTemplateState flag : MsgTemplateState.values()) {
            if (flag.getValue() == value) {
                return flag;
            }
        }
        return null;
    }
}
