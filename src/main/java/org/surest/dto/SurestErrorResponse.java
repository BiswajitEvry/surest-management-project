package org.surest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurestErrorResponse {

    private int status;
    private String error;
    private String message;
    private String path;
}
