package edu.bk.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bk.core.constant.PromptConstant;
import edu.bk.core.model.PingRequest;
import edu.bk.core.model.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CoreService {

    @Autowired(required = false)
    private RestClient restClient;

    @Value("${openapi.api.key}")
    private String apiKey;

    public List<ChatResponse> chatResponses = new ArrayList<>();

    public List<ChatResponse> getAllChallenge(){
        return chatResponses;
    }

    public String pong(PingRequest request){
        return "pong";
    }

    public ChatResponse getChallenge(ChatRequest request) throws JsonProcessingException {
        var chatResponse = ChatResponse.builder().build();
        if (StringUtils.equals(request.getUserId(), "1")){
            ObjectMapper objectMapper = new ObjectMapper();

            chatResponse = objectMapper.readValue(getChatResponse(), ChatResponse.class);
            chatResponses.add(chatResponse);
        }else {
            chatResponses.add(ChatResponse.builder()
                    .judulTantanganHarian("Hoodie Hari Ini, Emas Besok – Hemat Rp120.000, Wujudkan dengan Cicil Emas!")
                    .deskripsi("Hari ini kamu memanjakan diri dengan hoodie baru seharga Rp120.000—gaya kamu makin keren! Tapi yuk, tantang diri sendiri untuk menahan belanja fashion selama satu hari saja. Sebagai gantinya, alihkan jumlah yang sama ke tabungan masa depan. Siapa tahu, hoodie hari ini bisa jadi awal dari lembaran menabung yang lebih keren lagi.")
                    .jumlah("Rp120.000")
                    .tabungan("Cicil Emas")
                    .dorongan("Langkah kecil hari ini bisa jadi lompatan besar untuk masa depanmu—ayo mulai tantangannya!")
                    .validDate(getTodayEndOfDay())
                    .build());

            chatResponses.add(ChatResponse.builder()
                    .judulTantanganHarian("Sandal Flash Sale? Saatnya Flash Save Rp150.000 dengan Cicil Emas!")
                    .deskripsi("Hari ini kamu menangkap momen flash sale dan mendapatkan sandal baru seharga Rp150.000—deal yang menggoda! Sekarang, mari coba tantangan kecil: lewatkan godaan flash sale berikutnya dan alihkan dananya ke tabungan. Siapa tahu, kamu bisa punya 'koleksi emas' selain koleksi fashion!")
                    .jumlah("Rp150.000")
                    .tabungan("Cicil Emas")
                    .dorongan("Kamu sudah jago belanja pintar, kini saatnya jadi jago menabung juga!")
                    .validDate(getTodayEndOfDay())
                    .build());
        }
        System.out.println("Chat response: "+chatResponse);

        return chatResponse;
    }

    public String getChatResponse() {
        ChatGPTRequest request = new ChatGPTRequest(
                "gpt-4o-mini",
                List.of(new ChatGPTRequest.Message("user", PromptConstant.PROMPT_1))
        );

        ChatGPTResponse response = restClient.post()
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(request)
                .retrieve()
                .body(ChatGPTResponse.class);

//        log.info(response.choices().get(0).message().content());
        System.out.println("GPT: "+response.choices().get(0).message().content());

        return response.choices().get(0).message().content();
    }

    public ChatResponse getMission(ChatRequest request){
        if (request.getUserId().equals("1")){
            return ChatResponse.builder()
                    .judulTantanganHarian("Hoodie Hari Ini, Emas Besok – Hemat Rp120.000, Wujudkan dengan Cicil Emas!")
                    .deskripsi("Hari ini kamu memanjakan diri dengan hoodie baru seharga Rp120.000—gaya kamu makin keren! Tapi yuk, tantang diri sendiri untuk menahan belanja fashion selama satu hari saja. Sebagai gantinya, alihkan jumlah yang sama ke tabungan masa depan. Siapa tahu, hoodie hari ini bisa jadi awal dari lembaran menabung yang lebih keren lagi.")
                    .jumlah("Rp120.000")
                    .tabungan("Cicil Emas")
                    .dorongan("Langkah kecil hari ini bisa jadi lompatan besar untuk masa depanmu—ayo mulai tantangannya!")
                    .validDate(getTodayEndOfDay())
                    .build();
        } else if (request.getUserId().equals("2")) {
            return ChatResponse.builder()
                    .judulTantanganHarian("Sandal Flash Sale? Saatnya Flash Save Rp150.000 dengan Cicil Emas!")
                    .deskripsi("Hari ini kamu menangkap momen flash sale dan mendapatkan sandal baru seharga Rp150.000—deal yang menggoda! Sekarang, mari coba tantangan kecil: lewatkan godaan flash sale berikutnya dan alihkan dananya ke tabungan. Siapa tahu, kamu bisa punya 'koleksi emas' selain koleksi fashion!")
                    .jumlah("Rp150.000")
                    .tabungan("Cicil Emas")
                    .dorongan("Kamu sudah jago belanja pintar, kini saatnya jadi jago menabung juga!")
                    .validDate(getTodayEndOfDay())
                    .build();
        } else {
            return ChatResponse.builder()
                    .judulTantanganHarian("")
                    .deskripsi("")
                    .jumlah("")
                    .tabungan("")
                    .dorongan("")
                    .validDate("")
                    .build();
        }
    }

    public String getTodayEndOfDay(){
        LocalDate today = LocalDate.now();

        // Set time to 23:59:59
        LocalDateTime endOfDay = LocalDateTime.of(today, LocalTime.of(23, 59, 59));

        // Define the custom format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM hh:mm:ss");

        // Format the date-time
        return endOfDay.format(formatter);
    }
}