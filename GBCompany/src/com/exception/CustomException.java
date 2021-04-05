package com.exception;

import java.io.Serializable;

/**
 * @author HeeshaJ
 *
 */
public class CustomException extends Exception implements Serializable{

    private String msg;

    public CustomException(String msg) {
        super();
        this.msg = msg;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }


}
