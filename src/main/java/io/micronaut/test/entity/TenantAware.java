package io.micronaut.test.entity;

import org.babyfish.jimmer.sql.MappedSuperclass;

@MappedSuperclass
public interface TenantAware {

	String tenant();
}
