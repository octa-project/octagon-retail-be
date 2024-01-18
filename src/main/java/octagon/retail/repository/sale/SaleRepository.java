package octagon.retail.repository.sale;

import octagon.retail.entity.Sales;
import octagon.retail.repository.MainRepository;
import octagon.retail.utils.SaleType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends MainRepository<Sales, Long> {

        @Query("SELECT s FROM Sales s WHERE s.type = :type")
        List<Sales> findByType(SaleType type);

        @Query("SELECT a FROM Sales a WHERE a.createdDate BETWEEN :startDate AND :endDate AND a.type = 1")
        List<Sales> getManyByDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

        @Query("select sum(s.totalAmount) as total_amount from Sales s where s.isPaid= true and  s.createdDate = :date")
        Object getTotalAmountByDate(Date date);

        @Query("select sum(si.qty*(ic.sellPrice-ic.costPrice)) AS profit\n" +
                        "from SaleItems si\n" +
                        "    left join Sales s\n" +
                        "        on si.saleId = s.id\n" +
                        "            left join ItemCodes ic\n" +
                        "                on si.itemCodeId = ic.itemId\n" +
                        "            where s.isPaid = true and  DATE(s.createdDate) = :date")
        Object getProfitByDate(Date date);

        @Query("select sum(s.totalQty)from Sales s where s.isPaid = true and  DATE(s.createdDate) = :date")
        Object getTotalQuantityByDate(Date date);

}