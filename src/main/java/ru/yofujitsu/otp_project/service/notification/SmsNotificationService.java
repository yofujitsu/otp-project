package ru.yofujitsu.otp_project.service.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsmpp.bean.*;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.yofujitsu.otp_project.entity.User;

@Service("SMS")
@RequiredArgsConstructor
@Slf4j
public class SmsNotificationService implements CodeNotificationService {

    @Value("${smpp.host}")
    private String host;
    @Value("${smpp.port}")
    private int port;
    @Value("${smpp.system_id}")
    private String systemId;
    @Value("${smpp.password}")
    private String password;
    @Value("${smpp.system_type}")
    private String systemType;
    @Value("${smpp.source_addr}")
    private String sourceAddr;


    @Override
    public void sendCode(User user, String code) {
        SMPPSession session = new SMPPSession();
        try {
            session.connectAndBind(host, port,
                    new BindParameter(
                            BindType.BIND_TX,
                            systemId,
                            password,
                            systemType,
                            TypeOfNumber.UNKNOWN,
                            NumberingPlanIndicator.UNKNOWN,
                            sourceAddr
                    ));
            session.submitShortMessage(
                    systemType,
                    TypeOfNumber.UNKNOWN,
                    NumberingPlanIndicator.UNKNOWN,
                    sourceAddr,
                    TypeOfNumber.UNKNOWN,
                    NumberingPlanIndicator.UNKNOWN,
                    user.getPhone(),
                    new ESMClass(),
                    (byte) 0,
                    (byte) 1,
                    null,
                    null,
                    new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT),
                    (byte) 0,
                    new GeneralDataCoding(Alphabet.ALPHA_DEFAULT),
                    (byte) 0,
                    ("Your OTP Verification code: " + code).getBytes()
            );
            log.info("Sent request with OTP code to mobile phone");
        } catch (Exception e) {
            throw new RuntimeException("SMS failed", e);
        } finally {
            session.unbindAndClose();
        }
    }
}
