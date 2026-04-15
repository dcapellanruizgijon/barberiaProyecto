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

    @Override
    public Cliente getClienteById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return repo.save(cliente);
    }


    //para recuperar la contraseña
    @Override
     public Cliente getClienteByEmail(String email) {
        List<Cliente> clientes = repo.findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)) {
                return cliente; 
            } 
        }
        return null;//si no ha devuelto el cliente que devuelva null
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        Cliente existingCliente = repo.findById(id).orElse(null);
        if (existingCliente != null) {
            existingCliente.setNombre(cliente.getNombre());
            existingCliente.setEmail(cliente.getEmail());
            existingCliente.setContrasena(cliente.getContrasena());
            return repo.save(existingCliente);          
    
        } else {
            return null;
        }
    }

    @Override
    public void eliminarCliente(Long id) {
        repo.deleteById(id);    
    }

    @Override
    public Cliente login(String email, String contrasena) {
        List<Cliente> clientes = repo.findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email) && cliente.getContrasena().equals(contrasena)) {
                return cliente; 
            } 
        }
        return null;//si no ha devuelto el cliente que devuelva null
    }

    @Override 
    public Cliente actualizarContrasena(Long id, String nuevaContrasena) {
        Cliente existingCliente = repo.findById(id).orElse(null);
        if (existingCliente != null) {
            existingCliente.setContrasena(nuevaContrasena);
            return repo.save(existingCliente);          
    
        } else {
            return null;
        }
    }


}
