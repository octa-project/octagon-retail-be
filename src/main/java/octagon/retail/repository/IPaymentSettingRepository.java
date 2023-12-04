package octagon.retail.repository;

import octagon.retail.entity.PaymentSetting;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentSettingRepository extends MainRepository<PaymentSetting, Long> {
}
