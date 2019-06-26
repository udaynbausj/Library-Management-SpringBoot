package io.code.lms.Entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    private Date issuedOn;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "num_of_times_renewed")
    private Integer numOfTimesRenewed;

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
    public Date getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(Date issuedOn) {
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

    public Integer getNumOfTimesRenewed() {
        return numOfTimesRenewed;
    }

    public void setNumOfTimesRenewed(Integer numOfTimesRenewed) {
        this.numOfTimesRenewed = numOfTimesRenewed;
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
                ", numOfTimesRenewed=" + numOfTimesRenewed +
                '}';
    }
}
