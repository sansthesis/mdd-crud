package com.github.jasonrose.crud.om.generated;

import java.util.Set;
import com.github.jasonrose.crud.om.DefaultDao;
import javax.validation.Validator;
import com.github.jasonrose.crud.om.Contact;

public class ContactDefaultDao extends DefaultDao<Contact> implements ContactDao {
  public ContactDefaultDao(final Validator validator) {
    super(validator);
  }

  public ContactDefaultDao() {
    super();
  }
  
  public Set<Contact> getContactsByDivision(Long id) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
}
