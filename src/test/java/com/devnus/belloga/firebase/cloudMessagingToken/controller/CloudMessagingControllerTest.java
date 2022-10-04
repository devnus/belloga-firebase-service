package com.devnus.belloga.firebase.cloudMessagingToken.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
@EmbeddedKafka(
        brokerProperties = {
                "listeners=PLAINTEXT://localhost:9092"
        },
        ports = { 9092 })
class CloudMessagingControllerTest {
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private ObjectMapper objectMapper;

        @Test
        @DisplayName("사용자의 토큰 update db에 저장 성공 테스트")
        void updateUserFcmTokenSuccessTest () throws Exception {
                String userId = "gildong";
                String fcmToken = "blahhhhhh";
                Map<String, String> input = new HashMap<>();
                input.put("token", fcmToken);

                mockMvc.perform(RestDocumentationRequestBuilders.put("/api/cloud-messaging-token/v1/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(input))
                                .header("labeler-id", userId) // 라벨링 수행하는 유저의 식별아이디, api gateway에서 받아온다.
                        )

                        .andExpect(status().isOk())
                        .andDo(print())
                        .andDo(document("update-fcm-token",
                                responseFields(
                                        fieldWithPath("id").description("logging을 위한 api response 고유 ID"),
                                        fieldWithPath("dateTime").description("response time"),
                                        fieldWithPath("success").description("정상 응답 여부"),
                                        fieldWithPath("response").description("성공여부"),
                                        fieldWithPath("error").description("error 발생 시 에러 정보")
                                )
                        ));
        }

}
