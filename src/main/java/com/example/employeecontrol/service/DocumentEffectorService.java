package com.example.employeecontrol.service;

import com.example.employeecontrol.dto.DocumentEffectorDto;
import com.example.employeecontrol.dto.DocumentEffectorDtoResponse;
import com.example.employeecontrol.model.DeadLineType;
import com.example.employeecontrol.model.DocumentContent;
import com.example.employeecontrol.model.DocumentEffector;
import com.example.employeecontrol.model.Manager;
import com.example.employeecontrol.repository.DocumentContentRepository;
import com.example.employeecontrol.repository.DocumentEffectorRepository;
import com.example.employeecontrol.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentEffectorService {
    @Autowired
    DocumentEffectorRepository documentEffectorRepository;
    @Autowired
    DocumentContentRepository documentContentRepository;
    @Autowired
    EmployeeService employeeService;

    //===== Document qo'shish =======
//    public ApiResponse addDocument(MultipartFile file, DocumentEffectorDto documentEffectorDto) throws IOException {
//        Manager managerInSystem = employeeService.getManagerInSystem();
//        if (managerInSystem.getRole().getName().equals("PERFORM")) {
//            if (file != null) {
//                DocumentEffectorDto d = documentEffectorDto;
//                LocalDate now = LocalDate.now();
//                if (d.getExecutionDeadline().isAfter(now)) {
//                    DocumentEffector documentEffector = new DocumentEffector(d.getTaskSection(), d.getDocumentType(),
//                            d.getDocumentNumber(), d.getDocumentDate(), d.getRegistrDay(), d.getRegistrNumber(),
//                            d.getDocumentNameOrContent(), d.getOrganizationName(), d.getDirectorResolution(),
//                            managerInSystem, d.getEffectorSection(), d.getExecutionDeadline());
//                    documentEffectorRepository.save(documentEffector);
//                    String savefile = UUID.randomUUID().toString();
//                    String[] split = file.getOriginalFilename().split("\\.");
//                    savefile = savefile + "." + split[split.length - 1];
//                    DocumentContent documentContent = new DocumentContent(file.getBytes(), file.getOriginalFilename(), savefile, documentEffector);
//                    documentContentRepository.save(documentContent);
//                    return new ApiResponse("Saqlandi", true);
//                }
//                return new ApiResponse("Xato!!! Deadline joriy vaqtdan oldinga qo'yilyapti", false);
//            }
//            return new ApiResponse("File tanlanmagan", false);
//
//        }
//        return new ApiResponse("Sizda bunday huquq yo'q", false);
//    }
//
//
//    //=======Documentni ko'rish=========
//    public Page<DocumentEffectorDtoResponse> viewDocument(Integer pageNumber,String deadLineType){
//        Pageable pageable= PageRequest.of(pageNumber,10);
//        LocalDate localDate=LocalDate.now();
//        Manager manager= employeeService.getManagerInSystem();
//        //Muddati o'tgan
//        if (deadLineType.equals(DeadLineType.RED.name())){
//            Page<DocumentEffector> deadlineBefore = documentEffectorRepository.findAllByEffectorNameAndExecutionDeadlineBefore(manager, localDate, pageable);
//            List<DocumentContent> contents = documentContentRepository.findAllByDocumentEffectorContaining((List<DocumentEffector>) deadlineBefore);
//List<DocumentEffectorDtoResponse> dtoResponses=new ArrayList<>();
//            int a=0;
//            for (DocumentEffector d:deadlineBefore) {
//                LocalDate localDate1=d.getExecutionDeadline();
//                int deadlineDay=(int)localDate.until(localDate1, ChronoUnit.DAYS);
//                DocumentEffectorDtoResponse e=new DocumentEffectorDtoResponse(d.getUuid(),d.getTaskSection(),d.getDocumentType(),d.getDocumentNumber(),
//                        d.getDocumentDate(),d.getRegistrDay(),d.getRegistrNumber(),d.getDocumentNameOrContent(),d.getOrganizationName(),
//                        d.getDirectorResolution(), (manager.getSurname()+" "+manager.getName()),d.getEffectorSection(),
//                        localDate1,deadlineDay,contents.get(a).getOriginalFileName());
//                dtoResponses.add(e);
//            }
//            return (Page<DocumentEffectorDtoResponse>) dtoResponses;
//        }
//        //Muddati kelgan
//        if (deadLineType.equals(DeadLineType.YELLOW.name())){
//            Page<DocumentEffector> deadlineBefore = documentEffectorRepository.findAllByEffectorNameAndExecutionDeadline(manager, localDate, pageable);
//            List<DocumentContent> contents = documentContentRepository.findAllByDocumentEffectorContaining((List<DocumentEffector>) deadlineBefore);
//            List<DocumentEffectorDtoResponse> dtoResponses=new ArrayList<>();
//            int a=0;
//            for (DocumentEffector d:deadlineBefore) {
//                LocalDate localDate1=d.getExecutionDeadline();
//                int deadlineDay=(int)localDate.until(localDate1, ChronoUnit.DAYS);
//                DocumentEffectorDtoResponse e=new DocumentEffectorDtoResponse(d.getUuid(),d.getTaskSection(),d.getDocumentType(),d.getDocumentNumber(),
//                        d.getDocumentDate(),d.getRegistrDay(),d.getRegistrNumber(),d.getDocumentNameOrContent(),d.getOrganizationName(),
//                        d.getDirectorResolution(), (manager.getSurname()+" "+manager.getName()),d.getEffectorSection(),
//                        localDate1,deadlineDay,contents.get(a).getOriginalFileName());
//                dtoResponses.add(e);
//            }
//            return (Page<DocumentEffectorDtoResponse>) dtoResponses;
//        }
//        //Muddati bor
//        if (deadLineType.equals(DeadLineType.GREEN.name())){
//            Page<DocumentEffector> deadlineBefore = documentEffectorRepository.findAllByEffectorNameAndExecutionDeadlineAfter(manager, localDate, pageable);
//            List<DocumentContent> contents = documentContentRepository.findAllByDocumentEffectorContaining((List<DocumentEffector>) deadlineBefore);
//            List<DocumentEffectorDtoResponse> dtoResponses=new ArrayList<>();
//            int a=0;
//            for (DocumentEffector d:deadlineBefore) {
//                LocalDate localDate1=d.getExecutionDeadline();
//                int deadlineDay=(int)localDate.until(localDate1, ChronoUnit.DAYS);
//                DocumentEffectorDtoResponse e=new DocumentEffectorDtoResponse(d.getUuid(),d.getTaskSection(),d.getDocumentType(),d.getDocumentNumber(),
//                        d.getDocumentDate(),d.getRegistrDay(),d.getRegistrNumber(),d.getDocumentNameOrContent(),d.getOrganizationName(),
//                        d.getDirectorResolution(), (manager.getSurname()+" "+manager.getName()),d.getEffectorSection(),
//                        localDate1,deadlineDay,contents.get(a).getOriginalFileName());
//                dtoResponses.add(e);
//            }
//            return (Page<DocumentEffectorDtoResponse>) dtoResponses;
//        }
//        return null;
//    }

}
