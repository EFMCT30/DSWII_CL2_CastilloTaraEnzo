package pe.edu.cibertec.DSWII_CL2_CastilloTaraEnzo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII_CL2_CastilloTaraEnzo.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII_CL2_CastilloTaraEnzo.model.Producto;
import pe.edu.cibertec.DSWII_CL2_CastilloTaraEnzo.repository.ProductoRepository;
import pe.edu.cibertec.DSWII_CL2_CastilloTaraEnzo.service.ProductoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/producto")
public class ProductoController {

    private ProductoService productoService;

    @GetMapping("")
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> productoList = new ArrayList<>();
        productoService.listarProductos()
                .forEach(productoList::add);
        if(productoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerCategoria(
            @PathVariable("id") Integer id){
        Producto producto = productoService
                .obtenerProductoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoria con el Id Nro. "+
                        id + " no existe."));

        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Producto> registrarProducto(
            @RequestBody Producto producto
    ){
        return new ResponseEntity<>(
                productoService.guardar(producto), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarCategoria(
            @PathVariable("id") Integer id,
            @RequestBody Producto producto
    ){
        Producto oldProducto = productoService
                .obtenerProductoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Producto con el Id Nro. "+
                        id + " no existe."));
        oldProducto.setNombre(producto.getNombre());
        oldProducto.setDescripcion(producto.getDescripcion());
        oldProducto.setCantidad(producto.getCantidad());
        oldProducto.setFechavencimiento(producto.getFechavencimiento());
        return new ResponseEntity<>(
                productoService.guardar(oldProducto), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(
            @PathVariable("id") Integer id) {
        try {
            productoService.eliminarProductoPorId(id);
            return new ResponseEntity<>("Producto eliminado con ID: " + id, HttpStatus.OK);
        } catch (ResourceNotFoundException exception) {
            return new ResponseEntity<>("Producto no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error al eliminar el producto con ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // x nombre
     @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Producto> obtenerCategoriaPorNombre(
            @PathVariable("nombre") String nombre){
        Producto producto = productoService
                .obtenerProductoPorNombre(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("El producto con el nombre "+
                        nombre + " no existe."));

        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    // x cantidad
    @GetMapping("/buscarPorCantidadEntre10y100")
    public ResponseEntity<List<Producto>> buscarProductosPorCantidadEntre10y100() {
        List<Producto> productos = productoService.buscarProductosCantidadEntre10y100();
        return construirRespuesta(productos);
    }

    private ResponseEntity<List<Producto>> construirRespuesta(List<Producto> productos) {
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // x fecha
    @GetMapping("/buscarPorVencimientoAnio2024")
    public ResponseEntity<List<Producto>> buscarProductosVencimientoAnio2024() {
        List<Producto> productos = productoService.buscarProductosVencimientoAnio2024();
        return construirRespuesta(productos);
    }

}
