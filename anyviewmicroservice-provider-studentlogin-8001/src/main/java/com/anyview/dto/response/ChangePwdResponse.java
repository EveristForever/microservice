package com.anyview.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-04-08 12:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePwdResponse<T> implements Serializable {
    private static final long serialVersionUID = -2784330694252846851L;

    private int changePswState; //1成功 0失败
    private String msg;
    //private T user;

}
