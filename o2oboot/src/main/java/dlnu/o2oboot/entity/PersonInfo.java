package dlnu.o2oboot.entity;

import java.util.Date;

public class PersonInfo {
    private Long personId;
    private String name;
    private String profileImg;
    private String email;
    private String gender;
    private Integer enableStatus;


    //1顾客 2店家 3root
    private Integer userType;

    private Date createTime;
    private Date lastEditTime;

    public PersonInfo(){}
    public PersonInfo(String name, String profileImg, String email, String gender, Integer enableStatus, Integer userType, Date createTime, Date lastEditTime) {
        this.name = name;
        this.profileImg = profileImg;
        this.email = email;
        this.gender = gender;
        this.enableStatus = enableStatus;
        this.userType = userType;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Long getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }
}
