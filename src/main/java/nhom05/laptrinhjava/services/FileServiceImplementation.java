package nhom05.laptrinhjava.services;

import nhom05.laptrinhjava.modal.FileModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nhom05.laptrinhjava.repository.FileRepository;

import java.util.List;

@Service
public class FileServiceImplementation implements FileService {
    @Autowired
    FileRepository fileRepository;

    @Override
    public List<FileModal> getAllFiles() {
        // fetch all the files form database
        return fileRepository.findAll();
    }
    public void saveAllFilesList(List<FileModal> fileList) {
        fileRepository.saveAll(fileList);
    }
    public void deleteFileById(Long id) {
        fileRepository.deleteById(id);
    }
    public void deleteAllFiles() {
        fileRepository.deleteAll();
    }
}