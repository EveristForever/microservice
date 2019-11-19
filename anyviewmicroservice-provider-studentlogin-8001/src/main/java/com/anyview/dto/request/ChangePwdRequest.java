package com.anyview.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-04-08 12:28
 */
@Data
public class ChangePwdRequest implements Serializable {

    private static final long serialVersionUID = 6602371538958768997L;

    private Long id;
    private Long roleId;
    private String oldPwd;
    private String newPwd;

}
