package com.github.jasonrose.crud.om.generated;

import java.util.Set;
import com.github.jasonrose.crud.om.Dao;
import com.github.jasonrose.crud.om.Contact;

public interface ContactDao extends Dao<Contact> {
  
  Set<Contact> getContactsByDivision(Long id);
}
