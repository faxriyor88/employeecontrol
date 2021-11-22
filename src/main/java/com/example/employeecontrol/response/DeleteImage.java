package com.example.employeecontrol.response;

import com.example.employeecontrol.model.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteImage {
    private ApiResponse response;
    private Attachment attachment;
}
