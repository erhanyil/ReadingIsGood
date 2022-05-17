package com.example.readingisgood.exception;

import com.example.readingisgood.constant.SystemMessage;

public class FriendlyException extends RuntimeException {

    private String errorStr;

    public FriendlyException(Throwable throwable) {
        super(throwable);
    }

    public FriendlyException(SystemMessage systemMessage) {
        this.errorStr = systemMessage.name();
    }

    public FriendlyException(String str) {
        this.errorStr = str;
    }

    public String getErrorStr() {
        return errorStr;
    }

    public void setErrorStr(String errorStr) {
        this.errorStr = errorStr;
    }
}
