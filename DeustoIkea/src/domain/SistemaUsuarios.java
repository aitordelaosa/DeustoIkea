package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaUsuarios {
    private static SistemaUsuarios instancia;
    private List<Cliente> listaClientes;

    private SistemaUsuarios() {
        listaClientes = new ArrayList<>();
    }

    public static SistemaUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new SistemaUsuarios();
        }
        return instancia;
    }

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public Cliente buscarCliente(String identificador) {
        for (Cliente c : listaClientes) {
            if (c.getDni().equals(identificador) || c.getEmail().equals(identificador) || c.getTelefono().equals(identificador)) {
                return c;
            }
        }
        return null;
    }
}

