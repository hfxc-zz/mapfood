package com.codenation.mapfood.controller;

import com.codenation.mapfood.service.PopulateDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hfxc on 25/02/19.
 */
@RestController
@RequestMapping("/popular-banco")
public class PopulateDBController {

    @Autowired
    private PopulateDBService service;

    @GetMapping
    public void populate() {
        service.popularTabelaCustomerApartirDoClienteCSV();
        service.popularTabelaMotoboyApartirDoMotoboyCSV();
        service.popularTabelaEstabelecimentoApartirDoEstabelecimentoCSV();
        service.popularTabelaProdutoApartirDoProdutoCSV();
    }
}
