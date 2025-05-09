package edu.bk.core.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PingRequest implements Serializable {
    private static final long serialVersionUID = 3493983925666887237L;
    String ping;
}