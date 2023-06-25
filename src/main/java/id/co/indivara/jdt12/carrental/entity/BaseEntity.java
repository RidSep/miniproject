package id.co.indivara.jdt12.carrental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @JsonIgnore
    @Column(name = "created_date")
    private Date createdDate;
//    @JsonIgnore
//    @Column(name = "created_by")
//    private String createdBy;
    @JsonIgnore
    @Column(name = "last_update_date")
    private Date lastUpdateDate;
//    @JsonIgnore
//    @Column(name = "last_update_by")
//    private String lastUpdateBy;

    @PrePersist
    private void onCreate() {
        this.createdDate = new Date();
        this.lastUpdateDate = this.createdDate;
    }

    @PreUpdate
    private void onUpdate() {
        this.lastUpdateDate = new Date();
    }

}
