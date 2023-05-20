package com.stocks.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.api.model.StocksApiModel;
import com.stocks.api.service.StocksApiService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StocksApiController {
  @Autowired
  StocksApiService stocksApiService;

  @GetMapping("/stocks")
  public ResponseEntity<List<StocksApiModel>> getAllStocks(@RequestParam(required = false) String title) {
    try {
      List<StocksApiModel> stocksApiModels = new ArrayList<StocksApiModel>();

      if (stocksApiModels.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(stocksApiModels, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/stocks/{tckr}")
  public ResponseEntity<StocksApiModel> getStockPrice(@PathVariable("tckr") long tckr) {
    StocksApiModel stocksApiModel = new StocksApiModel();

    if (stocksApiModel != null) {
      return new ResponseEntity<>(stocksApiModel, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/stocks")
  public ResponseEntity<StocksApiModel> buyStock(@RequestBody StocksApiModel stocksApiModel) {
    try {
      StocksApiModel _stocksApiModel = new StocksApiModel();
              //stocksApiService
          //.save(new StocksApiModel(stocksApiModel.getTitle(), stocksApiModel.getDescription(), false));
      return new ResponseEntity<>(_stocksApiModel, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/stocks/{tckr}")
  public ResponseEntity<StocksApiModel> sellStock(@PathVariable("tckr") long tckr, @RequestBody StocksApiModel stocksApiModel) {
    StocksApiModel _stocksApiModel = new StocksApiModel();
            //stocksApiService.findById(id);

    if (_stocksApiModel != null) {
      _stocksApiModel.setTitle(stocksApiModel.getTitle());
      _stocksApiModel.setDescription(stocksApiModel.getDescription());
      _stocksApiModel.setPublished(stocksApiModel.isPublished());
      //stocksApiService.save(_stocksApiModel);
      return new ResponseEntity<>(stocksApiModel, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
