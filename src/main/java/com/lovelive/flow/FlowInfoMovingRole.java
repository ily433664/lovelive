package com.lovelive.flow;

import java.io.Serializable;

/**
 *  流程流转规则
 *
 *
 *
 * */
public class FlowInfoMovingRole implements Serializable {

    private static final long serialVersionUID = 620128480041351153L;

    private FlowInfoMovingNode node;

    private FlowInfoMovingNode toNode;

    private String transition;
}
