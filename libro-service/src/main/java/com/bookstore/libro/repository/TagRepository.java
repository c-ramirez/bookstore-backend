package com.bookstore.libro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookstore.libro.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{
	Tag findByNombre(String nombre);
	@Query("SELECT tag FROM Tag tag ORDER BY tag.nombre DESC")
	Page<Tag> obtenerCategorias(Pageable pageable);
}
