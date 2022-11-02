package com.shopping.wx.managed_mapper.ryd;

import com.shopping.base.foundation.data.BaseDao;
import com.shopping.base.foundation.result.QueryResult;
import com.shopping.wx.model.Organ;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository("rydOrganDAO")
public class RydOrganDAO extends BaseDao<Organ, String> {

    public QueryResult<Organ> queryOrganVOByBankId(String bankId) {
       return super.query(
               (Specification<Organ>) (root, query, criteriaBuilder) -> query
                       .multiselect(
                               root.get("bankid"),
                               root.get("organname")
                       ).where(
                               criteriaBuilder.equal(
                                       root.get("id"),
                                       bankId
                               )
                       ).getRestriction()
       );
    }

    public QueryResult<Organ> queryAllOrganVO(){
        return super.query(
                (Specification<Organ>) (root, query, criteriaBuilder) -> query
                        .multiselect(root.get("id"),root.get("organname"))
                        .getRestriction()
        );
    }
}
