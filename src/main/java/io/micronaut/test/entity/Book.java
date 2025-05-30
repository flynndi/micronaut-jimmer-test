package io.micronaut.test.entity;

import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.GeneratedValue;
import org.babyfish.jimmer.sql.GenerationType;
import org.babyfish.jimmer.sql.Id;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;

@Entity
public interface Book extends TenantAware, BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id();

	String name();

	int edition();

	BigDecimal price();

	@Nullable
	Long storeId();
}
