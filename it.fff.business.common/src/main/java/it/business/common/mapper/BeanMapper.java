package it.business.common.mapper;

public interface BeanMapper<T,V,K> {
	
	public V mapDto2Bo(T dto);
	
	public T mapBo2Dto(V bo);
	
	public K mapBo2Dao(V bo);
	
	public V mapDao2Bo(K dao);

}
