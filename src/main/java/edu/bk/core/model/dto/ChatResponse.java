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
public class ChatResponse implements Serializable {
    private static final long serialVersionUID = -3032677937770824288L;
    private String judulTantanganHarian;
    private String deskripsi;
    private String jumlah;
    private String tabungan;
    private String dorongan;
    private String validDate;
}