package fi.metropolia.ilkkas.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.metropolia.ilkkas.demo.repository.OrdersRepository;
import fi.metropolia.ilkkas.demo.entity.Orders;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersRepository ordersRepository;

    public OrdersController(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOrderById(int id) {
        return ordersRepository.findById(id).map(order -> ResponseEntity.ok(order)).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<?> putOrderById(@PathVariable(required = false) int id, @RequestParam(required = false) Integer idParam) {
        
        int orderId = (idParam != null) ? idParam : id;
        
        if (ordersRepository.existsById(orderId)) {
            return ResponseEntity.ok("Tilauksen päivittäminen onnistui.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postOrder(@RequestBody Orders order) {
        ordersRepository.save(order);
        return ResponseEntity.ok("Tilauksen tallentaminen onnistui.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable(required = false) int id, @RequestParam(required = false) Integer idParam) {
        
        int orderId = (idParam != null) ? idParam : id;
        
        if (ordersRepository.existsById(orderId)) {
            ordersRepository.deleteById(orderId);
            return ResponseEntity.ok("Tilauksen poisto onnistui.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
