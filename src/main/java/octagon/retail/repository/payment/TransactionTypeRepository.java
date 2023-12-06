package octagon.retail.repository.payment;

import octagon.retail.entity.payment.TransactionType;
import octagon.retail.repository.MainRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionTypeRepository extends MainRepository<TransactionType, Long> {
    @Query("select T from TransactionType T where T.name=:name")
    TransactionType exist(String name);
    @Query("select T from TransactionType T where T.isActive=true and T.parent=null")
    List<TransactionType> getZero();
//    @Query("select T from TransactionType T where T.isActive=true and T.parentId=:parentId")
//    List<TransactionType> getParent(Long parentId);
}
