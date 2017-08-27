import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

public class Parent implements Serializable {
  protected transient Integer foo;

  @EnsuresNonNull("foo")
  public Parent() {
    initTransientFields();
  }

  @EnsuresNonNull("foo")
  private void initTransientFields() {
    foo = 42;
  }

  @EnsuresNonNull("foo")
  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();
    initTransientFields();
  }

  private void readObjectNoData() {
    initTransientFields();
  }
}
