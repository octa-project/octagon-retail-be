package octagon.retail.repository;

import octagon.retail.entity.Sales;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleRepository extends MainRepository<Sales, Long> {

//    @Query("select new mn.octagon.userservice.model.UserModel(a.id, a.first_name, a.last_name) from Users a")
//    List<UserModel> getUserList();
}