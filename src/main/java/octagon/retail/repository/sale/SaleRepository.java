package octagon.retail.repository.sale;

import octagon.retail.entity.sale.Sales;
import octagon.retail.repository.MainRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface SaleRepository extends MainRepository<Sales, Long> {
    @Query("select a from Sales a where a.isDeleted=false and a.date between :startDate and :endDate")
    List<Sales> getMany(Date startDate,Date endDate);

   @Query("select sum(s.totalAmount) as total_amount from Sales s where s.isPaid= true and  DATE(s.date) = :date")
   Object getTotalAmountByDate(Date date);

   @Query("select sum(si.qty*(ic.sellPrice-ic.purchasePrice)) AS profit\n" +
           "from SaleItems si\n" +
           "    left join Sales s\n" +
           "        on si.saleId = s.id\n" +
           "            left join ItemCodes ic\n" +
           "                on si.itemCodeId = ic.itemId\n" +
           "            where s.isPaid = true and  DATE(s.date) = :date")
   Object getProfitByDate(Date date);
   @Query("select sum(s.totalQty)from Sales s where s.isPaid = true and  DATE(s.date) = :date")
   Object getTotalQuantityByDate(Date date);

}