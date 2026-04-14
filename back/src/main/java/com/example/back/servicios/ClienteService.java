package com.example.back.servicios;

import java.util.List;

import com.example.back.Cliente;

public interface ClienteService{
    public List<Cliente> getAllClientes();
    public Cliente getClienteById(Integer id);
    public Cliente guardarCliente(Cliente cliente);
    public Cliente actualizarCliente(Integer id, Cliente cliente);
    public void eliminarCliente(Integer id);    
    public Cliente login(String email, String contrasena);
    public Cliente getClienteByEmail(String email);//para recuperar la contraseña
    public Cliente actualizarContrasena(Integer id, String nuevaContrasena);//para actualizarla

}
