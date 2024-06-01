package org.blogs.blogsphere.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    
    @ManyToOne
    @JoinColumn(name="created_by")
    private User createdBy;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;
}
