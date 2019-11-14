package dlnu.o2oboot.entity;

import java.util.Date;

public class HeadLine {
    private Long lineId;

    private String lineName;
    private String lineImg;
    private String lineLink;

    private Integer priority;

    private Integer enableStatus;

    private Date createTime;
    private Date lastEditTime;

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public HeadLine(String lineName, String lineImg, String lineLink, Integer priority, Date createTime, Date lastEditTime) {
        this.lineName = lineName;
        this.lineImg = lineImg;
        this.lineLink = lineLink;
        this.priority = priority;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
    }

    public HeadLine() {
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public void setLineImg(String lineImg) {
        this.lineImg = lineImg;
    }

    public void setLineLink(String lineLink) {
        this.lineLink = lineLink;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Long getLineId() {
        return lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public String getLineImg() {
        return lineImg;
    }

    public String getLineLink() {
        return lineLink;
    }

    public Integer getPriority() {
        return priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }
}
