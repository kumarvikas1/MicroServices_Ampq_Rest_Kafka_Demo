package com.kumarvikas1.core.assets.controller;

import com.kumarvikas1.core.assets.service.AssetsService;
import com.kumarvikas1.core.assets.service.CoreService;
import com.kumarvikas1.core.models.BankingResponse;
import com.kumarvikas1.core.models.Transaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by vikakumar on 1/2/18.
 */
@Controller
public class BankingController {

	private final CoreService coreService;

	@Autowired
	public BankingController(final CoreService coreService) {
		this.coreService = coreService;
	}


	@RequestMapping(method = RequestMethod.GET,value = "/")
	public ResponseEntity alive() {
		return new ResponseEntity(HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.POST,value = "/")
	public ResponseEntity transactions(@RequestBody List<Transaction> transaction) {
		coreService.postAccountDetails(transaction);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET,value = "/details")
	public ResponseEntity<BankingResponse> getAccountDetails(@RequestParam(value = "accountId") String accountId) {
		return new ResponseEntity(coreService.getAssets(accountId),HttpStatus.OK);
	}
}
