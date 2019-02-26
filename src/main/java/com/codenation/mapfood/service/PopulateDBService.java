package com.codenation.mapfood.service;

import com.codenation.mapfood.model.*;
import com.codenation.mapfood.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service
public class PopulateDBService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MotoboyRepository motoboyRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ProductRepository productRepository;

    public void popularTabelaCustomerApartirDoClienteCSV() {

        try (BufferedReader clienteCSV = new BufferedReader(
                new InputStreamReader(new FileInputStream("../mapfood/src/main/resources/csv/clientes.csv")))){
            String linhaArquivo;
            Scanner lerArquivo = new Scanner(clienteCSV);
            lerArquivo.nextLine();

            while (lerArquivo.hasNext()) {
                linhaArquivo = lerArquivo.nextLine();
                String[] clienteAux = linhaArquivo.split(",");

                Customer cliente = new Customer(Long.parseLong(clienteAux[0]), clienteAux[1], clienteAux[2]);

                customerRepository.save(cliente);
            }

            lerArquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void popularTabelaEstabelecimentoApartirDoEstabelecimentoCSV() {

        try (BufferedReader estabelecimentoCSV = new BufferedReader(
                new InputStreamReader(new FileInputStream("../mapfood/src/main/resources/csv/estabelecimentos.csv")))) {
            String linhaArquivo;
            Scanner lerArquivo = new Scanner(estabelecimentoCSV);
            lerArquivo.nextLine();

            while (lerArquivo.hasNext()) {
                linhaArquivo = lerArquivo.nextLine();
                String[] estabelecimentoAux = linhaArquivo.split(",");

                City cidade = cityRepository.findByName(estabelecimentoAux[2]);
                if(cidade == null) {
                    if(estabelecimentoAux[2] != null && !estabelecimentoAux[2].isEmpty()) {
                        cidade = cityRepository.save(new City(estabelecimentoAux[2]));
                    }
                }

                Restaurant estabelecimento = new Restaurant(estabelecimentoAux[1], cidade, estabelecimentoAux[3], estabelecimentoAux[4], estabelecimentoAux[5], estabelecimentoAux[0]);

                restaurantRepository.save(estabelecimento);
            }
            lerArquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void popularTabelaMotoboyApartirDoMotoboyCSV() {

        try (BufferedReader motoboyCSV = new BufferedReader(
                new InputStreamReader(new FileInputStream("../mapfood/src/main/resources/csv/motoboys.csv")))) {
            String linhaArquivo;
            Scanner lerArquivo = new Scanner(motoboyCSV);
            lerArquivo.nextLine();

            while (lerArquivo.hasNext()) {
                linhaArquivo = lerArquivo.nextLine();
                String[] motoboyAux = linhaArquivo.split(",");

                Motoboy motoboy = new Motoboy(Long.parseLong(motoboyAux[0]), motoboyAux[1], motoboyAux[2]);

                motoboyRepository.save(motoboy);
            }
            lerArquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void popularTabelaProdutoApartirDoProdutoCSV() {

        try (BufferedReader produtoCSV = new BufferedReader(
                new InputStreamReader(new FileInputStream("../mapfood/src/main/resources/csv/produtos.csv")))) {
            String linhaArquivo;
            Scanner lerArquivo = new Scanner(produtoCSV);
            lerArquivo.nextLine();

            Integer totalProdutosBons = 0;
            while (lerArquivo.hasNext()) {
                linhaArquivo = lerArquivo.nextLine();
                String[] produtoAux = linhaArquivo.split(",");

                Random random = new Random();

                List<Restaurant> listaEstabelecimentos = restaurantRepository.findAll();

                Integer value = random.nextInt(listaEstabelecimentos.size());

                Long idRandomico = value.longValue();
                City cidade = new City();

                Integer count = 0;
                for (Restaurant est : listaEstabelecimentos) {
                    count++;
                    if(est.getHashId().equals(produtoAux[2])) {
                        idRandomico = count.longValue();
                        totalProdutosBons ++;
                    }
                    if(est.getCity().getName().equals(produtoAux[6])) {
                        cidade = est.getCity();
                    }
                }

                Product produto = new Product(produtoAux[0], idRandomico.equals(0l) ? 1l : idRandomico, produtoAux[4], Double.parseDouble(produtoAux[5]), cidade);

                productRepository.save(produto);

            }
            System.out.println("totalProdutos: " + totalProdutosBons);
            lerArquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
