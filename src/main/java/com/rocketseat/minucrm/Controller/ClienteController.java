package com.rocketseat.minucrm.Controller;

import com.rocketseat.minucrm.model.Cliente;
import com.rocketseat.minucrm.model.Contato;
import com.rocketseat.minucrm.repository.ClienteRepository;
import com.rocketseat.minucrm.repository.ContatoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final ContatoRepository contatoRepository;

    public ClienteController(ClienteRepository clienteRepository, ContatoRepository contatoRepository) {
        this.clienteRepository = clienteRepository;
        this.contatoRepository = contatoRepository;
    }

    // Criar cliente
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente payload) {
        Cliente cliente = clienteRepository.save(payload);
        return ResponseEntity
                .created(URI.create("/clientes/" + cliente.getId()))
                .body(cliente);
    }

    // Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    // Criar contato para um cliente específico
    @PostMapping("/{id}/contatos")
    public ResponseEntity<Contato> criarContato(@PathVariable Long id, @RequestBody Contato payload) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    payload.setId(null);
                    payload.setCliente(cliente);
                    Contato salvo = contatoRepository.save(payload);
                    return ResponseEntity
                            .created(URI.create("/clientes/" + cliente.getId() + "/contatos/" + salvo.getId()))
                            .body(salvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar contatos de um cliente específico
    @GetMapping("/{id}/contatos")
    public ResponseEntity<List<Contato>> listarContatos(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> ResponseEntity.ok(cliente.getContatos()))
                .orElse(ResponseEntity.notFound().build());
    }
}
