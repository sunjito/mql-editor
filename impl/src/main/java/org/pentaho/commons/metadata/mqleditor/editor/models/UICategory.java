/*!
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
 */

package org.pentaho.commons.metadata.mqleditor.editor.models;

import java.util.List;

import org.pentaho.commons.metadata.mqleditor.MqlCategory;
import org.pentaho.commons.metadata.mqleditor.beans.Category;
import org.pentaho.commons.metadata.mqleditor.beans.Column;
import org.pentaho.ui.xul.stereotype.Bindable;

public class UICategory extends AbstractModelNode<UIColumn> implements MqlCategory {

  private String id, name;

  public UICategory( Category category ) {
    this.id = category.getId();
    this.name = category.getName();

    for ( Column col : category.getBusinessColumns() ) {
      UIColumn c = new UIColumn( col );
      this.children.add( c );
    }
  }

  public String getId() {
    return id;
  }

  public void setId( String id ) {
    this.id = id;
  }

  @Bindable
  public String getName() {
    return this.name;
  }

  @Bindable
  public void setName( String name ) {
    this.name = name;
  }

  public UICategory() {

  }

  public List<UIColumn> getBusinessColumns() {
    return this.getChildren();
  }

  public void setBusinessColumns( List<UIColumn> cols ) {
    this.children = cols;
  }

}
