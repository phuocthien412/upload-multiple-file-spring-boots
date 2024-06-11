package nhom05.laptrinhjava.services;


import nhom05.laptrinhjava.modal.FileModal;

import java.util.List;

public interface FileService {
    List<FileModal> getAllFiles();
    void saveAllFilesList(List<FileModal> fileList);
    void deleteFileById(Long id);
    void deleteAllFiles();
}