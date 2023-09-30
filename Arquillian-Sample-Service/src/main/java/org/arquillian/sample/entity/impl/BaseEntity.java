package org.arquillian.sample.entity.impl;

import org.arquillian.sample.entity.IBaseEntity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Kinson, Jason, Robin
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements IBaseEntity, Serializable {


}
