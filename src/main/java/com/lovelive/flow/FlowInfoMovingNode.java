package com.lovelive.flow;

import java.io.Serializable;

/**
 *  流程节点
 *
 *
 * */
public class FlowInfoMovingNode implements Serializable {

    private static final long serialVersionUID = 4115279131312803208L;

    private String nodeCode;

    private String nodeName;

    private Flow flow;

    private  FlowInfoMovingNode handerNode;

}
