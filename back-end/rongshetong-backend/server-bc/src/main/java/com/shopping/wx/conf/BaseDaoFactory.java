package com.shopping.wx.conf;

import com.github.pagehelper.PageHelper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class BaseDaoFactory {
	// Generic singleton factory pattern
	private static BaseDao<Object> BASE_DAO =
			new BaseDao<Object>() {
				@Override
				public Object selectById(Mapper<Object> mapper, Object id) {
					return mapper.selectByPrimaryKey(id);
				}

				@Override
				public List<Object> selectAll(Mapper<Object> mapper) {
					return mapper.selectAll();
				}

				@Override
				public Object selectOne(Mapper<Object> mapper, Object record) {
					return mapper.selectOne(record);
				}

				@Override
				public List<Object> select(Mapper<Object> mapper, Object record) {
					return mapper.select(record);
				}

				@Override
				public List<Object> selectPage(Mapper<Object> mapper, Page page) {
					startPage(page);
					return mapper.selectAll();
				}

				@Override
				public List<Object> select(Mapper<Object> mapper, Object record, Page page) {
					startPage(page);
					return mapper.select(record);
				}

				@Override
				public List<Object> selectAllByExample(Mapper<Object> mapper, Object example) {
					return mapper.selectByExample(example);
				}

				@Override
				public List<Object> selectPageByExample(Mapper<Object> mapper, Object example, Page page) {
					startPage(page);
					return mapper.selectByExample(example);
				}

				@Override
				public List<Object> selectAllOrderBy(Mapper<Object> mapper, Class<Object> clazz, String orderBy) {
					Example example = new Example(clazz);
					example.setOrderByClause(orderBy);
					return mapper.selectByExample(example);
				}

				@Override
				public List<Object> selectPageOrderBy(Mapper<Object> mapper, Class<Object> clazz, String orderBy,  Page page) {
					startPage(page);
					Example example = new Example(clazz);
					example.setOrderByClause(orderBy);
					return mapper.selectByExample(example);
				}

				@Override
				public int selectCount(Mapper<Object> mapper, Object record) {
					return mapper.selectCount(record);
				}

				@Override
				public int insert(Mapper<Object> mapper, Object record) {
					return mapper.insertSelective(record);
				}

				@Override
				public int deleteById(Mapper<Object> mapper, Object id) {
					return mapper.delete(id);
				}

				@Override
				public int delete(Mapper<Object> mapper, Object record) {
					return mapper.delete(record);
				}

				@Override
				public int update(Mapper<Object> mapper, Object record) {
					return mapper.updateByPrimaryKeySelective(record);
				}

				private void startPage(Page page) {
					if (Page.validate(page)) {
						PageHelper.startPage(page.getPage(), page.getRows());
					}
				}

				@Override
				public long selectCountByExample(Mapper<Object> mapper, Object example) {
					return mapper.selectCountByExample(example);
				}

			};

	@SuppressWarnings("unchecked")
	public static <T> BaseDao<T> baseDao() {
		return (BaseDao<T>)BASE_DAO;
	}

	private BaseDaoFactory() {}
}
