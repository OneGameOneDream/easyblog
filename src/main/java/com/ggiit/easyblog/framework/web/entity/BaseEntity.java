package com.ggiit.easyblog.framework.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Entity基类
 *
 * @author gao
 * @date 2019.4.23
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public abstract class BaseEntity implements Serializable {
    /**
     * 编号
     */
    @Column(name = "ID_", unique = true, nullable = false, length = 64)
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    /**
     * 创建者
     */
    @Column(name = "CREATE_BY_", length = 64)
    @CreatedBy
    private String createBy;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME_")
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 修改者
     */
    @Column(name = "UPDATE_BY_", length = 64)
    @LastModifiedBy
    private String updateBy;
    /**
     * 修改时间
     */
    @Column(name = "UPDATE_TIME_")
    @Temporal(value = TemporalType.TIMESTAMP)
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 备注
     */
    @Column(name = "REMARK_")
    private String remark;

    /**
     * 删除标识：1启用、0删除
     */
    @Column(name = "DEL_FLAG_", length = 1)
    private Boolean delFlag;


}




