package com.mill.app.service;

import com.mill.app.model.FlourOrder;
import com.mill.app.repository.FlourOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlourOrderService {

    @Autowired
    private FlourOrderRepository repository;

    public List<FlourOrder> getAllOrders() {
        return (List<FlourOrder>) repository.findAll();
    }

    public FlourOrder getOrderById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public FlourOrder saveOrder(FlourOrder order) {
        return repository.save(order);
    }

    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }
}
