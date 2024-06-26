package com.uduran.apiserverlet.webapp10.service;

import com.uduran.apiserverlet.webapp10.exceptions.ServiceJdbcException;
import com.uduran.apiserverlet.webapp10.models.Categoria;
import com.uduran.apiserverlet.webapp10.models.Producto;
import com.uduran.apiserverlet.webapp10.repository.CategoriaRepositoryImpl;
import com.uduran.apiserverlet.webapp10.repository.ProductoRepositoryJdbcImpl;
import com.uduran.apiserverlet.webapp10.repository.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ItemService<Producto> {

    private Repository<Producto> repositoryJdbc;
    private Repository<Categoria> repositoryCategoriaJdbc;

    public ProductoServiceJdbcImpl(Connection connection) {
        this.repositoryJdbc = new ProductoRepositoryJdbcImpl(connection);
        this.repositoryCategoriaJdbc = new CategoriaRepositoryImpl(connection);
    }

    @Override
    public List<Producto> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> buscar(String nombre) {
        return Optional.empty();
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return repositoryJdbc.porId(id);
            // of nullable convierte lo que devuelva porId que para el caso es un producto
            // a un optional.
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
