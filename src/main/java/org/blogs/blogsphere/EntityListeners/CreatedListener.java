package org.blogs.blogsphere.EntityListeners;

import java.util.Date;

import org.blogs.blogsphere.Entities.BaseEntity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.PrePersist;

@EntityListeners(CreatedListener.class)
public class CreatedListener {
    

    @PrePersist
    public void BeforePersist(Object entity)
    {
        if(entity instanceof BaseEntity)
        {
            BaseEntity baseEntity = (BaseEntity) entity;

            baseEntity.setCreatedAt(new Date());
        }
    }
}
