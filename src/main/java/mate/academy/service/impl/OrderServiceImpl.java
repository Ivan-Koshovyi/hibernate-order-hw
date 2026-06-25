package mate.academy.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import mate.academy.dao.OrderDao;
import mate.academy.dao.TicketDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Order;
import mate.academy.model.ShoppingCart;
import mate.academy.model.Ticket;
import mate.academy.model.User;
import mate.academy.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private TicketDao ticketDao;

    @Override
    public Order completeOrder(ShoppingCart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderDate(LocalDateTime.now());

        List<Ticket> tickets = new ArrayList<>(cart.getTickets());

        order.setTickets(tickets);

        Order orderAdd = orderDao.add(order);
        for (Ticket t : tickets) {
            t.setOrder(orderAdd);
            ticketDao.update(t);
        }

        cart.clear();

        return orderAdd;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getByUser(user);
    }
}
