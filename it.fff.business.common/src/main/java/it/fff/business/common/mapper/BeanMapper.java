package it.fff.business.common.mapper;

public interface BeanMapper<T,V,K> {
	
	public V mapDto2Bo(T dto);
	
	public T mapBo2Dto(V bo);
	
	public K mapBo2Eo(V bo);
	
	public V mapEo2Bo(K eo);

}
