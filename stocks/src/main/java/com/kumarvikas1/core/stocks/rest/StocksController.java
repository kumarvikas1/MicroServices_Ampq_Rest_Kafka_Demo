package com.kumarvikas1.core.stocks.rest;

import com.kumarvikas1.core.models.Assets;
import com.kumarvikas1.core.stocks.StocksDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by vikakumar on 1/2/18.
 */
@Controller
public class StocksController {

	@Autowired StocksDetails stocksDetails;

	@RequestMapping(method = RequestMethod.GET,path = "/")
	public ResponseEntity<Assets> getAssets(@RequestParam(value = "accountId") String accountId) {
		return new ResponseEntity(stocksDetails.getAccounts(accountId), HttpStatus.OK);
	}
}
