package com.anyview.dto.response;

import java.io.Serializable;
import java.util.Date;

/**
 * 储存虚拟目录和题目名称的类
 * 前五个字段来源于schemeContent表 后几个字段来源于exercise表
 * @author 杨坚新
 *
 */
public class CatalogInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer vid;
    // 原题ID
    private Integer pid;
    // 章名
    private String chapName;
    // 题目名
    private String pName;
    // 开始时间
    private Date startTime;
    // 结束时间
    private Date finishTime;

    // 编辑过后,习题内容的ID
    private Long eid;
    // 分值
    private Double score;
    // 运行结果
    private Integer runResult;
    // 运行错误次数
    private Integer runErrCount;
    // 编译次数
    private Integer cmpCount;
    // 编译正确次数
    private Integer cmpRightCount;
    // 编译错误次数
    private Integer cmpErrorCount;
    // 首次通过时间
    private Date firstPastTime;
    // 最后提交时间
    private Date lastTime;
    // 更新时间
    private Date updateTime;

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getChapName() {
        return chapName;
    }

    public void setChapName(String chapName) {
        this.chapName = chapName;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getRunResult() {
        return runResult;
    }

    public void setRunResult(Integer runResult) {
        this.runResult = runResult;
    }

    public Integer getRunErrCount() {
        return runErrCount;
    }

    public void setRunErrCount(Integer runErrCount) {
        this.runErrCount = runErrCount;
    }

    public Integer getCmpCount() {
        return cmpCount;
    }

    public void setCmpCount(Integer cmpCount) {
        this.cmpCount = cmpCount;
    }

    public Integer getCmpRightCount() {
        return cmpRightCount;
    }

    public void setCmpRightCount(Integer cmpRightCount) {
        this.cmpRightCount = cmpRightCount;
    }

    public Integer getCmpErrorCount() {
        return cmpErrorCount;
    }

    public void setCmpErrorCount(Integer cmpErrorCount) {
        this.cmpErrorCount = cmpErrorCount;
    }

    public Date getFirstPastTime() {
        return firstPastTime;
    }

    public void setFirstPastTime(Date firstPastTime) {
        this.firstPastTime = firstPastTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }



    public CatalogInfo() {

    }

    public CatalogInfo(Integer vid, Integer pid, String chapName, String pName, Date startTime, Date finishTime,
                       Long eid, Double score, Integer runResult, Integer runErrCount, Integer cmpCount, Integer cmpRightCount,
                       Integer cmpErrorCount, Date firstPastTime, Date lastTime, Date updateTime) {
        this.vid = vid;
        this.pid = pid;
        this.chapName = chapName;
        this.pName = pName;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.eid = eid;
        this.score = score;
        this.runResult = runResult;
        this.runErrCount = runErrCount;
        this.cmpCount = cmpCount;
        this.cmpRightCount = cmpRightCount;
        this.cmpErrorCount = cmpErrorCount;
        this.firstPastTime = firstPastTime;
        this.lastTime = lastTime;
        this.updateTime = updateTime;
    }

    public CatalogInfo(Integer pid, String chapName, String pName, Double score, Date startTime, Date finishTime,
                       Date updateTime) {
        this.pid = pid;
        this.chapName = chapName;
        this.pName = pName;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.score = score;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CatalogInfo [vid=" + vid + ", pid=" + pid + ", chapName=" + chapName + ", pName=" + pName
                + ", startTime=" + startTime + ", finishTime=" + finishTime + ", eid=" + eid + ", score=" + score
                + ", runResult=" + runResult + ", runErrCount=" + runErrCount + ", cmpCount=" + cmpCount
                + ", cmpRightCount=" + cmpRightCount + ", cmpErrorCount=" + cmpErrorCount + ", firstPastTime="
                + firstPastTime + ", lastTime=" + lastTime + ", updateTime=" + updateTime + "]";
    }

}
