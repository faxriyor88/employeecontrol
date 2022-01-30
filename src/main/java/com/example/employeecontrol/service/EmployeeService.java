
package com.example.employeecontrol.service;

import com.example.employeecontrol.dto.EmployeeAdditonalDTO;
import com.example.employeecontrol.dto.EmployeeDto;
import com.example.employeecontrol.dto.InformationAboutRelativeDTO;
import com.example.employeecontrol.dto.MehnatFaoliyatiDto;
import com.example.employeecontrol.model.*;
import com.example.employeecontrol.repository.*;
import com.example.employeecontrol.response.ApiResponse;
import com.example.employeecontrol.response.DeleteImage;
import com.example.employeecontrol.response.RegionAppropriateDistrict;
import com.example.employeecontrol.response.UploadImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    InformationAboutRelativeRepository aboutRelativeRepository;
    @Autowired
    EmployeeAdditionalRepository employeeAdditionalRepository;
    @Autowired
    AttachmentService attachmentService;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MehnatFaoliyatiRepository mehnatFaoliyatiRepository;

    //====== DIRECTOR VA REGION XODIM QO'SHISHI ======
    public ApiResponse addEmployee(EmployeeDto employeeDto, MultipartFile image) throws IOException {
        if (getManagerInSystem().getRole().getName().equals("DIRECTOR")) {
            if (image != null && image.getContentType().startsWith("image/")) {
                RegionAppropriateDistrict redis = regionAppropriateDistrict(regionRepository.findById(employeeDto.getRegionId()), districtRepository.findById(employeeDto.getDistrictId()));
                if (redis.isSuccess()) {
                    Optional<Company> optionalCompany = companyRepository.findById(employeeDto.getCompanyId());
                    if (optionalCompany.isPresent()) {
                        District district = redis.getDistrict();
                        Company company = optionalCompany.get();
                        if (!employeeRepository.existsByFullname(employeeDto.getFullname())) {
                            Employee employee = new Employee(employeeDto.getFullname(), district, company, employeeDto.getBirthday(), employeeDto.getLavozimivaQachondan());
                            EmployeeAdditional employeeAdditional = new EmployeeAdditional(employeeDto.getNationality(),
                                    employeeDto.getMalumoti(), employeeDto.getMalumotiboyichamutaxasisligi(),
                                    employeeDto.getIlmiydarajasi(), employeeDto.getIlmiyunvoni(), employeeDto.getChettillari(),
                                    employeeDto.getDavlatmukofotibilantaqdirlanganligiqanaqa(), employeeDto.getSaylovorganiazosi(),
                                    employeeDto.getPartiyaviyligi(), employeeDto.getTamomlaganjoyi(), employeeDto.getHarbiyunvoni(),
                                    employee);
                            UploadImageResponse imageResponse = attachmentService.uploadEmployeeImage(image);
                            if (imageResponse.getResponse().isSuccess()) {
                                employeeRepository.save(employee);
                                Attachment attachment = new Attachment(image.getOriginalFilename(), image.getContentType(), imageResponse.getSavefileimage(), imageResponse.getImageUrl(), employee);
                                attachmentRepository.save(attachment);
                                List<MehnatFaoliyati> arrayList=new ArrayList<>();
                                for (MehnatFaoliyatiDto m:employeeDto.getMehnatfaoliyati()) {
                                    MehnatFaoliyati mehnatFaoliyati=new MehnatFaoliyati(m.getText(),employee);
                                    arrayList.add(mehnatFaoliyati);
                                }
                                mehnatFaoliyatiRepository.saveAll(arrayList);
                                employeeAdditionalRepository.save(employeeAdditional);
                                for (InformationAboutRelativeDTO i : employeeDto.getAboutRelative()) {
                                    InformationAboutRelative informationAboutRelative = new InformationAboutRelative(
                                            i.getName(), i.getRelativefullname(), i.getBirthdayandbirthofplace(), i.getIshjoyivalavozimi(),
                                            i.getTurarjoyi(), employee);
                                    aboutRelativeRepository.save(informationAboutRelative);
                                }
                                return new ApiResponse("Saqlandi", true);
                            } else {
                                return imageResponse.getResponse();
                            }
                        } else {
                            return new ApiResponse("Bunday fullname bor", false);
                        }
                    } else {
                        return new ApiResponse("Bunday Companiya topilmadi", false);
                    }
                } else {
                    return new ApiResponse(redis.getMessage(), false);
                }
            } else {
                return new ApiResponse("Image tanlanmagan", false);
            }
        }
        if (getManagerInSystem().getRole().getName().equals("REGION")) {
            System.out.println(getManagerInSystem().getRole().getName());
            if (image != null && image.getContentType().startsWith("image/")) {
                RegionAppropriateDistrict redis = regionAppropriateDistrict(regionRepository.findById(employeeDto.getRegionId()), districtRepository.findById(employeeDto.getDistrictId()));
                if (redis.isSuccess()) {
                    Optional<Company> optionalCompany = companyRepository.findById(employeeDto.getCompanyId());
                    if (optionalCompany.isPresent()) {
                        Company company = optionalCompany.get();
                        if (getManagerInSystem().getCompany().getId() == company.getId()) {
                            if (!employeeRepository.existsByFullnameAndCompanyId(employeeDto.getFullname(), employeeDto.getCompanyId())) {
                                District district = redis.getDistrict();
                                Employee employee = new Employee(employeeDto.getFullname(), district, company, employeeDto.getBirthday(), employeeDto.getLavozimivaQachondan());
                                EmployeeAdditional employeeAdditional = new EmployeeAdditional(employeeDto.getNationality(),
                                        employeeDto.getMalumoti(), employeeDto.getMalumotiboyichamutaxasisligi(),
                                        employeeDto.getIlmiydarajasi(), employeeDto.getIlmiyunvoni(), employeeDto.getChettillari(),
                                        employeeDto.getDavlatmukofotibilantaqdirlanganligiqanaqa(), employeeDto.getSaylovorganiazosi(),
                                        employeeDto.getPartiyaviyligi(), employeeDto.getTamomlaganjoyi(), employeeDto.getHarbiyunvoni(), employee);

                                UploadImageResponse imageResponse = attachmentService.uploadEmployeeImage(image);
                                if (imageResponse.getResponse().isSuccess()) {
                                    employeeRepository.save(employee);
                                    Attachment attachment = new Attachment(image.getOriginalFilename(), image.getContentType(), imageResponse.getSavefileimage(), imageResponse.getImageUrl(), employee);
                                    attachmentRepository.save(attachment);
                                    List<MehnatFaoliyati> arrayList=new ArrayList<>();
                                    for (MehnatFaoliyatiDto m:employeeDto.getMehnatfaoliyati()) {
                                        MehnatFaoliyati mehnatFaoliyati=new MehnatFaoliyati(m.getText(),employee);
                                        arrayList.add(mehnatFaoliyati);
                                    }
                                    mehnatFaoliyatiRepository.saveAll(arrayList);
                                    employeeAdditionalRepository.save(employeeAdditional);
                                    for (InformationAboutRelativeDTO i : employeeDto.getAboutRelative()) {
                                        InformationAboutRelative informationAboutRelative = new InformationAboutRelative(
                                                i.getName(), i.getRelativefullname(), i.getBirthdayandbirthofplace(), i.getIshjoyivalavozimi(),
                                                i.getTurarjoyi(), employee);
                                        aboutRelativeRepository.save(informationAboutRelative);
                                    }

                                    return new ApiResponse("Saqlandi", true);
                                } else {
                                    return imageResponse.getResponse();
                                }
                            } else {
                                return new ApiResponse("Bunday fullname bor", false);
                            }
                        } else {
                            return new ApiResponse("Siz xodimni bu companyga qo'shaolmaysiz", false);
                        }
                    } else {
                        return new ApiResponse("Bunday Companiya topilmadi", false);
                    }
                } else {
                    return new ApiResponse(redis.getMessage(), false);
                }
            } else {
                return new ApiResponse("Image tanlanmagan", false);
            }
        }

        return new ApiResponse("Sizda bunday huquq yo'q", false);
    }

    //====== DIRECTOR VA REGION BARCHA XODIMLARNI KO'RISHI ======
    public Page<Employee> getAllEmployee(Integer pagenumber) {
        Manager manager = getManagerInSystem();
        if (manager.getRole().getName().equals("DIRECTOR")) {
            Pageable pageable = PageRequest.of(pagenumber, 10);
            return employeeRepository.findAll(pageable);
        }
        if (manager.getRole().getName().equals("REGION")) {
            Pageable pageable = PageRequest.of(pagenumber, 10);
            return employeeRepository.findAllByCompanyId(manager.getCompany().getId(), pageable);
        }
        return null;
    }

    //===== DIRECTOR VA REGION XODIMLARNI QIDIRISHI
    //1.Ism bo'yicha qidirish
    public List<Employee> findByName(String fullname) {
        Manager manager = getManagerInSystem();
        if (manager.getRole().getName().equals("DIRECTOR")) {
            return employeeRepository.findAllByFullnameContaining(fullname);
        }
        if (manager.getRole().getName().equals("REGION")) {
           return employeeRepository.findAllByFullnameContainingAndCompanyId(fullname, manager.getCompany().getId());
        }
        return new ArrayList<>();
    }

    //2.Company bo'yicha qidirish
    public List<Employee> findByCompany(String companyName) {
        Manager manager = getManagerInSystem();
        if (manager.getRole().getName().equals("DIRECTOR")) {
            return employeeRepository.findByCompany_CompanynameContaining(companyName);
        }
        if (manager.getRole().getName().equals("REGION")) {
            return employeeRepository.findCompanyNameAndCompanyId(manager.getCompany().getId(),companyName);
        }
        return new ArrayList<>();
    }

    //3.Lavozim bo'yicha qidirish
    public List<Employee> findByPosition(String lavozimi) {
        Manager manager = getManagerInSystem();
        if (manager.getRole().getName().equals("DIRECTOR")) {
            return employeeRepository.findAllByLavozimivaQachondanContaining(lavozimi);
        }
        if (manager.getRole().getName().equals("REGION")) {
            return employeeRepository.findAllByLavozimivaQachondanContainingAndCompanyId(lavozimi, manager.getCompany().getId());
        }
        return new ArrayList<>();
    }


    //====== DIRECTOR VA REGION XODIMLARNI TAHRIRLASHI ======
    public ApiResponse editEmployee(UUID employeeId, EmployeeDto employeeDto, MultipartFile image) throws IOException {
        Manager managerInSystem = getManagerInSystem();
        if (managerInSystem.getRole().getName().equals("DIRECTOR")) {
            if (image != null && image.getContentType().startsWith("image/")) {
                Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
                if (optionalEmployee.isPresent()) {
                    Employee employee = optionalEmployee.get();
                    String fullname = employee.getFullname();
                    List<Employee> employeeList = employeeRepository.findAll();
                    boolean avai = true;
                    for (Employee e : employeeList) {
                        if (!e.getId().equals(employeeId)) {
                            if (e.getFullname().equals(employeeDto.getFullname())) {
                                avai = false;

                            }
                        }
                    }
                    if (avai) {
                        RegionAppropriateDistrict redis = regionAppropriateDistrict(regionRepository.findById(employeeDto.getRegionId()), districtRepository.findById(employeeDto.getDistrictId()));
                        if (redis.isSuccess()) {
                            Optional<Company> optionalCompany = companyRepository.findById(employeeDto.getCompanyId());
                            if (optionalCompany.isPresent()) {

                                Company company = optionalCompany.get();
                                employee.setFullname(employeeDto.getFullname());
                                employee.setDistrict(redis.getDistrict());
                                employee.setCompany(company);
                                employee.setBirthday(employeeDto.getBirthday());
                                employee.setLavozimivaQachondan(employeeDto.getLavozimivaQachondan());
                                EmployeeAdditional employeeAdditional = employeeAdditionalRepository.findByEmployeeId(employee.getId());
                                employeeAdditional.setEmployee(employee);
                                employeeAdditional.setChettillari(employeeDto.getChettillari());
                                employeeAdditional.setDavlatmukofotibilantaqdirlanganligiqanaqa(employeeDto.getDavlatmukofotibilantaqdirlanganligiqanaqa());
                                employeeAdditional.setHarbiyunvoni(employeeDto.getHarbiyunvoni());
                                employeeAdditional.setIlmiydarajasi(employeeDto.getIlmiydarajasi());
                                employeeAdditional.setIlmiyunvon(employeeDto.getIlmiyunvoni());
                                employeeAdditional.setMalumoti(employeeDto.getMalumoti());
                                employeeAdditional.setMalumotiboyichamutaxasisligi(employeeDto.getMalumotiboyichamutaxasisligi());
                                employeeAdditional.setNationality(employeeDto.getNationality());
                                employeeAdditional.setPartiyaviyligi(employeeDto.getPartiyaviyligi());
                                employeeAdditional.setSaylovorganiazosi(employeeDto.getSaylovorganiazosi());
                  //              employeeAdditional.setMehnatfaoliyati(employeeDto.getMehnatfaoliyati());
                                employeeAdditional.setTamomlaganjoyi(employeeDto.getTamomlaganjoyi());
                                DeleteImage deleteImage = attachmentService.knowToDelete(employee);
                                if (deleteImage.getResponse().isSuccess()) {
                                    File file = new File("informationaboutemployee/" + fullname + deleteImage.getAttachment().getId() + ".docx");
                                    file.delete();
                                    employeeRepository.save(employee);
                                    ApiResponse apiResponse = attachmentService.uploadEmployeeImageEdit(deleteImage.getAttachment(), employee, image);
                                    if (apiResponse.isSuccess()) {
                                        List<MehnatFaoliyati> arrayList=new ArrayList<>();
                                        for (MehnatFaoliyatiDto m:employeeDto.getMehnatfaoliyati()) {
                                            MehnatFaoliyati mehnatFaoliyati=new MehnatFaoliyati(m.getText(),employee);
                                            arrayList.add(mehnatFaoliyati);
                                        }
                                        mehnatFaoliyatiRepository.deleteAll(mehnatFaoliyatiRepository.findAllByEmployeeId(employeeId));
                                        mehnatFaoliyatiRepository.saveAll(arrayList);
                                        employeeAdditionalRepository.save(employeeAdditional);
                                        List<InformationAboutRelative> list = new ArrayList<>();
                                        for (InformationAboutRelativeDTO i : employeeDto.getAboutRelative()) {
                                            InformationAboutRelative informationAboutRelative = new InformationAboutRelative();
                                            informationAboutRelative.setName(i.getName());
                                            informationAboutRelative.setRelativefullname(i.getRelativefullname());
                                            informationAboutRelative.setBirthdayandbirthofplace(i.getBirthdayandbirthofplace());
                                            informationAboutRelative.setIshjoyivalavozimi(i.getIshjoyivalavozimi());
                                            informationAboutRelative.setTurarjoyi(i.getTurarjoyi());
                                            informationAboutRelative.setEmployee(employee);
                                            list.add(informationAboutRelative);
                                        }
                                        aboutRelativeRepository.deleteAll(aboutRelativeRepository.findAllByEmployeeId(employeeId));
                                        aboutRelativeRepository.saveAll(list);
                                        return new ApiResponse("Tahrirlandi", true);
                                    } else {
                                        return apiResponse;
                                    }
                                } else {
                                    return deleteImage.getResponse();
                                }
                            } else {
                                return new ApiResponse("Bunday company topildi", false);
                            }
                        } else {
                            return new ApiResponse(redis.getMessage(), false);
                        }
                    } else {
                        return new ApiResponse("Bunday fullname bor", false);
                    }
                } else {
                    return new ApiResponse("Bunday employee topilmadi", false);
                }
            } else {
                return new ApiResponse("Image tanlanmagan", false);
            }
        }

        if (managerInSystem.getRole().getName().equals("REGION")) {
            if (image != null && image.getContentType().startsWith("image/")) {
                Optional<Employee> optionalEmployee = employeeRepository.findByIdAndCompanyId(employeeId, managerInSystem.getCompany().getId());
                if (optionalEmployee.isPresent()) {
                    Employee employee = optionalEmployee.get();
                    String fullname = employee.getFullname();
                    if (!employeeRepository.existsByFullnameNotAndCompanyId(employee.getFullname(), employee.getCompany().getId())) {
                        RegionAppropriateDistrict redis = regionAppropriateDistrict(regionRepository.findById(employeeDto.getRegionId()), districtRepository.findById(employeeDto.getDistrictId()));
                        if (redis.isSuccess()) {
                            if (managerInSystem.getCompany().getId() == employeeDto.getCompanyId()) {
                                Optional<Company> optionalCompany = companyRepository.findById(employeeDto.getCompanyId());
                                if (optionalCompany.isPresent()) {
                                    Company company = optionalCompany.get();
                                    List<Employee> employeeList = employeeRepository.findAllByCompanyId(company.getId());
                                    boolean avai = true;
                                    for (Employee e : employeeList) {
                                        if (!e.getId().equals(employeeId)) {
                                            if (e.getFullname().equals(employeeDto.getFullname())) {
                                                avai = false;
                                            }
                                        }
                                    }
                                    if (avai) {
                                        employee.setFullname(employeeDto.getFullname());
                                        employee.setDistrict(redis.getDistrict());
                                        employee.setCompany(company);
                                        employee.setBirthday(employeeDto.getBirthday());
                                        employee.setLavozimivaQachondan(employeeDto.getLavozimivaQachondan());
                                        EmployeeAdditional employeeAdditional = employeeAdditionalRepository.findByEmployeeId(employee.getId());
                                        employeeAdditional.setEmployee(employee);
                                        employeeAdditional.setChettillari(employeeDto.getChettillari());
                                        employeeAdditional.setDavlatmukofotibilantaqdirlanganligiqanaqa(employeeDto.getDavlatmukofotibilantaqdirlanganligiqanaqa());
                                        employeeAdditional.setHarbiyunvoni(employeeDto.getHarbiyunvoni());
                                        employeeAdditional.setIlmiydarajasi(employeeDto.getIlmiydarajasi());
                                        employeeAdditional.setMalumoti(employeeDto.getMalumoti());
                                        employeeAdditional.setMalumotiboyichamutaxasisligi(employeeDto.getMalumotiboyichamutaxasisligi());
                                        employeeAdditional.setNationality(employeeDto.getNationality());
                                        employeeAdditional.setPartiyaviyligi(employeeDto.getPartiyaviyligi());
                                        employeeAdditional.setSaylovorganiazosi(employeeDto.getSaylovorganiazosi());
                                     //   employeeAdditional.setMehnatfaoliyati(employeeDto.getMehnatfaoliyati());
                                        employeeAdditional.setTamomlaganjoyi(employeeDto.getTamomlaganjoyi());
                                        DeleteImage deleteImage = attachmentService.knowToDelete(employee);
                                        if (deleteImage.getResponse().isSuccess()) {
                                            File file = new File("informationaboutemployee/" + fullname + deleteImage.getAttachment().getId() + ".docx");
                                            file.delete();
                                            employeeRepository.save(employee);
                                            ApiResponse apiResponse = attachmentService.uploadEmployeeImageEdit(deleteImage.getAttachment(), employee, image);
                                            if (apiResponse.isSuccess()) {
                                                List<MehnatFaoliyati> arrayList=new ArrayList<>();
                                                for (MehnatFaoliyatiDto m:employeeDto.getMehnatfaoliyati()) {
                                                    MehnatFaoliyati mehnatFaoliyati=new MehnatFaoliyati(m.getText(),employee);
                                                    arrayList.add(mehnatFaoliyati);
                                                }
                                                mehnatFaoliyatiRepository.deleteAll(mehnatFaoliyatiRepository.findAllByEmployeeId(employeeId));
                                                mehnatFaoliyatiRepository.saveAll(arrayList);
                                                employeeAdditionalRepository.save(employeeAdditional);
                                                List<InformationAboutRelative> list = new ArrayList<>();
                                                for (InformationAboutRelativeDTO i : employeeDto.getAboutRelative()) {
                                                    InformationAboutRelative informationAboutRelative = new InformationAboutRelative();
                                                    informationAboutRelative.setName(i.getName());
                                                    informationAboutRelative.setRelativefullname(i.getRelativefullname());
                                                    informationAboutRelative.setBirthdayandbirthofplace(i.getBirthdayandbirthofplace());
                                                    informationAboutRelative.setIshjoyivalavozimi(i.getIshjoyivalavozimi());
                                                    informationAboutRelative.setTurarjoyi(i.getTurarjoyi());
                                                    informationAboutRelative.setEmployee(employee);
                                                    list.add(informationAboutRelative);
                                                    //  aboutRelativeRepository.save(informationAboutRelative);

                                                }
                                                aboutRelativeRepository.deleteAll(aboutRelativeRepository.findAllByEmployeeId(employeeId));
                                                aboutRelativeRepository.saveAll(list);

                                                return new ApiResponse("Tahrirlandi", true);
                                            } else {
                                                return apiResponse;
                                            }
                                        } else {
                                            return deleteImage.getResponse();
                                        }
                                    } else {
                                        return new ApiResponse("Bunday fullname bor", false);
                                    }
                                } else {
                                    return new ApiResponse("Bunday company topilmadi", false);
                                }
                            } else {
                                return new ApiResponse("Siz xodimni bu companyga qo'shaolmaysiz", false);
                            }
                        } else {
                            return new ApiResponse(redis.getMessage(), false);
                        }
                    } else {
                        return new ApiResponse("Bunday fullname bor", false);
                    }
                } else {
                    return new ApiResponse("Bunday employee topilmadi", false);
                }

            } else {
                return new ApiResponse("Image tanlanmagan", false);
            }
        }
        return new ApiResponse("Sizda bunday huquq yo'q", false);
    }

    //======= DIRECTOR VA REGION XODIMLARNI O'CHIRISHI ======
    public ApiResponse deleteEmployee(UUID id) {
        Manager managerInSystem = getManagerInSystem();
        if (managerInSystem.getRole().getName().equals("DIRECTOR")) {
            if (employeeRepository.existsById(id)) {
                Attachment attachment = attachmentRepository.getByEmployeeId(id);
                File file = new File("imagelocation/" + attachment.getSaveimagename());
                file.delete();
                employeeRepository.deleteById(id);
                return new ApiResponse("O'chirildi", true);
            } else {
                return new ApiResponse("Bunday xodim topilmadi", false);
            }
        }
        if (managerInSystem.getRole().getName().equals("REGION")) {
            if (employeeRepository.existsByCompanyIdAndId(managerInSystem.getCompany().getId(), id)) {
                Attachment attachment = attachmentRepository.getByEmployeeId(id);
                File file = new File("imagelocation/" + attachment.getSaveimagename());
                file.delete();
                employeeRepository.deleteById(id);
                return new ApiResponse("O'chirildi", true);
            } else {
                return new ApiResponse("Bunday xodim topilmadi", false);
            }
        }
        return new ApiResponse("Sizda bunday huquq yo'q", false);
    }

    //====== DIRECTOR VA REGION XODIM HAQIDA QO'SHIMCHA MA'LUMOT OLISHI ======
    public EmployeeAdditonalDTO getEmployeeAdditional(UUID id) {
        Manager managerInSystem = getManagerInSystem();
        if (managerInSystem.getRole().getName().equals("DIRECTOR")) {
            Optional<Employee> employeeOptional = employeeRepository.findById(id);
            if (employeeOptional.isPresent()) {
                EmployeeAdditional e = employeeAdditionalRepository.findByEmployeeId(id);
                List<InformationAboutRelative> list = aboutRelativeRepository.findAllByEmployeeId(id);
                List<InformationAboutRelativeDTO> list1 = new ArrayList<>();
                for (InformationAboutRelative i : list) {
                    InformationAboutRelativeDTO in = new InformationAboutRelativeDTO(i.getName(), i.getRelativefullname(),
                            i.getBirthdayandbirthofplace(), i.getIshjoyivalavozimi(), i.getTurarjoyi());
                    list1.add(in);
                }
                List<MehnatFaoliyatiDto> mehnatFaoliyatiDtos=new ArrayList<>();
                for (MehnatFaoliyati m:mehnatFaoliyatiRepository.findAllByEmployeeId(id)) {
                    MehnatFaoliyatiDto mehnatFaoliyatiDto=new MehnatFaoliyatiDto(m.getText());
                    mehnatFaoliyatiDtos.add(mehnatFaoliyatiDto);
                }
                Attachment imageUrl = attachmentRepository.getByEmployeeId(id);
                EmployeeAdditonalDTO employeeAdditonalDTO = new EmployeeAdditonalDTO(list1,e.getNationality(),e.getMalumoti(),
                        e.getMalumotiboyichamutaxasisligi(),e.getIlmiydarajasi(),e.getIlmiyunvon(),
                        e.getChettillari(),e.getDavlatmukofotibilantaqdirlanganligiqanaqa(),e.getSaylovorganiazosi(),
                        e.getPartiyaviyligi(),e.getTamomlaganjoyi(),e.getHarbiyunvoni(),mehnatFaoliyatiDtos,imageUrl.getImageUrl());

                return employeeAdditonalDTO;

            } else {
                return new EmployeeAdditonalDTO();
            }
        }
        if (managerInSystem.getRole().getName().equals("REGION")) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);
            if (optionalEmployee.isPresent()) {
                EmployeeAdditional e = employeeAdditionalRepository.findByEmployeeId(id);
                List<InformationAboutRelative> list = aboutRelativeRepository.findAllByEmployeeId(id);
                List<InformationAboutRelativeDTO> list1 = new ArrayList<>();
                for (InformationAboutRelative i : list) {
                    InformationAboutRelativeDTO in = new InformationAboutRelativeDTO(i.getName(), i.getRelativefullname(),
                            i.getBirthdayandbirthofplace(), i.getIshjoyivalavozimi(), i.getTurarjoyi());
                    list1.add(in);
                }
                List<MehnatFaoliyatiDto> mehnatFaoliyatiDtos=new ArrayList<>();
                for (MehnatFaoliyati m:mehnatFaoliyatiRepository.findAllByEmployeeId(id)) {
                    MehnatFaoliyatiDto mehnatFaoliyatiDto=new MehnatFaoliyatiDto(m.getText());
                    mehnatFaoliyatiDtos.add(mehnatFaoliyatiDto);
                }
                Attachment imageUrl = attachmentRepository.getByEmployeeId(id);
                EmployeeAdditonalDTO employeeAdditonalDTO = new EmployeeAdditonalDTO(list1, e.getNationality(), e.getMalumoti(),
                        e.getMalumotiboyichamutaxasisligi(), e.getIlmiydarajasi(),e.getIlmiyunvon(), e.getChettillari(), e.getDavlatmukofotibilantaqdirlanganligiqanaqa(),
                        e.getSaylovorganiazosi(), e.getPartiyaviyligi(), e.getTamomlaganjoyi(), e.getHarbiyunvoni(),mehnatFaoliyatiDtos, imageUrl.getImageUrl());
                return employeeAdditonalDTO;
            }
        }
        return null;
    }


    //======= REGIONNI DISTRICTGA MOSLIGI ======
    public RegionAppropriateDistrict regionAppropriateDistrict(Optional<Region> region, Optional<District> district) {
        Optional<Region> optionalRegion = region;
        if (optionalRegion.isPresent()) {
            Optional<District> optionalDistrict = district;
            if (optionalDistrict.isPresent()) {
                Region region1 = optionalRegion.get();
                District district1 = optionalDistrict.get();
                if (district1.getRegion().getId() == region1.getId()) {
                    return new RegionAppropriateDistrict(region1, district1, true);
                } else {
                    return new RegionAppropriateDistrict("district bu regionga tegishli emas", false);
                }
            } else {
                return new RegionAppropriateDistrict("Bunday district topilmadi", false);
            }
        } else {
            return new RegionAppropriateDistrict("Bunday region topilmadi", false);
        }

        //

    }

    //======= TIZIMDAGI MANAGERNI ANIQLASH =======
    public Manager getManagerInSystem() {
        Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return manager;
    }
}