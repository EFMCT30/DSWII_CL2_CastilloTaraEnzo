package pe.edu.cibertec.DSWII_CL2_CastilloTaraEnzo.service;

import pe.edu.cibertec.DSWII_CL2_CastilloTaraEnzo.model.Producto;
import pe.edu.cibertec.DSWII_CL2_CastilloTaraEnzo.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

public class ProductoService {

    private ProductoRepository productoRepository;

    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }
    public Producto guardar(Producto producto){
        return productoRepository.save(producto);
    }
    public Optional<Producto> obtenerProductoPorId(Integer id){
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isEmpty()){
            return Optional.empty();
        }else
            return producto;
    }

    public Optional<Producto> obtenerProductoPorNombre(String nombre){
        Optional<Producto> category = productoRepository.findByNombre(nombre);
        if(category.isEmpty())
            return  Optional.empty();
        else
            return category;
    }

    public List<Producto> obtenerProductosPorFiltro(String filtro){
        return productoRepository.filtrarProductosPorNombreSQL(filtro);
    }

}
