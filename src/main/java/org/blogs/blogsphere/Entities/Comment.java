package org.blogs.blogsphere.Entities;

import org.blogs.blogsphere.EntityListeners.CreatedListener;
import org.blogs.blogsphere.Enum.EStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
@EntityListeners(CreatedListener.class)
public class Comment extends BaseEntity
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private EStatus status;

    @ManyToOne
    private Post post;

    public Comment(String content, EStatus status, Post post) {
        this.content = content;
        this.status = status;
        this.post = post;
    }

}
