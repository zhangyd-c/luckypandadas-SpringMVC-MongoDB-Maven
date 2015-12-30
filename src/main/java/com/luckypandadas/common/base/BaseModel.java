package com.luckypandadas.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Foghost
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1034671331121855153L;

    /**
     * 主键
     */
    public String id;

    /**
     * 创建时间
     */
    public Date createdTime;

    /**
     * 更新时间
     */
    public Date updatedTime;

    /**
     * 1-正常0-删除，详见ConstantType:STATUS
     */
    public Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
