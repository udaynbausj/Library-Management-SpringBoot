package io.code.lms.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "scholarid_bookid_mapping")
public class BookIdScholarIdMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @NotEmpty(message = "bookId cannot be null")
    private Integer bookId;

    @NotEmpty(message = "scholarId cannot be null ")
    private Integer scholarId;

    @NotEmpty(message = "issuedon cannot be null ")
    private Integer issuedOn;
    private Date createdAt;
    private Date updatedAt;

    public BookIdScholarIdMapping() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getScholarId() {
        return scholarId;
    }

    public void setScholarId(Integer scholarId) {
        this.scholarId = scholarId;
    }

    public Integer getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(Integer issuedOn) {
        this.issuedOn = issuedOn;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "BookIdScholarIdMapping{" +
                "Id=" + Id +
                ", bookId=" + bookId +
                ", scholarId=" + scholarId +
                ", issuedOn=" + issuedOn +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
