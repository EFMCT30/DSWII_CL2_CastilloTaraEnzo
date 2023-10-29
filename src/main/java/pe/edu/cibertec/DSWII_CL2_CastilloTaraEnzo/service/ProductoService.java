package pe.edu.cibertec.DSWII_CL2_CastilloTaraEnzo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII_CL2_CastilloTaraEnzo.model.Producto;
import pe.edu.cibertec.DSWII_CL2_CastilloTaraEnzo.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
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

    public void eliminarProductoPorId(Integer id) {
        // Verificar si el producto existe antes de eliminarlo
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            productoRepository.deleteById(id);
        } else {
            // Manejo si el producto no existe
            throw new RuntimeException("El producto con ID " + id + " no existe.");
        }
    }

    public List<Producto> buscarProductosCantidadEntre10y100() {
        return productoRepository.findProductosCantidadEntre10y100();
    }

    public List<Producto> buscarProductosVencimientoAnio2024() {
        return productoRepository.findProductosVencimientoAnio2024();
    }

}
