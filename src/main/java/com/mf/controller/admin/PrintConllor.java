package com.mf.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.mf.entity.SaleList;
import com.mf.service.IRegisterService;
import com.mf.service.SaleListGoodsService;
import com.mf.service.SaleListService;

@RestController
@RequestMapping("/print")
public class PrintConllor {

	
	@Resource
	private SaleListGoodsService saleListGoodsService;
	@Resource
	private IRegisterService registerService;
	@Resource
	private SaleListService saleListService;
	
	/**
	 * 根据销售单id查询所有销售单商品
	 * @param saleListId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sale", method = RequestMethod.POST)
	public Map<String, Object> printOrder(HttpServletRequest request)throws Exception{
		Map<String, Object> model = new HashMap<>();
		System.out.println(JSON.toJSONString(request.getParameterMap()));
		String saleNumber = request.getParameter("saleNumber").toString();
		SaleList saleList = new SaleList();
		saleList.setSaleNumber(saleNumber);
		List<SaleList> saleListList=saleListService.list(saleList, Direction.DESC, "saleDate");
		saleList = saleListList.get(0);
		model.put("company", registerService.getCompanyName()+"销售单");
		model.put("rows", saleListGoodsService.listBySaleListId(saleList.getId()));
		System.out.println(JSON.toJSONString(model));
		return model;
	}
}
