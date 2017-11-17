package com.bootdo.system.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.DetailsDO;
import com.bootdo.system.service.DetailsService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-15 16:35:13
 */
 
@Controller
@RequestMapping("/system/details")
public class DetailsController {
	@Autowired
	private DetailsService detailsService;
	
	@GetMapping()
	@RequiresPermissions("system:details:details")
	String Details(){
	    return "system/details/details";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:details:details")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DetailsDO> detailsList = detailsService.list(query);
		int total = detailsService.count(query);
		PageUtils pageUtils = new PageUtils(detailsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:details:add")
	String add(){
	    return "system/details/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:details:edit")
	String edit(@PathVariable("id") Long id,Model model){
		DetailsDO details = detailsService.get(id);
		model.addAttribute("details", details);
	    return "system/details/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:details:add")
	public R save( DetailsDO details){
		Object object = SecurityUtils.getSubject().getSession().getAttributeKeys();
		details.setCreateTime(new Date());
		if(detailsService.save(details)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:details:edit")
	public R update( DetailsDO details){
		detailsService.update(details);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:details:remove")
	public R remove( Long id){
		if(detailsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:details:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		detailsService.batchRemove(ids);
		return R.ok();
	}
	
}
