package com.Library;

import java.time.LocalDate;
import java.util.List;

public class CheckOutService extends GenericDao<CheckOut> {

    public List<Reader> whoIsOverDue(){
        List<Reader> readers = (List<Reader>) entityManager.createQuery("select r from Reader r join r.checkOuts c where c.dueDate < :now")
                .setParameter("now", LocalDate.now()).getResultList();
        return readers;
    }
}
