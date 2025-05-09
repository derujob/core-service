package edu.bk.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromptRequest implements Serializable {
    private static final long serialVersionUID = -9040509530831203839L;

    private String promptRequest;
}