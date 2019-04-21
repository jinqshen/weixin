package com.jinqshen.weixin.pojo;

import java.io.Serializable;

public class ResponseData extends Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5797811754210862876L;
	
	private Object data;

    public ResponseData(Object data) {
        this.data = data;
    }
    
    public ResponseData(ExceptionMsg msg) {
    	  super(msg);
    }
    
    public ResponseData(String rspCode, String rspMsg) {
        super(rspCode, rspMsg);
    }

    public ResponseData(String rspCode, String rspMsg, Object data) {
        super(rspCode, rspMsg);
        this.data = data;
    }

    public ResponseData(ExceptionMsg msg, Object data) {
        super(msg);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
