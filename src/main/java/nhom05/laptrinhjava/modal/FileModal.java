package nhom05.laptrinhjava.modal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "filemodal")
public class FileModal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(name = "name")
    String fileName;
    @Lob
    @Column(length = 16777215)
    String content;
    @Column(name = "filetype")
    private String fileType;

}