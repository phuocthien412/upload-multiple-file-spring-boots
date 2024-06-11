package nhom05.laptrinhjava.controllers;


import nhom05.laptrinhjava.modal.FileModal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import nhom05.laptrinhjava.services.FileServiceImplementation;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileController {
    @Autowired
    FileServiceImplementation fileServiceImplementation;

    @GetMapping("/")
    public String getData() {
        return "File";
    }

    @PostMapping("/")
    public String uploadMultipartFile(@RequestParam("files") MultipartFile[] files, Model modal) {

        try {
            List<FileModal> fileList = new ArrayList<FileModal>();
            // luu file vao duong dan
            String uploadDir = "src/main/resources/static/uploads/";
            for (MultipartFile file : files) {
                String fileContentType = file.getContentType();
                //Lấy nội dung file dưới dạng chuỗi
                String sourceFileContent = new String(file.getBytes(), StandardCharsets.UTF_8);

                String fileName = file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir + fileName);
                //filePath.getParent() lấy đường dẫn của thư mục chứa tệp.
                // Files.createDirectories đảm bảo rằng tất cả các thư mục cha trong đường dẫn này được tạo ra nếu chúng chưa tồn tại.
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, file.getBytes());


                Files.write(filePath, file.getBytes());
                FileModal fileModal = new FileModal();
                fileModal.setFileName(fileName);
                fileModal.setContent(sourceFileContent);
                fileModal.setFileType(fileContentType);
                fileList.add(fileModal);
            }
            fileServiceImplementation.saveAllFilesList(fileList);

        } catch (Exception e) {
            e.printStackTrace();
            modal.addAttribute("message", "An error occurred while uploading files: " + e.getMessage());
            return "error";
        }
        modal.addAttribute("allFiles", fileServiceImplementation.getAllFiles());
        return "FileList";
    }
    @PostMapping("/delete")
    public String deleteFile(@RequestParam("id") long id, Model model) {
        try {
            fileServiceImplementation.deleteFileById(id);
            model.addAttribute("message", "File deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("message",
                    "An error occurred while deleting the file: " + e.getMessage());
            return "error";
        }

        model.addAttribute("allFiles", fileServiceImplementation.getAllFiles());
        return "FileList";
    }
    @PostMapping("/deleteAll")
    public String deleteAllFiles(Model modal) {
        fileServiceImplementation.deleteAllFiles();
        try{
            fileServiceImplementation.deleteAllFiles();
        }catch(Exception e){
            e.printStackTrace();
        }
        modal.addAttribute("allFiles", fileServiceImplementation.getAllFiles());
        return "FileList";
    }

}