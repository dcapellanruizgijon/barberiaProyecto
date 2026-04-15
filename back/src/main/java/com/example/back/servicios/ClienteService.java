package com.example.back.servicios;

import java.util.List;

import com.example.back.Cliente;

public interface ClienteService{
    public List<Cliente> getAllClientes();
    public Cliente getClienteById(Long id);
    public Cliente guardarCliente(Cliente cliente);
    public Cliente actualizarCliente(Long id, Cliente cliente);
    public void eliminarCliente(Long id);    
    public Cliente login(String email, String contrasena);
    public Cliente getClienteByEmail(String email);//para recuperar la contraseña
    public Cliente actualizarContrasena(Long id, String nuevaContrasena);//para actualizarla

}
