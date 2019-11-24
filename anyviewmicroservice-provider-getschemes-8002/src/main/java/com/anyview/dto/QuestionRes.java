package com.anyview.dto;

import com.anyview.entity.QuestionContent;
import com.anyview.entity.QuestionHeaderFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jingshanccc
 * @description: 解析后的题目内容
 * @create: 2019-03-21 22:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRes {

    private String questionFullName;
    private String config;
    private QuestionContent questionContent;
    private List<QuestionHeaderFile> questionHeaderFiles;
}
