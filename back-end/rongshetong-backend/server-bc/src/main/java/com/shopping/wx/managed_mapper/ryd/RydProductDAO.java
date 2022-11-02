package com.shopping.wx.managed_mapper.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.base.foundation.data.BaseDao;
import com.shopping.base.foundation.form.PaginationForm;
import com.shopping.base.foundation.result.PaginationResult;
import com.shopping.base.foundation.result.QueryResult;
import com.shopping.wx.model.Product;
import com.shopping.wx.object.finance_market.condition.SortCondition;
import com.shopping.wx.object.finance_market.condition.filter_condition.FilterConditions;
import com.shopping.wx.object.finance_market.condition.filter_condition.FilterStrategy;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository("rydProductDAO")
public class RydProductDAO extends BaseDao<Product, String> {

    public PaginationResult<Product> queryByCondition(JSONObject condition) throws IllegalArgumentException {
        PaginationForm page = condition.getObject("page", PaginationForm.class);

        if (page == null) {
            throw new IllegalArgumentException("parameter Page is missing");
        }

        return super.query((Specification<Product>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            String keyword = condition.getString("keyword");
            if (StringUtils.isNotEmpty(keyword)) {
                predicates.add(keywordPredicate(root, criteriaBuilder, keyword));
            }

            JSONObject filter = condition.getJSONObject("filter");
            if (filter != null) {
                predicates.addAll(filterPredicates(root, criteriaBuilder, filter));
            }

            String sort = condition.getString("sort");
            if (StringUtils.isNotBlank(sort)) {

                Order order = sortOrder(root, criteriaBuilder, sort);
                if (order != null) {
                    query.orderBy(order);
                }

                return query
                        .multiselect(
                                root.get("id"),
                                // 银行id，用于查询银行信息
                                root.get("bankid"),
                                // 产品名称
                                root.get("productname"),
                                // 年利率
                                root.get("nianhualv"),
                                // 期限
                                root.get("qixian"),
                                // 额度
                                root.get("edu")
                        )
                        .where(predicates.toArray(new Predicate[0]))
                        .getRestriction();
            } else {
                throw new IllegalArgumentException("Missing parameter: sortCondition");
            }

        }, page);
    }

    public QueryResult<Product> queryAllTargetUserType() {
        return super.query(
                (Specification<Product>) (root, query, criteriaBuilder) -> query
                        .select(root.get("floanUse"))
                        .distinct(true)
                        .getRestriction()
        );
    }

    private Order sortOrder(Root<Product> root, CriteriaBuilder criteriaBuilder, String sort) throws IllegalArgumentException {
        SortCondition sortCondition = SortCondition.valueOf(sort);
        if (sortCondition == SortCondition.DEFAULT) {
            return null;
        } else {
            if (sortCondition.isAcs()) {
                return criteriaBuilder.asc(root.get(sortCondition.getOrderField()));
            } else {
                return criteriaBuilder.desc(root.get(sortCondition.getOrderField()));
            }
        }

    }

    List<Predicate> filterPredicates(Root<Product> root, CriteriaBuilder criteriaBuilder, JSONObject filter) {
        List<FilterStrategy> strategies = new ArrayList<>();

        FilterConditions fc = FilterConditions.getInstance();

        strategies.add(
                fc
                        .getLoanLimitCondition()
                        .getStrategy(filter.getInteger("loanLimit"))
        );
        strategies.add(
                fc
                        .getLoanDueTimeCondition()
                        .getStrategy(filter.getInteger("loanDueTime"))
        );
        strategies.add(
                fc
                        .getFinanceOrganizationCondition()
                        .getStrategy(filter.getString("bankId"))
        );
        strategies.add(
                fc
                        .getLoanGuarantyTypeCondition()
                        .getStrategy(filter.getJSONObject("guarantyType"))
        );
        strategies.add(
                fc
                        .getTargetUserCondition()
                        .getStrategy(filter.getString("userType"))
        );

        return strategies.stream().filter((Objects::nonNull)).map(
                (strategy) -> strategy.apply(root, criteriaBuilder)
        ).collect(Collectors.toList());
    }

    Predicate keywordPredicate(Root<Product> root, CriteriaBuilder criteriaBuilder, String keyword) {
        // %% 表示转义 %, format 即为 %%s%
        return criteriaBuilder.and(
                criteriaBuilder.like(root.get("productname"), String.format("%%%s%%", keyword))
        );
    }


}
