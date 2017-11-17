package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.DetailsDao;
import com.bootdo.system.domain.DetailsDO;
import com.bootdo.system.service.DetailsService;



@Service
public class DetailsServiceImpl implements DetailsService {
	@Autowired
	private DetailsDao detailsDao;
	
	@Override
	public DetailsDO get(Long id){
		return detailsDao.get(id);
	}
	
	@Override
	public List<DetailsDO> list(Map<String, Object> map){
		return detailsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return detailsDao.count(map);
	}
	
	@Override
	public int save(DetailsDO details){
		return detailsDao.save(details);
	}
	
	@Override
	public int update(DetailsDO details){
		return detailsDao.update(details);
	}
	
	@Override
	public int remove(Long id){
		return detailsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return detailsDao.batchRemove(ids);
	}
	
}
