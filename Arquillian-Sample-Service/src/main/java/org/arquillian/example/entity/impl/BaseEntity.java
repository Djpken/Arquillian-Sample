package org.arquillian.example.entity.impl;

import org.arquillian.example.entity.IBaseEntity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Kinson, Jason, Robin
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements IBaseEntity, Serializable {


}
