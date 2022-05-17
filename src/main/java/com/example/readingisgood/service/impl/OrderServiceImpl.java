package com.example.readingisgood.service.impl;

import com.example.readingisgood.constant.SystemMessage;
import com.example.readingisgood.dto.OrderDTO;
import com.example.readingisgood.dto.book.BookResponseDTO;
import com.example.readingisgood.dto.order.OrderRequestDTO;
import com.example.readingisgood.dto.order.orderItem.OrderItemRequestDTO;
import com.example.readingisgood.exception.FriendlyException;
import com.example.readingisgood.model.BookModel;
import com.example.readingisgood.model.CustomerModel;
import com.example.readingisgood.model.OrderItemModel;
import com.example.readingisgood.model.OrderModel;
import com.example.readingisgood.repository.OrderRepository;
import com.example.readingisgood.service.BookService;
import com.example.readingisgood.service.CustomerService;
import com.example.readingisgood.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CustomerService customerService;
    private final BookService bookService;
    private final ObjectMapper objectMapper;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public OrderDTO save(Long customerId, OrderRequestDTO orderRequestDTO) {
        CustomerModel customerModel = customerService.findById(customerId);

        List<OrderItemModel> orderItemModels = new ArrayList<>();
        Float totalPrice = 0F;
        OrderModel orderModel = new OrderModel();
        for (OrderItemRequestDTO orderItemRequestDTO: orderRequestDTO.getOrderItems()) {
          OrderItemModel orderItemModel = objectMapper.convertValue(orderItemRequestDTO,  OrderItemModel.class);
          BookModel bookModel = bookService.findById(orderItemModel.getBook().getId());
          if(bookModel.getStock() >= orderItemModel.getQuantity()) {
              bookService.decreaseStock(bookModel.getId(), orderItemModel.getQuantity() * -1);
          } else {
              bookService.decreaseStock(bookModel.getId(), orderItemModel.getQuantity());
              throw new FriendlyException(SystemMessage.INVALID_QUANTITY);
          }

          orderItemModel.setBook(bookModel);
          orderItemModel.setPrice(bookModel.getPrice());
          orderItemModel.setOrder(orderModel);
          orderItemModels.add(orderItemModel);
          totalPrice += orderItemModel.getPrice() * orderItemModel.getQuantity();
        }
        orderModel.setOrderItem(orderItemModels);
        orderModel.setCustomer(customerModel);
        orderModel.setPrice(totalPrice);
        orderRepository.save(orderModel);
        return objectMapper.convertValue(orderModel, OrderDTO.class);
    }

    @Override
    public OrderDTO get(Long customerId, Long id) {
        OrderModel orderModel = orderRepository.findById(id).orElseThrow(() -> new FriendlyException(SystemMessage.RECORD_NOT_FOUND));
        return objectMapper.convertValue(orderModel, OrderDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long customerId, Long id) {
        OrderModel orderModel = orderRepository.findById(id).orElseThrow(() -> new FriendlyException(SystemMessage.RECORD_NOT_FOUND));
        orderRepository.delete(orderModel);
    }

    @Override
    public List<OrderDTO> getAll(Long customerId, Date startDate, Date endDate) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<OrderModel> criteriaQuery = cb.createQuery(OrderModel.class);
        Root<OrderModel> root = criteriaQuery.from(OrderModel.class);
        List<Predicate> predicates = new ArrayList<>();

        if(customerId != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("orderDate"), customerId));
        }
        if(startDate != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("orderDate"), startDate));
        }
        if(endDate != null) {
            predicates.add(cb.equal(root.get("customerId"), endDate));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<OrderModel> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList().stream().map(orderModel -> objectMapper.convertValue(orderModel, OrderDTO.class)).collect(Collectors.toList());
    }
}
