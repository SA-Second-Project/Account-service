package miu.edu.account.client;

import miu.edu.account.model.BankInfo;
import miu.edu.account.model.PayPalInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("Payment-service")
@Component
public interface PaymentClient {

    @RequestMapping(method = RequestMethod.POST, value = "/Payment/bankInfo/{id}")
    BankInfo addBankInfo(@RequestBody BankInfo bankInfo, Long id);

    @RequestMapping(method = RequestMethod.POST, value = "/Payment/PayPal/{id}")
    PayPalInfo addPayPal(@RequestBody PayPalInfo payPalInfo, Long id);


}
