package com.bootdo.system.service;

import com.bootdo.system.domain.DetailsDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-15 16:35:13
 */
public interface DetailsService {
	
	DetailsDO get(Long id);
	
	List<DetailsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DetailsDO details);
	
	int update(DetailsDO details);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
