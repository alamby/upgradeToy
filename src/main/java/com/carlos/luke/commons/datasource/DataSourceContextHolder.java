package com.carlos.luke.commons.datasource;

public class DataSourceContextHolder {

	private static final ThreadLocal<DataSourceEnum> contextHolder = new ThreadLocal<DataSourceEnum>();

	public static void setTargetDataSource(DataSourceEnum targetDataSource) {
		contextHolder.set(targetDataSource);
	}

	public static DataSourceEnum getTargetDataSource() {
		return (DataSourceEnum) contextHolder.get();
	}

	public static void resetDefaultDataSource() {
		contextHolder.remove();
	}

}
