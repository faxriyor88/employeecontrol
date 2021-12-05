package com.example.employeecontrol.service;

import com.example.employeecontrol.model.*;
import com.example.employeecontrol.repository.*;

import com.example.employeecontrol.response.ApiResponse;
import com.example.employeecontrol.response.DeleteImage;
import com.example.employeecontrol.response.UploadImageResponse;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import java.util.UUID;


@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeAdditionalRepository employeeAdditionalRepository;
    @Autowired
    InformationAboutRelativeRepository informationAboutRelativeRepository;
    @Autowired
    EmployeeService employeeService;


    //====== Xodim rasmini bazaga yuklash ======
    public UploadImageResponse uploadEmployeeImage(MultipartFile image) throws IOException {
        try {
            String savefileimage = UUID.randomUUID().toString();
            String[] split = image.getOriginalFilename().split("\\.");
            savefileimage = savefileimage + "." + split[split.length - 1];
            Path path = Paths.get("imagelocation/" + savefileimage);
            String imageUrl = "https://empproba.herokuapp.com/imagelocation/" + savefileimage;
            Files.copy(image.getInputStream(), path);
            return new UploadImageResponse(new ApiResponse("Success", true), imageUrl, savefileimage);
        } catch (Exception e) {
            return new UploadImageResponse(new ApiResponse("Xodim rasmini bazaga yuklab bo'lmadi", false), null, null);
        }
    }

    //====== Xodim rasmini tahrirlash =====
    public ApiResponse uploadEmployeeImageEdit(Attachment attachment1, Employee employee, MultipartFile image) throws IOException {
        try {
            Attachment attachment = attachment1;
            String savefileimage = UUID.randomUUID().toString();
            String[] split = image.getOriginalFilename().split("\\.");
            savefileimage = savefileimage + "." + split[split.length - 1];
            Path path = Paths.get("imagelocation/" + savefileimage);
            String imageUrl = "https://empproba.herokuapp.com/imagelocation/" + savefileimage;
            Files.copy(image.getInputStream(), path);
            attachment.setSaveimagename(savefileimage);
            attachment.setEmployee(employee);
            attachment.setContent_type(image.getContentType());
            attachment.setImageUrl(imageUrl);
            attachment.setFileOriginalname(image.getOriginalFilename());
            attachmentRepository.save(attachment);
            return new ApiResponse("Success", true);
        } catch (Exception e) {
            return new ApiResponse("Xodim rasmi bilan muammo bor", false);
        }
    }

    //===== Xodim ma'lumotlarini yuklash =====
    public ApiResponse downloadEmployee(UUID employeeId, HttpServletResponse response) {
        Manager managerInSystem = employeeService.getManagerInSystem();
        if (managerInSystem.getRole().getName().equals("DIRECTOR")) {
            Optional<Attachment> optionalAttachment = attachmentRepository.findByEmployeeId(employeeId);
            if (optionalAttachment.isPresent()) {
                try {
                    Attachment attachment = optionalAttachment.get();
                    Employee employee = employeeRepository.getById(employeeId);
                    File file = new File("informationaboutemployee/" + employee.getFullname() + attachment.getId() + ".docx");
                    file.delete();
                    EmployeeAdditional employeeAdditional = employeeAdditionalRepository.findByEmployeeId(employeeId);
                    List<InformationAboutRelative> informationAboutRelatives = informationAboutRelativeRepository.findAllByEmployeeId(employeeId);
                    boolean newFile = file.createNewFile();
                    if (newFile) {
                        getDocx(employee, employeeAdditional, file, informationAboutRelatives);
                        response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getName() + "\"");
                        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                        FileInputStream fileInputStream = new FileInputStream("informationaboutemployee/" + file.getName());
                     //   FileCopyUtils.copy(fileInputStream, response.getOutputStream());
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        FileReader fileReader = new FileReader(file);
                        fileInputStream.close();
                        fileOutputStream.close();
                        fileReader.close();
                        boolean delete = file.delete();
                        if (delete) {
                            return new ApiResponse("O'chdi", true);
                        }
                        return new ApiResponse("O'chmadi", false);
                    }
                    return new ApiResponse("Fayl ochib bo'lmadi", false);

                } catch (Exception e) {
                    return new ApiResponse("Error", false);
                }
            } else {
                return new ApiResponse("Bunday Attachment topilmadi", false);
            }
        }
        if (managerInSystem.getRole().getName().equals("REGION")) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
            if (optionalEmployee.isPresent()) {
                if (managerInSystem.getCompany().getId() == optionalEmployee.get().getCompany().getId()) {
                    Optional<Attachment> optionalAttachment = attachmentRepository.findByEmployeeId(employeeId);
                    if (optionalAttachment.isPresent()) {
                        try {
                            Attachment attachment = optionalAttachment.get();
                            Employee employee = employeeRepository.getById(employeeId);
                            File file = new File("informationaboutemployee/" + employee.getFullname() + attachment.getId() + ".docx");
                            file.delete();
                            EmployeeAdditional employeeAdditional = employeeAdditionalRepository.findByEmployeeId(employeeId);
                            List<InformationAboutRelative> informationAboutRelatives = informationAboutRelativeRepository.findAllByEmployeeId(employeeId);
                            boolean newFile = file.createNewFile();
                            if (newFile) {
                                getDocx(employee, employeeAdditional, file, informationAboutRelatives);
                                response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getName() + "\"");
                                response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                                FileInputStream fileInputStream = new FileInputStream("informationaboutemployee/" + file.getName());
                                FileCopyUtils.copy(fileInputStream, response.getOutputStream());
                                fileInputStream.close();
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                FileReader fileReader = new FileReader(file);
                                fileInputStream.close();
                                fileOutputStream.close();
                                fileReader.close();
                                boolean delete = file.delete();
                                if (delete) {
                                    return new ApiResponse("O'chdi", true);
                                }
                                return new ApiResponse("O'chmadi", false);
                            }
                            return new ApiResponse("Fayl ochib bo'lmadi", false);

                        } catch (Exception e) {
                            return new ApiResponse("Error", false);

                        }
                    } else {
                        return new ApiResponse("Bunaday Attachment topilmadi", false);
                    }
                } else {
                    return new ApiResponse("Sizda bunday huquq yo'q", false);
                }
            } else {
                return new ApiResponse("Bunday xodim topilmadi", false);
            }
        }
        return new ApiResponse("Forbidden", false);
    }

    //===== Xodim ma'lumotlarini docx qilib chiqarish ======
    public void getDocx(Employee employee, EmployeeAdditional employeeAdditional, File file, List<InformationAboutRelative> informationAboutRelative) {

        try {
            String lavozimivaQachondan = employee.getLavozimivaQachondan();
            String tugilganjoyi = employee.getDistrict().getRegion().getName() + " вилояти, " + employee.getDistrict().getDistrictname() + " тумани";
            String fullname = employee.getFullname();
            String tugilganyili = employee.getBirthday() + " йил";
            String millati = employeeAdditional.getNationality();
            String partiyaviyligi = employeeAdditional.getPartiyaviyligi();
            String malumoti = employeeAdditional.getMalumoti();
            String tamomlagan = employeeAdditional.getTamomlaganjoyi();
            String malumotiboyichamutax = employeeAdditional.getMalumotiboyichamutaxasisligi();
            String ilmiydaraja = employeeAdditional.getIlmiydarajasi();
            String ilmiyunvon = employeeAdditional.getIlmiyunvon();
            String chettillari = employeeAdditional.getChettillari();
            String harbiyunvon = employeeAdditional.getHarbiyunvoni();
            String davlatmukofoti = employeeAdditional.getDavlatmukofotibilantaqdirlanganligiqanaqa();
            String saylovorganiazosi = employeeAdditional.getSaylovorganiazosi();
            String mehnatfaoliyati = employeeAdditional.getMehnatfaoliyati();
            String[] empadd = {fullname, lavozimivaQachondan, tugilganyili, tugilganjoyi, millati, partiyaviyligi, malumoti, tamomlagan, malumotiboyichamutax, ilmiydaraja, ilmiyunvon, chettillari, harbiyunvon, davlatmukofoti, saylovorganiazosi, mehnatfaoliyati, fullname};
            String[] find = {"fullname", "lavozimi", "tugyili", "tug", "nation", "ayitrap", "lam", "momat", "lammut", "lirad", "liun", "tehclit", "rahun", "vadkum", "depxalq", "tanhem", "yak"};

            String pathOriginal = "src/main/java/com/example/employeecontrol/service/docxs/"; // path template
            String templateDoc = "Template.docx"; // original document will not be changed


            String tempPath = "src/main/java/com/example/employeecontrol/service/docxs/temp.docx";
            Path dirOrigem = Paths.get(pathOriginal + templateDoc);
            Path dirDestino = Paths.get(tempPath);


            Files.copy(dirOrigem, dirDestino, StandardCopyOption.REPLACE_EXISTING); // copy the template to temporary
            // directory
            int a = 0;
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(tempPath));
            for (XWPFParagraph p : xdoc.getParagraphs()) {
                for (XWPFRun r : p.getRuns()) {
                    if (r != null) {
                        String text = r.getText(0);
                        if (text != null && (text.contains("fullname") || text.contains("lavozimi")
                                || text.contains("tugyili") || text.contains("tug")
                                || text.contains("nation") || text.contains("ayitrap")
                                || text.contains("lam") || text.contains("momat") || text.contains("lammut")
                                || text.contains("lirad") || text.contains("liun")
                                || text.contains("tehclit") || text.contains("rahun")
                                || text.contains("vadkum") || text.contains("depxalq") || text.contains("tanhem") || text.contains("yak"))) {
                            text = text.replace(find[a], empadd[a]);
                            r.setText(text, 0);
                            a++;
                        }
                    }
                }
            }
            XWPFTable table = xdoc.createTable(1, 5);
            XWPFTableRow row = table.getRow(0);

            XWPFParagraph p1 = table.getRow(0).getCell(0).getParagraphs().get(0);
            p1.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r1 = p1.createRun();
            r1.setBold(true);
            r1.setText("Қариндошлиги");

            XWPFParagraph p2 = table.getRow(0).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r2 = p2.createRun();
            r2.setBold(true);
            r2.setText("Фамилияси, исми ва отасининг исми");

            XWPFParagraph p3 = table.getRow(0).getCell(2).getParagraphs().get(0);
            p3.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r3 = p3.createRun();
            r3.setBold(true);
            r3.setText("Туғилган йили ва жойи");

            XWPFParagraph p4 = table.getRow(0).getCell(3).getParagraphs().get(0);
            p4.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r4 = p4.createRun();
            r4.setBold(true);
            r4.setText("Иш жойи ва лавозими");

            XWPFParagraph p5 = table.getRow(0).getCell(4).getParagraphs().get(0);
            p5.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r5 = p5.createRun();
            r5.setBold(true);
            r5.setText("Турар жойи");

            for (int i = 1; i <= informationAboutRelative.size(); i++) {
                InformationAboutRelative rel = informationAboutRelative.get(i - 1);
                row = table.createRow();
                XWPFParagraph p6 = table.getRow(i).getCell(0).getParagraphs().get(0);
                p6.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun r6 = p6.createRun();
                r6.setBold(true);
                r6.setText(rel.getName());

                XWPFParagraph p7 = table.getRow(i).getCell(1).getParagraphs().get(0);
                p7.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun r7 = p7.createRun();
                r7.setText(rel.getRelativefullname());

                XWPFParagraph p8 = table.getRow(i).getCell(2).getParagraphs().get(0);
                p8.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun r8 = p8.createRun();
                r8.setText(rel.getBirthdayandbirthofplace());

                XWPFParagraph p9 = table.getRow(i).getCell(3).getParagraphs().get(0);
                p9.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun r9 = p9.createRun();
                r9.setText(rel.getIshjoyivalavozimi());

                XWPFParagraph p10 = table.getRow(i).getCell(4).getParagraphs().get(0);
                p10.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun r10 = p10.createRun();
                r10.setText(rel.getTurarjoyi());

            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            xdoc.write(fileOutputStream);
            xdoc.close();
            fileOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ==== Imageni content typeni aniqlash =======
    public int returnContentType(String imageContentType) {
        int imageType = XWPFDocument.PICTURE_TYPE_PNG;
        int[] type = {XWPFDocument.PICTURE_TYPE_PNG, XWPFDocument.PICTURE_TYPE_JPEG, XWPFDocument.PICTURE_TYPE_GIF,
                XWPFDocument.PICTURE_TYPE_BMP, XWPFDocument.PICTURE_TYPE_WPG, XWPFDocument.PICTURE_TYPE_DIB,
                XWPFDocument.PICTURE_TYPE_EMF, XWPFDocument.PICTURE_TYPE_EPS, XWPFDocument.PICTURE_TYPE_TIFF, XWPFDocument.PICTURE_TYPE_PICT, XWPFDocument.PICTURE_TYPE_WMF};
        String[] types = {"png,", "jpeg", "gif", "bmp", "wpg", "dib", "emf", "eps", "tiff", "pict", "wmf"};
        for (int i = 0; i < types.length; i++) {
            if (types[i].contains(imageContentType)) {
                imageType = type[i];
                break;
            }
        }
        return imageType;
    }

    //===== Image o'chirilganini bilish ===========
    public DeleteImage knowToDelete(Employee employee) {
        Optional<Attachment> attachmentOptional = attachmentRepository.findByEmployeeId(employee.getId());
        if (attachmentOptional.isPresent()) {
            try {
                Attachment attachment = attachmentOptional.get();
                Path paths = Paths.get("imagelocation/" + attachment.getSaveimagename());
                Files.delete(paths);
                return new DeleteImage(new ApiResponse("O'chirildi", true), attachment);
            } catch (Exception e) {
                return new DeleteImage(new ApiResponse("O'chirilmadi", false), null);
            }
        }
        return new DeleteImage(new ApiResponse("Bunday rasm topilmadi", false), null);
    }
}