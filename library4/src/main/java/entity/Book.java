package entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Base64;

@Data
@Entity
@Table(name = "book")
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "genre_id", nullable = false)
    private Long genreId;
    @Basic
    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "author", nullable = false, length = 100)
    private String author;
    @Basic
    @Column(name = "publisher", length = 100)
    private String publisher;
    @Basic
    @Column(name = "year")
    private Integer year;
    @Basic
    @Column(name = "lang")
    private String language;
    @Basic
    @Column(name = "image")
    private byte[] image;
    @Basic
    @Column(name = "description", length = -1)
    private String description;
    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Genre genreByGenreId;
    @Transient
    private String base64Image;

    public String getBase64Image() {
        base64Image = Base64.getEncoder().encodeToString(this.image);
        return base64Image;
    }

    public String getDescription() {
        return description.replaceAll("\n", "<br/>");
    }

}
