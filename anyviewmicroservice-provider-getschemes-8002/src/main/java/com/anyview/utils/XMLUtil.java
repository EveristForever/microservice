package com.anyview.utils;

import com.anyview.dto.QuestionRes;
import com.anyview.entity.QuestionContent;
import com.anyview.entity.QuestionHeaderFile;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-03-21 22:21
 */
public class XMLUtil {
    private  org.w3c.dom.Document doc;
    private DocumentBuilder builder = null;

    public XMLUtil(){
        // 初始化xml解析工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 创建DocumentBuilder
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        // 创建Document
        doc = builder.newDocument();
        // standalone用来表示该文件是否呼叫其它外部的文件。若值是 ”yes” 表示没有呼叫外部文件
        doc.setXmlStandalone(true);
    }

    public String stringToXML_String(String code) throws TransformerConfigurationException {
        /**
         *  @Author: permission
         *  @Description: 将code代码转为xml格式，再转为string类型存放。说明: doc.createElement("元素名")、element.setAttribute("属性名","属性值")、element.setTextContent("标签间内容")；
         *  @Date: 2019/9/6 23:32
         *  @Param [code]
         *  @Return: java.lang.String
         */

        org.w3c.dom.Element myOne=doc.createElement("econtent");
        myOne.setTextContent(code);
        doc.appendChild(myOne);
        // 把doc内容输出到具体的xml文件或者转化为string
        TransformerFactory formerFactory= TransformerFactory.newInstance();
        Transformer transformer=formerFactory.newTransformer();
        // 换行
        transformer.setOutputProperty(OutputKeys.INDENT, "YES");
        // 文档字符编码
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        /* 作用：StreamResult 是将源文件转换为字符流放到file或者其他Result类中*/
        StringWriter writer = new StringWriter();
        try {
            transformer.transform(new DOMSource(doc),new StreamResult(writer));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    //解析作业题目
    public static QuestionRes parseProblemXMLToString (String fullName, Integer questionId , String xmlContent) throws DocumentException {
        Document document;
        if (StringUtils.isEmpty(xmlContent)){
            return null;
        }
        else document = DocumentHelper.parseText(xmlContent);
        String config = "";
        QuestionContent questionContent = new QuestionContent();
        List<QuestionHeaderFile> questionHeaderFiles = new ArrayList<>();

        //解析过程与resources文件夹下的question.xml同时食用效果更佳
        Element problem = document.getRootElement();//<problem>
        List<Element> problemElements = problem.elements();
        for (Element problemChild : problemElements){
            String problemChildName = problemChild.getName();
            switch (problemChildName) {
                case "doc":
                    questionContent.setQuestionDescription(problemChild.getText());
                    break;
                case "content":
                    List<Element> contentElements = problemChild.elements();
                    for (Element contentChild : contentElements) {
                        String contentChildName = contentChild.getName();
                        switch (contentChildName) {
                            case "main-file":
                                questionContent.setMainFileName(contentChild.attributeValue("filename"));
                                questionContent.setMainFileContent(contentChild.getText());
                                break;
                            case "answer-file":
                                questionContent.setStandardAnswer(contentChild.getText());
                                break;
                            case "user-file":
                                String stuAns = contentChild.getText();//取除去注释后的内容
                                if(stuAns.contains("/*")) {//todo
                                    questionContent.setStudentAnswer(stuAns.substring(stuAns.indexOf("*/") + 2));
                                }else {
                                    questionContent.setStudentAnswer(stuAns);
                                }
                                break;
                            case "head-files":
                                List<Element> headElements = contentChild.elements();
                                for (Element headChild : headElements) {
                                    QuestionHeaderFile questionHeaderFile = new QuestionHeaderFile();
                                    questionHeaderFile.setHeaderFileName(headChild.attributeValue("filename"));
                                    questionHeaderFile.setHeaderFileContent(headChild.getText());
                                    questionHeaderFiles.add(questionHeaderFile);
                                }
                                break;
                            case "programContent":
                                break;
                            case "document":
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case "config":
                    config = problemChild.getText();
                    break;
            }

        }

        return new QuestionRes(fullName,config,questionContent,questionHeaderFiles);
    }

    //获取exercise表的内容 包含题目和学生答案，更多是获取学生作业的作用
    public static String exerciseContent(String xmlContent) throws DocumentException {
        Document document;
        if (StringUtils.isEmpty(xmlContent)){
            return null;
        }
        else document = DocumentHelper.parseText(xmlContent);
        return document.getRootElement().getText();
    }

    public String parseDocument(String xml){
        /**
         *  @Author: permission
         *  @Description: 解析xml文件
         *  @Date: 2019/9/7 19:53
         *  @Param [xml]
         *  @Return: void
         */
        org.w3c.dom.Element element=null;
        try {
            /* 作用： java.io.ByteArrayInputStream类的作用是将string转化为IS流,因为parse接口接收
            的是xml文件，文件输入的形式都是流，所以将字符串转换为流，一般是可以代替文件的效果的,但是，
            注意的是：xml解析一般是inputSource，而不是inputStream，前者会带着xml的格式化XSD，后者则
            没有，在解析xml格式的时候会报错，解析不了。但是，实际上。没有出现上述问题  */
            doc = builder.parse(new java.io.ByteArrayInputStream(xml.getBytes()));
            // 递归解析Element
            element = doc.getDocumentElement();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseElement(element);//这个类被重写了，原本的w3c库中就有一个这样的类了
    }
    public static String parseElement(org.w3c.dom.Element element){
        /* 作用：解析根节点   */
        /* 作用：递归解析子节点   */
        NodeList nodeList = element.getChildNodes();/* 作用：根据XSD结构获取子节点列表   */
        Node childNode=null;
        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            childNode = nodeList.item(temp);
            // 判断是否属于节点
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                // 判断是否还有子节点
                if(childNode.hasChildNodes()){
                    parseElement((org.w3c.dom.Element) childNode);
                } else if (childNode.getNodeType() != Node.COMMENT_NODE) {

                }
            }
        }
        return childNode.getTextContent();
    }
}
