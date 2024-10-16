package org.blogs.blogsphere.Entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.blogs.blogsphere.EntityListeners.CreatedListener;
import org.blogs.blogsphere.Enum.EStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name="post")
@EntityListeners(CreatedListener.class)
public class Post extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name="featured_image", nullable = false)
    @Lob
    private byte[] featuredImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EStatus status;
    
    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "post_category",
        joinColumns = @JoinColumn(name="post_id"),
        inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Post(String title, String content, byte[] featuredImage, EStatus status, Set<Category> categories) {
        this.title = title;
        this.content = content;
        this.featuredImage = featuredImage;
        this.status = status;
        this.categories = categories;
    }

    
}
