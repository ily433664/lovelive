package com.lovelive.flow;


import com.lovelive.user.entity.User;

import java.io.Serializable;


/**
 * 流程流转信息
 */
public class FlowInfoMoving implements Serializable {

    private static final long serialVersionUID = -930118406681972346L;

    // 流程节点，标识此流转信息是流转到了哪个节点
    private FlowInfoMovingNode node;

    // 申请源，具体申请信息的唯一标识
    private Long applyId;

    // 申请人，具体申请信息的申请人
    private User applyUser;

    // 申请信息，具体申请信息的简要概述，由申请提供
    private String discription;

    // 申请类别，即申请的流程的类型
    private Dictionary applyType;

    // 变换的结果，即审核通过未通过等条件，此条件同FlowInfoMovingRole种的transition，
    // 但有一些区别，因为有添加待审核，已结束等其他信息条件
    private String transition;

    // 处理信息，即审核信息，由审核人添加
    private String handlerInfo;

    // 处理人，即此流转应该由谁来申请，由FlowInfoMovingNode中的handlerRole来进行筛选，
    //并由上一个节点的处理人来进行选择具体审核人。
    private User handlerUser;

    // 此流转信息的状态,用于查询,0未审核,1已审核,2本申请已经结束
    private int status;


}
