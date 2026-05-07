package com.example.back.serviciosImplementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.back.Cliente;
import com.example.back.repositorios.ClienteRepositorio;
import com.example.back.servicios.ClienteService;

@Service
public class ClienteImplementacion implements ClienteService {

    @Autowired
    private ClienteRepositorio repo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        if (cliente.getContrasena() != null && !cliente.getContrasena().startsWith("$2a$")) {
            String contrasenaHasheada = passwordEncoder.encode(cliente.getContrasena());
            cliente.setContrasena(contrasenaHasheada);
        }
        return repo.save(cliente);
    }

    @Override
    public Cliente getClienteByEmail(String email) {
        // ✅ CORREGIDO: Sin .orElse() porque no es Optional
        return repo.findByEmail(email);
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente clienteConDatosNuevos) {
        Cliente clienteEnBD = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        clienteEnBD.setNombre(clienteConDatosNuevos.getNombre());
        clienteEnBD.setEmail(clienteConDatosNuevos.getEmail());

        if (clienteConDatosNuevos.getFotoPerfil() != null) {
            clienteEnBD.setFotoPerfil(clienteConDatosNuevos.getFotoPerfil());
        }
        
        if (clienteConDatosNuevos.getContrasena() != null && 
            !clienteConDatosNuevos.getContrasena().isEmpty()) {
            if (!clienteConDatosNuevos.getContrasena().startsWith("$2a$")) {
                String nuevaContrasenaHasheada = passwordEncoder.encode(clienteConDatosNuevos.getContrasena());
                clienteEnBD.setContrasena(nuevaContrasenaHasheada);
            } else {
                clienteEnBD.setContrasena(clienteConDatosNuevos.getContrasena());
            }
        }

        return repo.save(clienteEnBD);
    }

    @Override
    public void eliminarCliente(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Cliente login(String email, String contrasenaPlana) {
        // ✅ CORREGIDO: Sin Optional
        Cliente cliente = repo.findByEmail(email);
        
        if (cliente != null && passwordEncoder.matches(contrasenaPlana, cliente.getContrasena())) {
            return cliente;
        }
        
        return null;
    }

    @Override
    public Cliente actualizarContrasena(Long id, String nuevaContrasenaPlana) {
        Cliente existingCliente = repo.findById(id).orElse(null);
        if (existingCliente != null) {
            String contrasenaHasheada = passwordEncoder.encode(nuevaContrasenaPlana);
            existingCliente.setContrasena(contrasenaHasheada);
            return repo.save(existingCliente);
        } else {
            return null;
        }
    }
}