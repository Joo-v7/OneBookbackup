package com.nhnacademy.taskapi.order.service.Impl;

import com.nhnacademy.taskapi.book.domain.Book;
import com.nhnacademy.taskapi.book.exception.BookNotFoundException;
import com.nhnacademy.taskapi.book.repository.BookRepository;
import com.nhnacademy.taskapi.order.dto.BookOrderRequest;
import com.nhnacademy.taskapi.order.service.PricingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PricingServiceImpl implements PricingService {
    @Override
    public int calculatorToTalPriceByOrderRequest(List<BookOrderRequest> items, BookRepository bookRepository) {
        int totalPrice = 0;
        for (BookOrderRequest item : items) {
            int salePrice = bookRepository.findById(item.getBookId())
                    .orElseThrow(() -> new BookNotFoundException("Book id " + item.getBookId() + "not found!!")).getSalePrice();
            totalPrice += salePrice;
        }

        return totalPrice;
    }

    @Override
    public int calculatorDeliveryFee(int totalBookPrice) {
        return totalBookPrice > 30000 ? 5000 : 0;
    }
}
