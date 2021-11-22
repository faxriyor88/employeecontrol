package com.example.employeecontrol.aop;

import com.example.employeecontrol.exceptions.ResourceNdException;
import com.example.employeecontrol.model.Manager;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class CheckPermissionExecuter {

    @Before(value = "@annotation(checkPermission)")
    public void check(CheckPermission checkPermission){
        String permission= checkPermission.permission();
        String permission1= checkPermission.permission1();
        Manager manager=(Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean checkker=false;
        for (GrantedAuthority grantedAuthority1:manager.getAuthorities()) {
           if(grantedAuthority1.getAuthority().equals(permission)||grantedAuthority1.getAuthority().equals(permission1)){
               checkker=true;
               break;
           }
        }
        if (!checkker)
            throw new ResourceNdException("Sizda bunday huquq yo'q");
    }
}
