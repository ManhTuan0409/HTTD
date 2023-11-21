package com.example.HTTD.Repository;

import com.example.HTTD.Entity.ExCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<ExCategory, Long> {

}
