package com.shopping.wx.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component("db")
public class DB {
	@Autowired
	private Mappers mappers;

	@SuppressWarnings("unchecked")
	public <M> M selectById(Object id, Class<M> clazz) {
		return ((BaseDao<M>)baseDao()).selectById(mapper(clazz), id);
	}

	@SuppressWarnings("unchecked")
	public <M> M selectOne(M record) {
		return ((BaseDao<M>)baseDao()).selectOne(mapper(record), record);
	}

	@SuppressWarnings("unchecked")
	public <M> List<M> selectAll(Class<M> clazz) {
		return ((BaseDao<M>)baseDao()).selectAll(mapper(clazz));
	}

	@SuppressWarnings("unchecked")
	public <M> List<M> select(M record) {//TODO TEST
		return ((BaseDao<M>)baseDao()).select(mapper(record), record);
	}

	@SuppressWarnings("unchecked")
	public <M> List<M> selectPage(Class<M> clazz, Page page) {
		//TODO TEST
		return ((BaseDao<M>)baseDao()).selectPage(mapper(clazz), page);
	}

	@SuppressWarnings("unchecked")
	public <M> List<M> select(M record, Page page) {
		// TODO TEST
		return ((BaseDao<M>)baseDao()).select(mapper(record), record, page);
	}

	@SuppressWarnings("unchecked")
	public <M> List<M> selectAllByExample(Class<M> clazz, Object example) {
		return ((BaseDao<M>)baseDao()).selectAllByExample(mapper(clazz), example);
	}

	@SuppressWarnings("unchecked")
	public <M> List<M> selectPageByExample(Class<M> clazz, Object example, Page page) {
		return ((BaseDao<M>)baseDao()).selectPageByExample(mapper(clazz), example, page);
	}

	public <M> long selectCountByExample(Class<M> clazz, Object example) {
		return ((BaseDao<M>)baseDao()).selectCountByExample(mapper(clazz), example);
	}

	@SuppressWarnings("unchecked")
	public <M> List<M>  selectAllOrderBy(Class<M> clazz, String orderBy) {
		return ((BaseDao<M>)baseDao()).selectAllOrderBy(mapper(clazz), clazz, orderBy);
	}

	@SuppressWarnings("unchecked")
	public <M> List<M> selectPageOrderBy(Class<M> clazz, String orderBy, Page page) {
		return ((BaseDao<M>)baseDao()).selectPageOrderBy(mapper(clazz), clazz, orderBy, page);
	}

	@SuppressWarnings("unchecked")
	public <M> int selectCount(M record) {
		return ((BaseDao<M>)baseDao()).selectCount(mapper(record), record);
	}

	@SuppressWarnings("unchecked")
	public <M> int insert(M record) {
		return ((BaseDao<M>)baseDao()).insert(mapper(record), record);
	}

	@SuppressWarnings("unchecked")
	public <M> int deleteById(Class<M> clazz, Object id) {
		return ((BaseDao<M>)baseDao()).deleteById(mapper(clazz), id);
	}

	@SuppressWarnings("unchecked")
	public <M> int delete(M record) {
		return ((BaseDao<M>)baseDao()).delete(mapper(record), record);
	}

	@SuppressWarnings("unchecked")
	public <M> int update(M record) {
		return ((BaseDao<M>)baseDao()).update(mapper(record), record);
	}

	private <M> BaseDao<M> baseDao() {
		return BaseDaoFactory.baseDao();
	}

	private <M> Mapper<M> mapper(Class<M> clazz) {
		return mappers.getMapper(clazz);
	}

	@SuppressWarnings("unchecked")
	private <M> Mapper<M> mapper(M record) {
		Class<M> clazz = (Class<M>)record.getClass();
		Mapper<M> mapper = mappers.getMapper(clazz);
		return mapper;
	}

}