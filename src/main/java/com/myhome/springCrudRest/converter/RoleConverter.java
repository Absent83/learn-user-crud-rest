package com.myhome.springCrudRest.converter;

import com.myhome.springCrudRest.model.Role;
import com.myhome.springCrudRest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A converter class used in views to map id's to actual role objects.
 */
@Component
public class RoleConverter implements Converter<Object, Role> {

	@Autowired
	RoleService roleService;

	/**
	 * Gets Role by Id
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public Role convert(Object element) {
		Integer id = Integer.parseInt((String)element);

		return roleService.get(id).orElseThrow(IllegalArgumentException::new);
	}
}