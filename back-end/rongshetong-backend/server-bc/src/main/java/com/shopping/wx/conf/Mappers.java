package com.shopping.wx.conf;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Mappers异构泛型
 * Key=Entity.class, Value=EntityMapperBean
 * @author Administrator
 *
 */
@Component
public class Mappers implements ApplicationContextAware {
	private Map<Class<?>, Object> mappers =
			new HashMap<Class<?>, Object>();
	private ApplicationContext applicationContext;

	private static Mappers instance = new Mappers();

	public static Mappers getInstance() {
		return instance;
	}

	public <T> void putMapper(Class<T> type, Mapper<T> instance) {
		if (type == null)
			throw new NullPointerException("Model type is null");
		mappers.put(type, instance);
	}

	@SuppressWarnings("unchecked")
	public <T> Mapper<T> getMapper(Class<T> type) {
		Mapper<T> mapper = (Mapper<T>)mappers.get(type);
		if (mapper == null) {
			mapper = getMapperBean(type);
			putMapper(type, mapper);
		}
		return mapper;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@SuppressWarnings("unchecked")
	private <M> Mapper<M> getMapperBean(Class<M> clazz) {
		String mapperName = getMapperName(clazz.getName());
		try {
			Class<?> mapperClass = this.getClass().forName(mapperName);
			Mapper<M> mapper = (Mapper<M>) applicationContext.getBean(mapperClass);
			return mapper;
		} catch (ClassNotFoundException e) {
		}
		throw new RuntimeException(mapperName + " Not found.");
	}

	private String getMapperName(String entityName) {
		return entityName.replace("model", "mapper") + "Mapper";
	}
	private Mappers() {

	}
}