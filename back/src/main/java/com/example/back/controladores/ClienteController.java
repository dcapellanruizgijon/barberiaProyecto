package com.example.back.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.back.Cliente;
import com.example.back.servicios.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*") // Para permitir peticiones desde frontend
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear nuevo cliente
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    // Actualizar cliente completo
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteActualizado = clienteService.actualizarCliente(id, cliente);
        if (clienteActualizado != null) {
            return ResponseEntity.ok(clienteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar solo la contraseña
    @PatchMapping("/{id}/contrasena")
    public ResponseEntity<Cliente> actualizarContrasena(@PathVariable Long id, @RequestParam String nuevaContrasena) {
        Cliente clienteActualizado = clienteService.actualizarContrasena(id, nuevaContrasena);
        if (clienteActualizado != null) {
            return ResponseEntity.ok(clienteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            clienteService.eliminarCliente(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Login de cliente
    @PostMapping("/login")
    public ResponseEntity<Cliente> login(@RequestParam String email, @RequestParam String contrasena) {
        Cliente cliente = clienteService.login(email, contrasena);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // Obtener cliente por email (para recuperar contraseña)
    @GetMapping("/email/{email}")
    public ResponseEntity<Cliente> getClienteByEmail(@PathVariable String email) {
        Cliente cliente = clienteService.getClienteByEmail(email);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}