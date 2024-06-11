package nhom05.laptrinhjava.repository;


import nhom05.laptrinhjava.modal.FileModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileModal, Long> {
}
