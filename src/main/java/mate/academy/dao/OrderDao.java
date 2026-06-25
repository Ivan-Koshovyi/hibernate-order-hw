package mate.academy.dao;

import mate.academy.model.Order;
import mate.academy.model.User;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Order add(Order order);

    Optional<Order> get(Long id);

    public List<Order> getByUser(User user);
}
