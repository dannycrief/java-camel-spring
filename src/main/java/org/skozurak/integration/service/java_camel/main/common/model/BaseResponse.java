package org.skozurak.integration.service.java_camel.main.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Service status")
public class BaseResponse {

    public static final String STATUS_OK = "OK";

    public static final String STATUS_ERROR = "ERROR";

    @ApiModelProperty(value = "Service status")
    private String status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "Error details", required = false)
    private String errorDesc;
}
