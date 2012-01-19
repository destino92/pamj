/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is the Kowari Metadata Store.
 *
 * The Initial Developer of the Original Code is Plugged In Software Pty
 * Ltd (http://www.pisoftware.com, mailto:info@pisoftware.com). Portions
 * created by Plugged In Software Pty Ltd are Copyright (C) 2001,2002
 * Plugged In Software Pty Ltd. All Rights Reserved.
 *
 * Contributor(s): N/A.
 *
 * [NOTE: The text of this Exhibit A may differ slightly from the text
 * of the notices in the Source Code files of the Original Code. You
 * should use the text of this Exhibit A rather than the text found in the
 * Original Code Source Code for Your Modifications.]
 *
 */

package org.mulgara.query;

// Java 2 standard packages
import java.util.*;

// Third party packages
import org.apache.log4j.Category;

/**
 * A constraint expression composed of two subexpressions and a dyadic operator.
 * Implements {@link Transformable}.
 *
 * @created 2001-08-12
 *
 * @author <a href="http://staff.pisoftware.com/raboczi">Simon Raboczi</a>
 *
 * @version $Revision$
 *
 * @modified $Date: 2005/01/05 04:58:20 $
 *
 * @maintenanceAuthor $Author: newmana $
 *
 * @company <A href="mailto:info@PIsoftware.com">Plugged In Software</A>
 *
 * @copyright &copy; 2001-2003 <A href="http://www.PIsoftware.com/">Plugged In
 *      Software Pty Ltd</A>
 *
 * @licence <a href="{@docRoot}/../../LICENCE">Mozilla Public License v1.1</a>
 */
public abstract class ConstraintOperation extends AbstractConstraintExpression {

  /**
   * Allow newer compiled version of the stub to operate when changes
   * have not occurred with the class.
   * NOTE : update this serialVersionUID when a method or a public member is
   * deleted.
   */
  static final long serialVersionUID = -236847137057853871L;

  /**
   * Logger.
   */
  private static Category logger =
      Category.getInstance(ConstraintOperation.class.getName());

  //
  // Constructor
  //

  /**
   * Construct a constraint operation. Subclasses are compelled to use this
   * constructor, guaranteeing that the operands are always non-<code>null</code>
   * .
   *
   * @param lhs a non-<code>null</code> model expression
   * @param rhs another non-<code>null</code> model expression
   */
  protected ConstraintOperation(ConstraintExpression lhs, ConstraintExpression rhs) {
    // Validate "lhs" parameter
    if (lhs == null) {
      throw new IllegalArgumentException("Null \"lhs\" parameter");
    }

    // Validate "rhs" parameter
    if (rhs == null) {
      throw new IllegalArgumentException("Null \"rhs\" parameter");
    }

    // Initialize fields
    elements = new ArrayList(2);

    // Add the LHS
    if (lhs.getClass().equals(getClass())) {
      elements.addAll( ( (ConstraintOperation) lhs).getElements());
    } else {
      elements.add(lhs);
    }

    // Add the RHS
    if (rhs.getClass().equals(getClass())) {
      elements.addAll( ( (ConstraintOperation) rhs).getElements());
    } else {
      elements.add(rhs);
    }
  }

  /**
   * CONSTRUCTOR ConstraintOperation TO DO
   *
   * @param elements PARAMETER TO DO
   */
  protected ConstraintOperation(List elements) {
    // Validate "elements" parameter
    if (elements == null) {
      throw new IllegalArgumentException("Null \"elements\" parameter");
    }

    // Initialize fields
    this.elements = new ArrayList(elements);

    ListIterator i = this.elements.listIterator();

    while (i.hasNext()) {
      Object o = i.next();

      assert o != null;
      if (o instanceof ConstraintExpression ||
          o instanceof Constraint) {
        i.set(o);
      } else {
        logger.error("Bad element: "+o.getClass()+" in "+getClass());
        throw new Error("Bad element: " + o.getClass());
      }
    }
  }


  /**
   * Accessor method for the operands.
   *
   * @return a list of {@link ConstraintExpression}s
   */
  public List getElements() {
    return Collections.unmodifiableList(elements);
  }

  /**
   * Get a constraint element by index.
   *
   * @param index The constraint element to retrieve, from 0 to 3.
   * @return The constraint element referred to by index.
   */
  public ConstraintExpression getOperand(int index) {
    return (ConstraintExpression) elements.get(index);
  }


  /**
   * Defines Structual equality.
   *
   * @return equality.
   */
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }

    ConstraintOperation co = (ConstraintOperation) o;
    if (elements.size() != co.elements.size()) {
      return false;
    }

    Iterator lhs = elements.iterator();
    Iterator rhs = co.elements.iterator();
    while (lhs.hasNext()) {
      if (!(lhs.next().equals(rhs.next()))) {
        return false;
      }
    }

    return true;
  }

  /**
   * Get all constraints which are variables. For back-compatibility, this
   * method currently ignores the fourth element of the triple.
   *
   * @return A set containing all variable constraints.
   */
  public Set getVariables() {
    // Check to see if there variables have been retrieved.
    if (variables == null) {
      Set v = new HashSet();

      for (Iterator it = getElements().iterator(); it.hasNext(); ) {
        // Get the operands and check to see if they are a valid constraint.
        Object e = it.next();
        if (e instanceof ConstraintExpression) {
          v.addAll(((ConstraintExpression)e).getVariables());
        }
      }

      variables = Collections.unmodifiableSet(v);
    }

    return variables;
  }


  /**
   * Generate hashcode for this object.
   *
   * @return This objects hascode.
   */
  public int hashCode() {
    int hashCode = 0;
    Iterator i = elements.iterator();
    while (i.hasNext()) {
      hashCode ^= i.next().hashCode();
    }
    return hashCode;
  }

  /**
   * Convert this object to a string.
   *
   * @return A string representation of this object.
   */
  public String toString() {
    StringBuffer buffer = new StringBuffer("(" + getName());
    Iterator i = getElements().iterator();

    while (i.hasNext()) {
      buffer.append(i.next().toString());
      if (i.hasNext()) {
        buffer.append(" ");
      }
    }

    buffer.append(")");

    return buffer.toString();
  }

  /**
   * Gets the Name attribute of the ConstraintOperation object
   *
   * @return The Name value
   */
  abstract String getName();
}