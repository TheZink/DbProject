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
import java.util.Optional;


@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersRepository ordersRepository;

    public OrdersController(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable int id) {
        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<String> putOrderById(@PathVariable int id, @RequestParam(required = false) Integer idParam) {
        
        int orderId = (idParam != null) ? idParam : id;
        
        if (ordersRepository.existsById(orderId)) {
            return ResponseEntity.ok("Tilauksen päivittäminen onnistui.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/post")
    public ResponseEntity<String> postOrder(@RequestBody Orders order) {
        ordersRepository.save(order);
        return ResponseEntity.ok("Tilauksen tallentaminen onnistui.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable int id, @RequestParam(required = false) Integer idParam) {
        
        int orderId = (idParam != null) ? idParam : id;
        
        if (ordersRepository.existsById(orderId)) {
            ordersRepository.deleteById(orderId);
            return ResponseEntity.ok("Tilauksen poisto onnistui.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
