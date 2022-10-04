package com.devnus.belloga.firebase.cloudMessagingToken.controller;

import com.devnus.belloga.firebase.cloudMessagingToken.dto.RequestCloudMessagingToken;
import com.devnus.belloga.firebase.cloudMessagingToken.service.CloudMessagingTokenService;
import com.devnus.belloga.firebase.common.aop.annotation.GetAccountIdentification;
import com.devnus.belloga.firebase.common.aop.annotation.UserRole;
import com.devnus.belloga.firebase.common.dto.CommonResponse;
import com.devnus.belloga.firebase.common.exception.error.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cloud-messaging-token")
public class CloudMessagingTokenController {
    private final CloudMessagingTokenService cloudMessagingTokenService;

    /**
     * 모든 사용자 유형의 토큰을 등록한다.
     * API Gateway 에서 토큰을 까서 보내주므로 각 userId를 확인해 null이 아닌 경우 그걸 role로 지정한다.
     * @param adminId
     * @param labelerId
     * @param enterpriseId
     * @param updateTokenDto
     * @return
     */
    @PutMapping("/v1/update")
    public ResponseEntity<CommonResponse> updateToken(@GetAccountIdentification(role = UserRole.ADMIN) String adminId,
                                                      @GetAccountIdentification(role = UserRole.LABELER) String labelerId,
                                                      @GetAccountIdentification(role = UserRole.ENTERPRISE) String enterpriseId,
                                                      @Valid @RequestBody RequestCloudMessagingToken.UpdateToken updateTokenDto) {

        if(labelerId != null) {
            cloudMessagingTokenService.updateUserToken(labelerId, UserRole.LABELER, updateTokenDto.getToken());
        } else if(adminId != null) {
            cloudMessagingTokenService.updateUserToken(adminId, UserRole.ADMIN, updateTokenDto.getToken());
        } else if(enterpriseId != null) {
            cloudMessagingTokenService.updateUserToken(enterpriseId, UserRole.ENTERPRISE, updateTokenDto.getToken());
        } else {
            throw new NotFoundUserException();
        }

        return new ResponseEntity<>(CommonResponse.builder()
                .response("성공")
                .build(), HttpStatus.OK);
    }
}
