package org.pentaho.metadata.editor.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.pentaho.ui.xul.XulEventSourceAdapter;

public class AbstractModelNode<T> extends XulEventSourceAdapter implements Collection<T>, Iterable<T> {

  protected List<T> children = new ArrayList<T>();

  public AbstractModelNode() {
  }

  public AbstractModelNode(List<T> children) {
    Collections.copy(this.children, children);
  }

  public List<T> getChildren() {
    // return Collections.unmodifiableList(children);
    
    return children;
  }

  protected void fireCollectionChanged() {
    this.changeSupport.firePropertyChange("children", null, this.getChildren());
  }

  public boolean add(T child) {
    boolean retVal = this.children.add(child);
    fireCollectionChanged();
    return retVal;
  }

  public boolean remove(int idx){
    return this.remove(this.children.get(idx));
  }
  public boolean remove(Object child) {
    if (!this.children.contains(child)) {
      throw new IllegalArgumentException("Child does not exist in collection");
    }
    boolean retVal = this.children.remove(child);
    fireCollectionChanged();
    return retVal;
  }

  public T removeModel(int pos) {
    if (pos > this.children.size()) {
      throw new IllegalArgumentException("Specified position (" + pos + ") is greater than collection length");
    }
    T retVal = this.children.remove(pos);
    fireCollectionChanged();
    return retVal;
  }

  public Iterator<T> iterator() {
    return this.children.iterator();
  }

  public void clear() {
    this.children.clear();
    fireCollectionChanged();
  }

  public void moveChildUp(UIBusinessColumn column) {
    if (!this.children.contains(column)) {
      throw new IllegalArgumentException("child does not exist in collection");
    }

    int pos = this.children.indexOf(column);
    moveChildUp(pos);
  }

  public void moveChildUp(int position) {
    if (position-1 < 0 ) {
      throw new IllegalArgumentException("Specified position (" + position
          + ") is greater than child collection length");
    }
    //If already at Beginning do nothing
    if (position == 0) {
      return;
    }
    T child = this.children.remove(position);
    this.children.add(position -1 , child);
    fireCollectionChanged();
  }

  public void moveChildDown(UIBusinessColumn column) {
    if (!this.children.contains(column)) {
      throw new IllegalArgumentException("child does not exist in collection");
    }

    int pos = this.children.indexOf(column);
    moveChildDown(pos);
  }

  public void moveChildDown(int position) {
    if (position < 0 || position+1 >= this.children.size()) {
      throw new IllegalArgumentException("Specified position (" + position
          + ") is greater than child collection length");
    }

    T child = this.children.remove(position);
    this.children.add(position + 1, child);
    fireCollectionChanged();
  }


  public List<T> asList() {
    //UnmodifiableList not serializable
    //return Collections.unmodifiableList(this.children);
    
    return this.children;
  }

  public boolean addAll(Collection<? extends T> c) {
    return this.children.addAll(c);
  }

  public boolean contains(Object o) {
    return this.children.contains(o);
  }

  public boolean containsAll(Collection<?> c) {
    boolean retval = true;
    for (Object t : c) {
      if (this.children.contains(t) == false) {
        retval = false;
        break;
      }
    }
    return retval;
  }

  public boolean isEmpty() {
    return this.children.isEmpty();
  }

  public boolean removeAll(Collection<?> c) {
    boolean retVal = this.children.removeAll(c);

    fireCollectionChanged();
    return retVal;
  }

  public boolean retainAll(Collection<?> c) {
    boolean retVal = this.children.retainAll(c);

    fireCollectionChanged();
    return retVal;
  }

  public int size() {
    return this.children.size();
  }

  public Object[] toArray() {
    return this.children.toArray();
  }

  public <T> T[] toArray(T[] a) {
    return this.children.toArray(a);
  }

}
