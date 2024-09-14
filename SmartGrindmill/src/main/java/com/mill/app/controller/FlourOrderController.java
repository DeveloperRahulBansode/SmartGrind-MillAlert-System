package com.mill.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mill.app.model.FlourOrder;
import com.mill.app.service.FlourOrderService;
import com.mill.app.service.SmsService;

@Controller
@RequestMapping("/orders")
public class FlourOrderController {

    @Autowired
    private FlourOrderService flourOrderService;

    @Autowired
    private SmsService smsService;

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", flourOrderService.getAllOrders());
        return "order-list";
    }

    @GetMapping("/new")
    public String showNewOrderForm(Model model) {
        model.addAttribute("order", new FlourOrder());
        return "order-form";
    }

    @PostMapping
    public String saveOrder(@ModelAttribute("order") FlourOrder order) {
        flourOrderService.saveOrder(order);

        if ("Ready".equalsIgnoreCase(order.getOrderStatus())) {
            String message = "Dear " + order.getClientName() + ", your flour is ready for pickup.";
            smsService.sendSms(order.getContactNumber(), message);
        }

        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String showEditOrderForm(@PathVariable Long id, Model model) {
        FlourOrder order = flourOrderService.getOrderById(id);
        model.addAttribute("order", order);
        return "order-form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        // Call the service to delete the order by ID
        flourOrderService.deleteOrder(id);

        // Redirect to the orders page after deletion
        return "redirect:/orders";
    }

    @GetMapping("/sendSms")
    public String sendSms(@RequestParam String to, @RequestParam String body) {
        smsService.sendSms(to, body);
        return "SMS sent successfully!";
    }
}
