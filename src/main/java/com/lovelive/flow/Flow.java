package com.lovelive.flow;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  流程
 *
 *
 * */
public class Flow implements Serializable {

    private static final long serialVersionUID = -3101440514054899981L;

    private Long id;

    private String code;

    private String type;

    private String name;

    private Dictionary dictionary;

    private Timestamp startTime;

    private String remark;

    private String handerUrl;

    private Integer status;
}
