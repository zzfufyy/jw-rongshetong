package com.shopping.wx.conf;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaseDao<M> {

	M selectById(Mapper<M> mapper, Object id);

	M selectOne(Mapper<M> mapper, M record);

	List<M> selectAll(Mapper<M> mapper);

	List<M> select(Mapper<M> mapper, M record);

	List<M> selectPage(Mapper<M> mapper, Page page);

	List<M> select(Mapper<M> mapper, M record, Page page);

	List<M> selectAllByExample(Mapper<M> mapper, Object example);

	List<M> selectPageByExample(Mapper<M> mapper, Object example, Page page);

	List<M> selectAllOrderBy(Mapper<M> mapper, Class<M> clazz, String orderBy);

	List<M> selectPageOrderBy(Mapper<M> mapper, Class<M> clazz, String orderBy, Page page);

	int selectCount(Mapper<M> mapper, M record);

	long selectCountByExample(Mapper<M> mapper, Object example);

	int insert(Mapper<M> mapper, M record);

	int deleteById(Mapper<M> mapper, Object id);

	int delete(Mapper<M> mapper, M record);

	int update(Mapper<M> mapper, M record);

}