package com.example.back.serviciosImplementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.back.Cliente;
import com.example.back.repositorios.ClienteRepositorio;
import com.example.back.servicios.ClienteService;

@Service
public class ClienteImplementacion implements ClienteService{

    @Autowired
    private ClienteRepositorio repo;

    @Override
    public List<Cliente> getAllClientes() {
        return repo.findAll();
    }


}
