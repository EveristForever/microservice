package com.anyview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jingshanccc
 * @description: websocket消息
 * @create: 2019-03-31 21:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocketMsg<T> implements Serializable {

    private static final long serialVersionUID = 4664655416752160196L;

    private Integer type;
    private T content;

    public SocketMsg(Integer type){
        this.type = type;
    }

}
