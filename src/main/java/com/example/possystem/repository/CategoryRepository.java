package com.example.possystem.repository;

import com.example.possystem.modal.Category;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Locale;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByStoreId (Long storeId);
}
