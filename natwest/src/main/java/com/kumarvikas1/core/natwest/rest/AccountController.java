package com.kumarvikas1.core.natwest.rest;

import com.kumarvikas1.core.models.Assets;
import com.kumarvikas1.core.natwest.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
@Profile("rest")
public class AccountController {

	@Autowired AccountDetails accountDetails;

	@RequestMapping(method = RequestMethod.GET,path = "/")
	public ResponseEntity<Assets> getAssets(@RequestParam(value = "accountId") String accountId) {
		return new ResponseEntity(accountDetails.getAccounts(accountId), HttpStatus.OK);
	}
}
